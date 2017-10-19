package mail;
import java.net.*;
import java.io.*;
import java.util.*;

class Pop3Exception extends Exception{
	public Pop3Exception(String msg){
		super(msg);
	}
	public String toString(){
		return getMessage();
	}
}

class STAT{
	private boolean 	m_ok = false;
	private int	  	m_nmessages = 0;
	private int		m_tot_size = 0;

	private boolean p_parse_reply(String reply){
		boolean res = false;
		StringTokenizer tok = new StringTokenizer(reply, " ");
		if("+ok".equalsIgnoreCase(tok.nextToken())){
			m_ok = true;
			try {
				if( tok.hasMoreTokens()){
					m_nmessages = Integer.parseInt(tok.nextToken().trim());
				}
				else{
					System.out.println("read rfc1939...");
				}
				if( tok.hasMoreTokens()){
					String size = tok.nextToken().trim();
					if (size.endsWith(".")){
						size = size.substring(0, size.length() - 1);
					}
					m_tot_size = Integer.parseInt(size);
				}else{
					System.out.println("read rfc1939... \"...and the size of the maildrop in octets\"");
				}
				res = true;
			}catch (Exception e){
				System.out.println(e.toString());
			}
		}
		return res;
	}
	public STAT(String reply){
		System.out.println("STAT ctor");
		p_parse_reply(reply);
	}
	public STAT(){

	}
	public int GetTotalSize(){
		return m_tot_size;
	}
	public boolean IsOK(){
		return m_ok;
	}
	public int GetNumberMessages(){
		return m_nmessages;
	}
}

class UIDL{
	private String m_reply;
	public UIDL(String reply){
		m_reply = reply;
	}
	public String toString(){
		return m_reply;
	}
}

class LIST{
	private String m_reply;
	private Hashtable m_table = new Hashtable();
	private void p_parse_reply(String all){
		StringTokenizer tokens = new StringTokenizer(all, " \n");
		while(tokens.hasMoreTokens()){
			String ix = tokens.nextToken();
			String size = null;
			if (tokens.hasMoreTokens()){
				size = tokens.nextToken();
				m_table.put(ix, size);
				System.out.println("ix = " + ix + " size: " + size );
			}
		}
	}
	public LIST(String reply){
		m_reply = reply;
		p_parse_reply(reply);
	}
	public String toString(){
		return m_reply;
	}
	public String GetSize(int msg){
		String strix = Integer.toString(msg, 10);
		return (String) m_table.get(strix);
	}
	public int GetNumberMessages(){
		return m_table.size();
	}
}

public class Pop {
	private Socket 			m_socket;
	private BufferedReader 	m_in;
	private PrintWriter 		m_out;
	//private boolean	 		m_dbg = false;
	private IPop3CB			m_cb;

	private  boolean p_init_connection(String pop_server, int port) throws IOException{
		m_cb.Message("...CONNECTING...");
		m_socket = new Socket(pop_server, port);
		m_in = new BufferedReader(new InputStreamReader(m_socket.getInputStream()));
		m_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(m_socket.getOutputStream())),true);
		String reply = m_in.readLine();
		m_cb.Message(reply);
		if( p_CheckReply(reply)){
			return true;
		}
		return false;
	}
	private void p_close_connection(){
		CloseSocket();
	}
	private boolean p_authenticate(String user, String pwd) throws IOException{
		m_cb.OnStartAuthorization(user);
		boolean stat = false;
		do{
			String reply = SendCommand("USER " + user);
			if(!p_CheckReply(reply )){
				m_cb.Message("error USER");
				break;
			}
			m_out.println("PASS " + pwd);
			m_cb.Message("PASS " + "*********");
			reply = m_in.readLine();
			if(!p_CheckReply(reply )){
				m_cb.Message("error PASS");
				break;
			}
			else{
				m_cb.OnAuthorized("authorized");
				stat = true;
			}
		}while(false);
		return stat;
	}
	/**
	* the no operation command
	*
	*/
	public void DoNOOP(String pop_server, String user, String pwd, int port){
		try{
			p_init_connection(pop_server, port);
			p_authenticate(user, pwd);
			String reply = SendCommand("NOOP");
			if (!p_CheckReply(reply)){
				m_cb.Message("error NOOP");
			}
			reply = SendCommand("QUIT");
			if (!p_CheckReply(reply)){
				m_cb.Message("error QUIT ");
			}
			p_close_connection();
		}catch(IOException ie){
			m_cb.Message(ie.toString());
		}


	}
/**
*	the list command:
*
*/
	public LIST DoLIST(Account a){
		int iport = Integer.parseInt(a.GetPort());
		return DoLIST(a.GetPopServer(), a.GetUser(), a.GetPwd(), iport);

	}
	public LIST DoLIST(String pop_server, String user, String pwd, int port){
		LIST list = null;
		try{
			p_init_connection(pop_server, port);
			p_authenticate(user, pwd);
			String reply = SendCommand("LIST");
			if (!p_CheckReply(reply)){
				m_cb.Message("error LIST ");
			}
			list = new LIST(ReadToDot());
			reply = SendCommand("QUIT");
			if(!p_CheckReply(reply)){
				m_cb.Message("error QUITING");
			}	
			p_close_connection();
		}catch(IOException ie){
			m_cb.Message(ie.toString());
		}
		return list;
	}
	public Vector DoTOP(String pop_server, String user, String pwd, String port, int message){
		Vector v = new Vector();
		int iport = Integer.parseInt(port);
		try{
			p_init_connection(pop_server, iport);
			p_authenticate(user, pwd);
			String reply = SendCommand("STAT");
			STAT stat = new STAT(reply);
			if (stat.IsOK()){
				if( message == 0){
					for(int i = 1; i <= stat.GetNumberMessages(); i++){
						m_cb.Message("top message: " + i );
						reply = SendCommand("TOP " + i + " " + 20);
						if(p_CheckReply(reply)){
							v.add(new Pop3Message(ReadToDot()));
						}
					}
				}else{
					reply = SendCommand("TOP " + message + " " + 20);
					if(p_CheckReply(reply)){
						v.add(new Pop3Message(ReadToDot()));
					}
				}
			}
			reply = SendCommand("QUIT");
			if(!p_CheckReply(reply)){
				m_cb.Message("error QUITING");
			}
			p_close_connection();
		}catch(IOException ie){
			m_cb.Message(ie.toString());
		}
		return v;
	}
