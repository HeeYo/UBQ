import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class server implements Runnable{
	
	private static final int sizeBuf = 50;
    private Socket      clientSocket;
    private SocketAddress   clientAddress;
    private int rcvBufSize;
    private byte[] rcvBuf = new byte[sizeBuf]; 
    private static ServerSocket serverSocket;
    private static int port = 5003;
  
    public server(Socket clientSocket, SocketAddress clientAddress) {
        this.clientSocket  = clientSocket;
        this.clientAddress = clientAddress;
    }
    
    public void run() {
        try {
            InputStream ins   = clientSocket.getInputStream();
            OutputStream outs = clientSocket.getOutputStream();

            while ((rcvBufSize = ins.read(rcvBuf)) != -1) {
              String rcvData = new String(rcvBuf, 0, rcvBufSize, "UTF-8");
              
             
                  
              System.out.println("Received data : " + rcvData + " (" + clientAddress + ")");
              outs.write(rcvBuf, 0, rcvBufSize);
            }
            System.out.println(clientSocket.getRemoteSocketAddress() + " Closed");

        } catch (IOException e) {
          System.out.println("Exception: " + e);
        } finally {
            try {
              clientSocket.close();
                System.out.println("Disconnected! Client IP : " + clientAddress);
            } catch (IOException e) {
              System.out.println("Exception: " + e);
            }
        }
      }
    
    
    
  public static void main(String[] args) {
    try {
      System.out.println("Start Server");
    
      serverSocket = new ServerSocket(port);
              
      while (true) {
      
        Socket clientSocket = serverSocket.accept();
        
        Thread thread = new Thread(new server(clientSocket, clientSocket.getRemoteSocketAddress()));
        thread.start();
        
        if(clientSocket.isConnected()) {
          System.out.println("Connected Client IP : " + clientSocket.getRemoteSocketAddress());
        }
      }
    } catch (IOException e) {
      System.out.println("Exception: " + e);
    } 
  }
}