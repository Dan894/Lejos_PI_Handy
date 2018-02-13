import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class HelloWorld {
	static NXTRegulatedMotor leftMotor = Motor.B;
	static NXTRegulatedMotor rightMotor = Motor.A;
  public static void main (String[] args) {
//    System.out.println("Hello World");
    leftMotor.setSpeed(700);
    rightMotor.setSpeed(700);
    
    for (int i = 0; i<=3; i++){
    	leftMotor.rotate(1023, true);
    	rightMotor.rotate(1023);
    	
    	leftMotor.rotate(-188, true);
    	rightMotor.rotate(188);
    }
//    Button.waitForAnyPress();
  }
}