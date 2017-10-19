/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.StringTokenizer;

/**
 *
 * @author User
 */
class Account implements Comparable{
	private String popServer;
	private String m_user;
	private String m_pwd;
	private String m_port;
	private String m_name;
	private String m_all;
	public Account(String all){
		m_all = all;
		p_parse();
	}

    @Override
    public String toString() {
        return "Account{" + "popServer=" + popServer + ", m_user=" + m_user + ", m_pwd=" + m_pwd + ", m_port=" + m_port + ", m_name=" + m_name + '}';
    }
	public Account(String name, String ps, String user, String pwd, String port){
		m_name = name;
		popServer = ps;
		m_user = user;
		m_pwd = pwd;
		m_port = port;
	}
	public int compareTo(Object object){
            System.out.println("Account.compareTo");
		if ( getName().equals(
                        ((Account)object).getName()
                )){
			return 0;
		}
                
		return -1;
	}
	public boolean equals(Object o){
		return (compareTo(o) == 0 )? true: false;
	}
	private String p_get_value(String str){
		int i = str.indexOf(':');
		if(i == -1){
			return "error";
		}
		String s = str.substring(i +1, str.length());
		return s.trim();
	}
	private void p_parse(){
		StringTokenizer tokens = new StringTokenizer(m_all, "\n#");
		while (tokens.hasMoreTokens()){
			String token = tokens.nextToken();
			if (token.startsWith("name: ")){
				m_name = p_get_value(token);
			} 
			if (token.startsWith("pop_server: ")){
				popServer = p_get_value(token);
			}
			if (token.startsWith("user: ")){
				m_user = p_get_value(token);
			} 
			if (token.startsWith("pwd: ")){
				m_pwd = p_get_value(token);
			} 
			if (token.startsWith("port: ")){
				m_port = p_get_value(token);
			}  
		}
	}

	public String GetUser(){
		return m_user;
	}
	public String GetPwd(){
		return m_pwd;
	}
	public String getName(){
		return m_name;
	}
	public String GetPopServer(){
		return popServer;
	}
	public String GetPort(){
		return m_port;
	}
}
