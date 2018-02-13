import java.io.IOException;

import lejos.nxt.Motor;

public class TestPilot {

	//Testpilot Konfiguration
	public static Selbstfahrend pilot = new Selbstfahrend(Motor.B, Motor.A, 5.6, 11.2);
	
	public TestPilot(){
		super();
	}
//	http://www.lejos.org/nxt/nxj/api/lejos/robotics/navigation/DifferentialPilot.html
	public static void main(String[] args) throws IOException{
		//Aufrufen des Piloten  fürs Fahren
		pilot.drive(0.2,0);
		//stop der Vorwärtsbewegung
		pilot.stop();
		// 10 cm Rückwärtsfahren
		pilot.driveDistance(-5, 0.5, 50, true);
	}
}
