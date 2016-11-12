package org.usfirst.frc.team1289.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Preferences;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	 
	 private static DigitalInput _armLimitSwitch; 			
	 private static Solenoid _armPneumaticHorizontal;		
	 private static Solenoid _armPneumaticVertical;
	 private static RobotDrive _driveTrain;
	 private static SpeedController _driveTrainMotorLeft;
	 private static SpeedController _driveTrainMotorRight;
	 private static Encoder _driveTrainLeftQuadEncoder;
	 private static Encoder _driveTrainRightQuadEncoder;
	 private static Preferences _preferences;
	 private static double _encoderPulseDistance;
	 
	 public void init()
	 {
		 	_preferences = Preferences.getInstance();
		 
		 _armLimitSwitch = new DigitalInput(_preferences.getInt("LimitSwitch", 4));
	     LiveWindow.addSensor("Arm", "Limit Switch 1", _armLimitSwitch);
	    
	     _armPneumaticHorizontal = new Solenoid(_preferences.getInt("SolenoidModelNumber", 0), _preferences.getInt("HorizontalChannel", 4));
	     LiveWindow.addActuator("Arm", "Pneumatic Horizontal", _armPneumaticHorizontal);
	     
	     _armPneumaticVertical = new Solenoid(_preferences.getInt("SolenoidModelNumber", 0), _preferences.getInt("VerticalChannel", 7));
	     LiveWindow.addActuator("Arm", "Pneumatic Vertical", _armPneumaticVertical);
	    
	     _driveTrainMotorLeft = new Talon(_preferences.getInt("LeftMotor", 0));
	     LiveWindow.addActuator("DriveTrain", "MotorLeft", (Talon) _driveTrainMotorLeft);
	        
	     _driveTrainMotorRight = new Talon(_preferences.getInt("RightMotor", 1));
	     LiveWindow.addActuator("DriveTrain", "MotorRight", (Talon) _driveTrainMotorRight);
	      
	     _driveTrain = new RobotDrive(_driveTrainMotorLeft, _driveTrainMotorRight);
	     _driveTrain.setSafetyEnabled(_preferences.getBoolean("SafetyEnabled", true));
	     _driveTrain.setExpiration(_preferences.getDouble("Expiration", 0.1));
	     _driveTrain.setSensitivity(_preferences.getDouble("Sensitivity", 0.5));
	     _driveTrain.setMaxOutput(_preferences.getDouble("MaxOutput", 1.0));
	     _driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

	     _driveTrainLeftQuadEncoder = new Encoder(_preferences.getInt("LeftQuadEncoder_aSource", 0),
	    		 								  _preferences.getInt("LeftQuadEncoder_bSource", 1),
	    		 								  _preferences.getBoolean("LeftQuadEncoderReverse", false), EncodingType.k4X);
	      
	     //Distance Per Pulse = Diameter of Wheel (8.5in * Pi)/360 pulses per revolution = .0741765in
	     // NOTE right quadrature encoder turns in opposite direction from left.
	     double wheelDiameter = _preferences.getDouble("Wheel Diameter", 8.5);
	     _encoderPulseDistance = (wheelDiameter * Math.PI) / 360.0; //0.0741765;
   		 
	     _driveTrainLeftQuadEncoder.setDistancePerPulse(_encoderPulseDistance);
	     _driveTrainLeftQuadEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
	     LiveWindow.addSensor("DriveTrain", "LeftQuadEncoder", _driveTrainLeftQuadEncoder);
	     
	     _driveTrainRightQuadEncoder = new Encoder(_preferences.getInt("RightQuadEncoder_aSource", 2),
				  								   _preferences.getInt("RightQuadEncoder_bSource", 3),
				  								   _preferences.getBoolean("RightQuadEncoderReverse", true), EncodingType.k4X);
	     _driveTrainRightQuadEncoder.setDistancePerPulse(_encoderPulseDistance);
	     _driveTrainRightQuadEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
	     LiveWindow.addSensor("DriveTrain", "RightQuadEncoder", _driveTrainRightQuadEncoder);


		 }
	 
	 public DigitalInput GetArmLimitSwitch()
	 {
		 return _armLimitSwitch;
	 }
	 public Solenoid GetArmPneumaticHorizontal()
	 {
		 return _armPneumaticHorizontal;
	 }
	 public Solenoid GetArmPneumaticVertical()
	 {
		 return _armPneumaticHorizontal;
	 }
	 
	 public RobotDrive GetRobotDrive() 
	 { 
		 return _driveTrain;
	 }
	 
	 public SpeedController GetDriveTrainRightMotor()
	 {
		 return _driveTrainMotorRight;
	 }
	 
	 public SpeedController GetDriveTrainLeftMotor()
	 {
		 return _driveTrainMotorLeft;
	 }
	 
	 public Encoder GetDriveTrainLeftQuadEncoder()
	 {
		 return _driveTrainLeftQuadEncoder;
	 }
	 
	 public Encoder GetDriveTrainRightQuadEncoder()
	 {
		 return _driveTrainRightQuadEncoder;
	 }	 
	 
	 public double GetDistancerPerEncoderPulse()
	 {
		 return _encoderPulseDistance;
	 }
}
