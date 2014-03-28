import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws IOException{
		Socket client;
		//InputStream is = new client.getInputStream;
		//DataInputStream dis = new DataInputStream(is);
		
			
		try{
			client = new Socket("192.168.0.27", 5002);
			
		} catch (IOException e){
			System.out.println(e);
		}
	}

}
