import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;

import java.io.ByteArrayInputStream; //https://examples.javacodegeeks.com/core-java/io/bytearrayinputstream/java-bytearrayinputstream-example/
import java.util.Random;
//import java.util.zip.CRC32;

//import javax.bluetooth.RemoteDevice;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
//import lejos.nxt.comm.NXTConnection;

//import MOVE.src.TestPilot;

public class leijosBot{
	/**
	 * @param args
	 * @throws IOException 
	 */
//	protected void onCreate(){
//		super.getClass();
//	}
	public static Selbstfahrend pilot = new Selbstfahrend(Motor.B, Motor.A, 5.6, 11.2);
	
	public static void main(String[] args) throws IOException{
		
		//Kontrolle:
//		final byte[] buf = new byte[size];
//        while (myBIS.read(buf, 0, size) != -1)
//        {
//        }
		byte[] buffer = new byte[3];
        Random rnd = new Random();
        
        for (int i=0;i<buffer.length;i++) {
        	buffer[i] = (byte) rnd.nextInt();
        }
        
        ByteArrayInputStream b = new ByteArrayInputStream(buffer);

        System.out.println("All the elements in the buffer:");

//        int num;
//        while( (num = b.read()) != -1 ) {
//        	System.out.print(num+" ");
//        }
		
//		int buffsize= 5;	
		
	    byte[]outBuf = new byte[5]; 
	    
	    //Wenn Daten erst geladen werden sobald Verbindung vorhanden ist dann kann Startbyte und Checksumme raus
//	    outBuf[0]=(byte)5; //Startbyte 
	    outBuf[1]=(byte)127;//Linker Motor
		outBuf[2]=(byte)1; //Rechter Motor
		outBuf[3]=(byte)6; //Richtung
//		outBuf[4]=(byte)5; //Checksumme //https://stackoverflow.com/questions/4113890/how-to-calculate-the-internet-checksum-from-a-byte-in-java
		
		
		
		LCD.drawString("Punkte" + outBuf, 0, 3);
		LCD.drawString(""+Byte.toString(outBuf[0]), 0, 4);
		LCD.drawString("" + Integer.toBinaryString(outBuf[2]), 0, 5);
		LCD.drawString("" + outBuf[3], 0, 6);
		LCD.drawString("Länge"+ outBuf.length, 0, 6);
		
		LCD.drawString("Connecting...", 0, 0);
//		String name = "NXT";
//	    RemoteDevice btrd = Bluetooth.getKnownDevice(name);
//
//	    if (btrd == null) {
//	      LCD.clear();
//	      LCD.drawString("No such device", 0, 0);
//	      Button.waitForAnyPress();
//	      System.exit(1);
//	    }
//
	    BTConnection btc = Bluetooth.waitForConnection();

	    if (btc == null) {
	      LCD.clear();
	      LCD.drawString("Connect fail", 0, 0);
	      Button.waitForAnyPress();
	      System.exit(1); //Abbruch wenn keine Verbindung möglich ist
	    }
	  
	    LCD.clear();
	    LCD.drawString("Connected", 0, 0);
	    
	    DataInputStream dis = btc.openDataInputStream();
//	    DataOutputStream dos = btc.openDataOutputStream();
//	    https://developer.android.com/reference/java/io/ByteArrayOutputStream.html
	    
	    for(int i=0;i<100;i++) {
	      try { 
	    	pilot.drive(0.2,0);
//	    	LCD.drawString("Str:"+dis.read(outBuf), 0, 5);
	    	LCD.drawString("Str:"+b.read(outBuf), 0, 5);
	        LCD.drawInt(i*30000, 8, 0, 2);
//	        dos.writeInt(i*30000);
//	        dos.flush(); 
	      } catch (IOException ioe) {
	        LCD.drawString("Write Exception", 0, 0);
	      }
	    
	      try {
	        LCD.drawInt(dis.readInt(),8, 0,3);
	      } catch (IOException ioe) {
	        LCD.drawString("Read Exception ", 0, 0);
	      }
	    }
	  
	    try {
	      LCD.drawString("Closing... ", 0, 0);
	      dis.close();
	      b.close();
//	      dos.close();
	      btc.close();
	    } catch (IOException ioe) {
	      LCD.drawString("Close Exception", 0, 0);
	    }
	  
	    LCD.drawString("Finished",3, 4);
	    Button.waitForAnyPress();
	  
//		String connected = "Connected";
//	    String waiting = "Waiting...";
//	    String closing = "Closing...";
//	    
//	    byte []outBuf = new byte[255]; 
//	    
//	    outBuf[0]=(byte)127;
//		outBuf[1]=(byte)1;
//		outBuf[2]=(byte)6; 
//		
	    while (true) {
//	      LCD.drawString(waiting,0,0);
//	      NXTConnection connection = Bluetooth.waitForConnection(); 
////	      LCD.clear();
//	      LCD.drawString(connected,0,1);
//
//	      DataInputStream dis = connection.openDataInputStream();
//	      for(int i=0;i<100;i++) {
//	    	  if(i==1){
//	    		  LCD.drawString("Test", 0, 5);
//	    	  }
//	          int n = dis.readInt();
//	          LCD.drawInt(n,7,0,1);
////	          dos.writeInt(-n);
////	          dos.flush();
//	      }
////	      try {
////	          dis.read(outBuf);
////	          LCD.drawString("vor:"+ dis.read(outBuf),0,7);
////	        } catch (IOException e ) {
////	          System.out.println(" write error "); 
////	        }
////	      dis.read(outBuf, outBuf[2], 255);
////	      LCD.drawString("BIN:"+ Integer.toString(dis.read(outBuf),2), 0, 5);
////		  LCD.drawString("BYT:"+ Integer.toString(outBuf[1],2),0,6);
//		  LCD.drawString("vor:"+ dis.read(outBuf),0,7);
////	      DataInputStream dis1 = connection.openDataInputStream();
////	      DataOutputStream dos = connection.openDataOutputStream();
//	      
	      pilot.drive(0.2,0);
////	      (Speed, GRAD des Winkels???, Distance cm, ???)
////	      pilot.driveDistance(0.1, 0, 25, true);
//	      pilot.drive(0.1,0);
////	      pilot.driveDistance(-0.2, 2, 50, true);
////	      pilot.stop();
////	      pilot.drive(0.1, 0);
//	      
////	      for(int i=0;i<100;i++) {
////	        int n = dis1.readInt();
////	        LCD.drawInt(n,7,0,1);
//////	        dos.writeInt(-n);
//////	        dos.flush();
////	        LCD.drawString(dis1.toString(), 0, n);
//////	        pilot.drive(-20,0);
//////	        pilot.stop();
//////	        pilot.drive(20, 0);
	      }
//	      dis.close();
////	      dos.close();
//
//	      LCD.clear();
//	      LCD.drawString(closing,0,2);
//
//	      connection.close();
//	      Button.waitForAnyPress();
//	      LCD.clear();
	    }
//	}

//	private static void TestPilot(DataInputStream dis) throws IOException {
////		// TODO Auto-generated method stub
////		// TODO Auto-generated method stub
//		String connected = "Connected";
//	    String waiting = "Waiting...";
//	    String closing = "Closing...";
//
//	    while (true) {
//	      LCD.drawString(waiting,0,0);
//	      NXTConnection connection = Bluetooth.waitForConnection(); 
//	      LCD.clear();
//	      LCD.drawString(connected,0,1);
//
//	      DataInputStream dis1 = connection.openDataInputStream();
////	      DataOutputStream dos = connection.openDataOutputStream();
//
//	      for(int i=0;i<100;i++) {
//	        int n = dis1.readInt();
//	        LCD.drawInt(n,7,0,1);
////	        dos.writeInt(-n);
////	        dos.flush();
//	        LCD.drawString(dis1.toString(), 0, n);
//	        TestPilot(dis1);
//	      }
//	      dis1.close();
////	      dos.close();
//
//	      LCD.clear();
//	      LCD.drawString(closing,0,0);
//
//	      connection.close();
//	      LCD.clear();
//	    }
//	}

}
