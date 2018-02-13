import java.io.* ;
import java.util.Vector;

import javax.bluetooth.* ;
import lejos.nxt.* ;
import lejos.nxt.comm.* ;
import lejos.nxt.remote.* ;
import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
//import lejos.devices.* ;

public class BTH {
	static NXTRegulatedMotor leftMotor = Motor.B;
	static NXTRegulatedMotor rightMotor = Motor.A;
	static String connected = "Connected";
    static String waiting = "Waiting...";
    static String closing = "Closing...";
	public static Selbstfahrend pilot = new Selbstfahrend(Motor.B, Motor.A, 5.6, 11.2);
	/**
	 * @param args
	 * @return 
	 * @throws IOException 
	 */
    private static byte[] buffer = new byte[240]; // Puffer
    
	public static void connection (byte[] buffer, int bytes) throws IOException{
        byte []outBuf = new byte[255]; 
        byte[] inBuf = new byte[255]; 
//		int remainder = bytes %2;
		int remainder = 120;
		Integer.toBinaryString(remainder);
//		LCD.drawString("Bytes"+ remainder, 0, 3);
//		String test = Integer.toString(remainder,2); // decimal to binary
//		LCD.drawString("BIN:"+ test, 0, 4);
		remainder = 200;
		outBuf[0]=(byte)127;
		outBuf[1]=(byte)200;
		outBuf[2]=(byte)6; 
//		String test2 = Integer.toString(outBuf[0],2); // decimal to binary
		LCD.drawString("BIN:"+ Integer.toString(outBuf[0],2), 0, 5);
		LCD.drawString("BYT:"+ Integer.toString(outBuf[1],2),0,6);
		LCD.drawString("vor:"+ Integer.toString(outBuf[2],2),0,7);
	}
	public static void main(String[] args) throws IOException{
		connection(buffer,5);
		LCD.drawString("Bytes Zerlegen!!!",0,0);
		LCD.drawString("Buffer:",0,1);
//		LCD.drawString(buffer.toString(),0,2);
		LCD.drawString("L: "+buffer.length,0,2);
//	    pilot.drive(0.1,0);
//	    pilot.drive(0.1,0);
//	    pilot.drive(0.1,0);
	    pilot.drive(2,0);
//		pilot.driveDistance(100, 0, 50, true); //vorwärts
//	    pilot.driveDistance(-0.1, 0, 25, true); //rück
//	    pilot.driveDistance(0.1, 1, 25, true); //links 45Grad
//	    pilot.driveDistance(-0.1, -1, 25, true); //links 45Grad zurück
//	    pilot.driveDistance(0.1, -1, 25, true); //rechtss 45Grad
//	    pilot.driveDistance(-0.1, 1, 25, true); //links 45Grad zurück
//	    pilot.driveDistance(0.1, 1, 25, true); //links 45Grad
//		pilot.driveDistance(0.1, 1, 25, true); //Drehung 45 Grad
//		pilot.driveDistance(0.2, 1, 50, true); //Drehung 45 Grad Proportional zur geschwindigkeit
//		pilot.driveDistance(-0.1, 3, 25, true); //Drehung
//	    pilot.driveDistance(-0.1, 4, 25, true); //Drehung ==90Grad
	    pilot.stop();
		Button.waitForAnyPress();
	}
}
