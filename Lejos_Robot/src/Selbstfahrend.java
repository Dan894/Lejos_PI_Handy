import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
public class Selbstfahrend {

	/**
	 * Klassen für Differenzialsteuerung
	 */
	public double wheelDiameter;//radDurchmesser
	public double trackWidth;//radAbstand
	
	public NXTRegulatedMotor rightMotor;
	public NXTRegulatedMotor leftMotor;
	
	private double distancePerTurn; //Radumfang und damit zurückgelegt Strecke
	private double distancePerDeg; //Entfernung per Motortack 1 Durchlauf
	
	/**
	 * Konstruktoren
	 * @param args
	 * @param left Instanz für linken Motor
	 * @param right Instanz für rechten Motor
	 * @param wheel Raddurchmesser in cm
	 * @param track Spurbreite in cm
	 */
	public Selbstfahrend (NXTRegulatedMotor left, NXTRegulatedMotor right, double wheel, double track){
		this.leftMotor = left;
		this.rightMotor = right;
		this.wheelDiameter = wheel;
		this.trackWidth = track;
		
		this.distancePerTurn = (Math.PI * wheelDiameter);
		this.distancePerDeg = (distancePerTurn / 360);
	}
	
	public void drive(double v, double omega){
		double _radius;
		double _rightSpeed;
		double _leftSpeed;
		
		//Sollgeschwindigkeit des Roboters in Grad/s
		double _speedDegreesMiddle = v / distancePerDeg * 100;
		
		if (omega != 0){
			_radius = v * 100 / omega; //Umrechnung von m in 100
			
			//Zu fahrende Radien der Räder
			double _rightRadius = _radius + trackWidth /2;
			double _leftRadius = _radius - trackWidth /2;
			
			if (_radius != 0) { //1. Fall Kurve vorhaden
				_rightSpeed = _rightRadius * _speedDegreesMiddle / _radius;
				_leftSpeed = _leftRadius * _speedDegreesMiddle / _radius;
			} else { //2. Fall Drehen auf der Stelle
				_rightSpeed = omega * _rightRadius / distancePerDeg;
				_leftSpeed = -_rightSpeed;
			}
		} else { //3.Fall Gearadeaus
			_rightSpeed = _speedDegreesMiddle;
			_leftSpeed = _speedDegreesMiddle;
		}
		
		//Absolutbetrag Konversion nach Integer
		int _rightSpeedInt = (int) Math.abs(_rightSpeed);
		int _leftSpeedInt = (int) Math.abs(_leftSpeed);
		
		//Geschwindigkeit begrenzen (maximal 900)
		rightMotor.setSpeed(Math.min(_rightSpeedInt, 900));
		leftMotor.setSpeed(Math.min(_leftSpeedInt, 900));
		
		if (_rightSpeed != 0){
			if (_rightSpeed >0){
				rightMotor.forward();
			} else {
				rightMotor.backward();
			}
		}
		if (_leftSpeed !=0){
			if (_leftSpeed > 0){
				leftMotor.forward();
			}else{
				leftMotor.backward();
			}
		}
	}
	
	//Methode zum Abbremsen und zum Ausrollen
	public void stop(){
		leftMotor.stop();
		rightMotor.stop();
	}
	public void flt(){
//		this.updatePos(); //Odometriebestimmung noch nicht definiert
		leftMotor.flt();
		rightMotor.flt();
	}
	public void driveDistance(double v, double omega, double stopDistance, boolean flt){
		double _distance = 0.0;
		
		//Tacho wert zurücksetzen
		rightMotor.resetTachoCount();
		leftMotor.resetTachoCount();
		
		//Fahren mit den angegebenen Parametern
		this.drive(v, omega);
		
		//Wiederholung des Fahrens solange Entfernung nicht errreicht ist
		while(_distance <= stopDistance){
			//Mittelung der Strecke beider Räder
			_distance = ((Math.abs(leftMotor.getTachoCount())+ Math.abs(rightMotor.getTachoCount()))/2)*distancePerDeg;
		}
		if(flt){
			this.flt();
		}else{
			this.stop();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
