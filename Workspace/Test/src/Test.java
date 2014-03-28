import java.io.*;
import java.net.*;

public class Test implements Runnable {
	
	private static ServerSocket server;
	private static Socket socket;
	private SocketAddress   clientAddress;
	private static final int sizeBuf = 50;
	private int rcvBufSize;
    private byte[] rcvBuf = new byte[sizeBuf];
    
	public Test(Socket clientSocket, SocketAddress clientAddress) {
        this.socket  = clientSocket;
        this.clientAddress = clientAddress;
    }
	
	public void run(){
		try{
			InputStream in   = socket.getInputStream();
	        OutputStream out = socket.getOutputStream();
	        
	        while ((rcvBufSize = ins.read(rcvBuf)) != -1) {
	            String rcvData = new String(rcvBuf, 0, rcvBufSize, "UTF-8");
	            
	            if (rcvData.compareTo("Fan On") == 0)
	            	System.out.println("Fan On");
	            
	            if (rcvData.compareTo("Fan Off") == 0)
	            	System.out.println("Fan Off");
	            
	            System.out.println("Received data : " + rcvData + " (" + clientAddress + ")");
	              outs.write(rcvBuf, 0, rcvBufSize);
	            
	            
	        }
	        
			System.out.println("HI");
		} catch (IOException e){
			System.out.println("Exception: " + e);
		} finally{
			try {
	            socket.close();
	            System.out.println("Disconnected! Client IP : " + clientAddress);
	          } catch (IOException e) {
	            System.out.println("Exception: " + e);
	          }
		}
	}
	
	
	
	
	public static void main(String[] args){
		try{
			
		    
			System.out.println("Server start");			
			server = new ServerSocket(5003);
			while(true){
				Socket socket = server.accept();
				 Thread thread = new Thread(new Test(socket, socket.getRemoteSocketAddress()));

				if(socket.isConnected()) {
					System.out.println("Connected Client IP : " + socket.getRemoteSocketAddress());
				}
			}
		} catch (IOException e) {
			System.out.println("Exception: " + e);
		}
	}
}