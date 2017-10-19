package mail;
import java.io.*;
import java.util.*;

class Pop3Message implements Serializable{

	private String allt;
	private transient StringTokenizer strTok;
	private transient String boundary;
	private transient BufferedReader in;
	private transient Vector parts;



/**
* public Save(String file){}
*
*
*/
	public void Save(String file){
		System.out.println("Pop3Message.Save: " + file);
		try{
			PrintWriter ofile = new PrintWriter(new FileOutputStream( file ));
			ofile.write(allt);
			ofile.close();
		}catch (IOException io){
			System.out.println("exception: " + io.toString());
		}

	}

/**
* @param enchilda, hela brevet; 
* kanske dela upp: huvud s�v�l som kropp
* delas upp i l�mpliga delar som kan h�mtas visas
* och kanske sparas
*/

	public Pop3Message(String enchilada){

		allt = enchilada;	
		boundary = GetHeader("boundary");	
		//System.out.println("this is a boundary: " + boundary);
		//parts = getParts(boundary.substring(0, boundary.length() - 2));
		//Enumeration e = parts.elements();
		//while(e.hasMoreElements())
		//	System.out.println("H�R KOMMER EN DEL: " + (String)e.nextElement());	
	}

/**
* denna metod �r menad att returnera brevets kropp, det vill s�ga
* brevets inneh�ll, det blir dock lite problem med multiparts och s�dant
* jag har hitills funnit tre olika varianter p� att skriva text/plain
* det finns s�kert fler, 
*
*/
	public String getBody(){
		String temp = "";
		int i = 0;
		Enumeration e = parts.elements();
		if((i = parts.size()) == 1){	//detta �r inte ett multiPart	
			temp = (String) e.nextElement();
			i = temp.indexOf("\n\n");
			return temp.substring(i);
		}
		else{
			while(e.hasMoreElements())
			temp = (String) e.nextElement();
			i = temp.indexOf("Content-Type: text/plain;");
			if(i == -1)
				i = temp.indexOf("Content-Type: TEXT/PLAIN;");
			if (i == -1)
				i = temp.indexOf("Content-Type: text/plain");
			if(i == -1)
				return null;
			i = temp.indexOf("\n\n");
			return temp.substring(i);
		}
	}

/**
* en lite lustig metod, den kollar att brevet �r multipart och anropar i 
* s� fall getheader med "boundary:=" som argument, vilket den f�rvisso misslyckas
* med (getHeader hittar allts� inte boundary och returnerar s�lunda den sista raden
* i meddelandet som �r just den unika avgr�nsaren, (man f�r bara hoppas att ingen rad
* i brevets kropp b�rjar med boundary, s� jag borde nog g�ra om metoden
*/
	private String getBoundary(){
		String header = GetHeader("Content-Type:");
		if(header.equals("multipart/mixed;"))
			return GetHeader("boundary:=");
		else
			return null;
	}

/**
* det verkar som vi nu har ett s�tt att skapa brevdelar
*
*/
	private Vector getParts(String boundary){
		String temp =allt;
		Vector vektor = new Vector();
		int a = 0;
		int b = 0;
		while((b = temp.indexOf(boundary))!= -1){
			vektor.add(temp.substring(a, b));
			temp = temp.substring(b + boundary.length(), temp.length());
		}
		return vektor;
	}


/**
* detta �r en generell metod f�r att h�mta olika delare av ett brevhuvud
* @ param header; n�gon av de rubriker som specificerade i rfc 8?? vad det nu va
* 2003:05:04, changed name to GetHeader
*/

	public String GetHeader(String header){
		String temp = "";
		boolean found = false;
		strTok = new StringTokenizer(allt, "\n");
		while(strTok.hasMoreTokens() && !found){
			temp = strTok.nextToken();	
			if(temp.regionMatches(true, 0, header, 0, header.length())){
				temp = temp.substring(header.length());
				found = true;
			}	
		}
		if ( found){
			return temp;
		}
		return "";
	}
}


/**
* en klass som kanske skall utvecklas, vid behov det vill s�ga om 
* jag vill kunna hantera olika typer p� olika s�tt
*/

class aMultiPart implements Serializable{
	
	private String delStr;

	public aMultiPart(String str){
		delStr = str;
	}
	
//den h�r g�r att g�ra snyggare
	public String getPlainText(){
		int i = 0;
		String temp = delStr;
			i = temp.indexOf("Content-Type: text/plain;");
			if(i == -1)
				i = temp.indexOf("Content-Type: TEXT/PLAIN;");
			if (i == -1)
				i = temp.indexOf("Content-Type: text/plain");
			if(i == -1)
				return null;
			i = temp.indexOf("\n\n");
			return temp.substring(i);
	}

}