/**
* a private helper method... a lot of pop replies uses the dot
* as "end of reply"....
*/
	private String ReadToDot() throws IOException{
		m_cb.Message("...READING...");
		String reply = "";
		String s = m_in.readLine();
		while (! ".".equals(s)) {
			m_cb.Message(s);
			reply += s + "\n";
			s = m_in.readLine();
		}
		return reply;
	}
	public Vector DoTOP( Account a, int message){
		return DoTOP(a.GetPopServer(), a.GetUser(), a.GetPwd(), a.GetPort(), message);
	}
	public Vector DoRETR( Account a, int message){
		return DoRETR(a.GetPopServer(), a.GetUser(), a.GetPwd(), message);
	}

	/**
	*if message param is 0: retreive all
	*
	*/
	public Vector DoRETR(String pop_server, String user, String pwd, int message){
		m_cb.Message(".... DoRETR....");
		Vector messages = new Vector();
		try {
			p_init_connection(pop_server, 110);
			p_authenticate(user, pwd);
			do{
				String reply = SendCommand("STAT");
				STAT stat = new STAT(reply);
				if (stat.IsOK()){
					if ( message == 0){
						for(int i = 1; i <= stat.GetNumberMessages(); i++){
							messages.add(RetrieveMessage(i));
						}
					}else{
						messages.add(RetrieveMessage(message));
					}
				}
	    		reply = SendCommand("QUIT");
	    		if (!p_CheckReply(reply)){
					m_cb.Message("error QUIT");
				}
			}while(false);
		}catch(IOException e){
			m_cb.Message(e.toString());
		}finally {
			p_close_connection();
		}
		return messages;
	}

	private Pop3Message RetrieveMessage(int message){
		
		try{
			String reply = SendCommand("RETR " + message);
			if(p_CheckReply(reply )){
				return new Pop3Message(ReadToDot());
			}
			else{
				m_cb.Message("error RETR");
			}
		}catch(IOException ioe){
			m_cb.Message(ioe.toString());
		}
		return new Pop3Message("");
	}
	public void DoUIDL(String pop_server, String user, String pwd){
		m_cb.Message("...DoUIDL...");
		try{
			do{
				if(!p_init_connection(pop_server, 110)){
					break;
				}
				if( !p_authenticate(user, pwd) ){
					break;
				}
				String reply = SendCommand("UIDL");
				if(p_CheckReply(reply)){
					UIDL uidl = new UIDL(ReadToDot());
					m_cb.Message("UIDL.toString(): " + uidl.toString());
				}
				reply = SendCommand("QUIT");
	    		if(!p_CheckReply(reply)){
					m_cb.Message("error QUIT");
				}		
			}while(false);


		}catch(IOException ioe){
			m_cb.Message(ioe.toString());
		}

	}

	public STAT DoSTAT(String pop_server, String user, String pwd, int port)throws Pop3Exception{
		STAT stat = null;
		m_cb.OnStartSTAT();
		try {
			do{
				if(!p_init_connection(pop_server, port)){
					throw new Pop3Exception("error connecting to " + pop_server );
				}
				if( p_authenticate(user, pwd)== false ){
					break;
				}
				String reply = SendCommand("STAT");
				stat = new STAT(reply);
			}while(false);
			String reply = SendCommand("QUIT");
	    	if (!p_CheckReply(reply)){
				m_cb.Message("error quiting");
			}

		}catch(IOException e){
			m_cb.Message(e.toString());
		}finally {
			p_close_connection();
		}
		return stat;
	}
	private void CloseSocket(){
		try{
			m_socket.close();
		}catch(IOException e){
			m_cb.Message(e.toString());
		}
	}

    private boolean p_CheckReply(String reply)throws IOException {
		//System.out.println("checking reply");
		//boolean stat = true;
		//String s = m_in.readLine();
		//if(s == null){
		//	return false;
		//}
		//m_cb.Message("REPLY:\t" + s);//debug rad
		StringTokenizer tok = new StringTokenizer(reply);
		if("-err".equalsIgnoreCase(tok.nextToken())) {
			return false;
		}
		return true;
    }
/*'
*sends a single line command and reads/returns reply
*
*/
    String SendCommand(String cmd)throws IOException {
		m_cb.Message("CMD:\t" + cmd);
		m_out.println(cmd);
		boolean stat = true;
		String reply = m_in.readLine();
		m_cb.Message("REPLY:\t" + reply);
		return reply;
	}
	
    int consumeNo() throws IOException {
		int x = 0;
		String s = m_in.readLine();
		StringTokenizer tok = new StringTokenizer(s, " ");
		if("+ok".equalsIgnoreCase(tok.nextToken())){
			try {
				x = Integer.parseInt(tok.nextToken().trim());
			}catch (Exception e){
				x = 0;
			}
		}
		return x;
	}
	private static void print_usage(){
		System.out.println("a simple pop client");
		System.out.println("author curt bylund");
		System.out.println("updated 2003:04:30");
		
	}
	public Pop(IPop3CB cb){
		System.out.println("pop ctor");
		m_cb =cb;
	}
}
			 
		
