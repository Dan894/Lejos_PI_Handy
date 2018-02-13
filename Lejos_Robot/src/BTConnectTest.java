import java.io.*;
import javax.bluetooth.*;
import lejos.nxt.*;
import lejos.nxt.comm.*;

public class BTConnectTest {
	public static Selbstfahrend pilot = new Selbstfahrend(Motor.B, Motor.A, 5.6, 11.2);
	
	public static void main(String[] args) throws Exception {
//		LCD.drawString(Bluetooth.getKnownDevicesList(), 0, 1);
		String name = "NXT";
		LCD.drawString("Connecting...", 0, 0);
		RemoteDevice btrd = Bluetooth.getKnownDevice(name);
		System.out.println(btrd);

		if (btrd == null) {
			LCD.clear();
			LCD.drawString("No such device", 0, 0);
			System.out.println(btrd);
			Button.waitForAnyPress();
			System.exit(1);
		}

		BTConnection btc = Bluetooth.connect(btrd);

		if (btc == null) {
			LCD.clear();
			LCD.drawString("Connect fail", 0, 0);
			Button.waitForAnyPress();
			System.exit(1);
		}

		DataInputStream dis = btc.openDataInputStream();
		DataOutputStream dos = btc.openDataOutputStream();

		for(int i=0;i<100;i++) {
			try { 
				LCD.drawInt(i*30000, 8, 0, 2);
				dos.writeInt(i*30000);
				dos.flush(); 
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
			dos.close();
			btc.close();
		} catch (IOException ioe) {
			LCD.drawString("Close Exception", 0, 0);
		}
  
		LCD.drawString("Finished",3, 4);
		Button.waitForAnyPress();
	}
}