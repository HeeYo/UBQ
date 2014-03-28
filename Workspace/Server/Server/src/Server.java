import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
//import com.pi4j.io.serial.Serial;
//import com.pi4j.io.serial.SerialDataEvent;
//import com.pi4j.io.serial.SerialDataListener;
//import com.pi4j.io.serial.SerialFactory;
//import com.pi4j.io.serial.SerialPortException;


public class Server extends Thread{
 private InputStream is;
 private OutputStream os;
 private ServerSocket serverSocket;
 private ObjectInputStream ois;
 ObjectOutputStream oos;
 Socket socket;
 public void run() {
  try{
   serverSocket = new ServerSocket(5001);       
   while(true){
    //  try{
	   //init();
     System.out.println("Server Open");
     socket = serverSocket.accept();
     System.out.println("Client Connected : "+
       socket.getInetAddress());
     is = socket.getInputStream();
     os = socket.getOutputStream();
     ois = new ObjectInputStream(is);
     oos = new ObjectOutputStream(os);               
     String msg = (String)ois.readObject();

     if(msg == "Led On"){
    	 //serial.write("LED ON");
    	 oos.writeObject("Led On");
     }
     else if(msg == "Led Off"){
    	 //serial.write("LED OFF");
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