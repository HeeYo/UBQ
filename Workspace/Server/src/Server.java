import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import com.pi4j.io.serial.Serial;
import com.pi4j.io.serial.SerialDataEvent;
import com.pi4j.io.serial.SerialDataListener;
import com.pi4j.io.erial.SerialPortException;


public class Server extends Thread{
 private InputStream is;
 private OutputStream os;
 private ServerSocket serverSocket;
 Socket socket;
 public void run() throws InterruptedException{
  try{
   serverSocket = new ServerSocket(5002);       
   while(true){
    //  try{
	  init()
     System.out.println("요청 대기");
     socket = serverSocket.accept();
     System.out.println("접속한 클라이언트 : "+
       socket.getInetAddress());
     is = socket.getInputStream();
     os = socket.getOutputStream();            
     
     
     if(msg == "Led On"){
    	 serial.write("LED ON");
    	 oos.writeObject("Led On");
     }
     else if(msg == "Led Off"){
    	 serial.write("LED OFF");
    	 oos.writeObject("Led Off");
     }
     else if(msg == "Close"){
    	 socket.close();
     }
   }
  }
  catch(Exception e){
   e.printStackTrace();
  }            
 }   
 public static void main(String[] args){
  new Server().start();
 }
}