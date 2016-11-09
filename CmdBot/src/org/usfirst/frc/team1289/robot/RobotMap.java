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

import org.ini4j.*;
import java.io.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	 private static Ini _iniParser;
	 
	 private static DigitalInput _armLimitSwitch; 			
	 private static Solenoid _armPneumaticHorizontal;		
	 private static Solenoid _armPneumaticVertical;
	 private static RobotDrive _driveTrain;
	 private static SpeedController _driveTrainMotorLeft;
	 private static SpeedController _driveTrainMotorRight;
	 private static Encoder _driveTrainLeftQuadEncoder;
	 private static Encoder _driveTrainRightQuadEncoder;
	 
	 
	 public void init() throws IOException
	 {
		 _iniParser = new Ini(new File("robot.ini"));
		 
	     _armLimitSwitch = new DigitalInput(_iniParser.get("arm", "LimitSwitch", int.class));
	     LiveWindow.addSensor("Arm", "Limit Switch 1", _armLimitSwitch);
	     
	     _armPneumaticHorizontal = new Solenoid(_iniParser.get("arm", "SolenoidModelNumber", int.class), _iniParser.get("arm", "HorizontalChannel", int.class));
	     LiveWindow.addActuator("Arm", "Pneumatic Horizontal", _armPneumaticHorizontal);
	     
	     _armPneumaticVertical = new Solenoid(_iniParser.get("arm", "SolenoidModelNumber", int.class), _iniParser.get("arm", "VerticalChannel", int.class));
	     LiveWindow.addActuator("Arm", "Pneumatic Vertical", _armPneumaticVertical);
	     
	     _driveTrainMotorLeft = new Talon(_iniParser.get("drivetrain", "LeftMotor", int.class));
	     LiveWindow.addActuator("DriveTrain", "MotorLeft", (Talon) _driveTrainMotorLeft);
	        
	     _driveTrainMotorRight = new Talon(_iniParser.get("drivetrain", "RightMotor", int.class));
	     LiveWindow.addActuator("DriveTrain", "MotorRight", (Talon) _driveTrainMotorRight);
	      
	     _driveTrain = new RobotDrive(_driveTrainMotorLeft, _driveTrainMotorRight);
	     _driveTrain.setSafetyEnabled(_iniParser.get("drivetrain", "SafetyEnabled", boolean.class));
	     _driveTrain.setExpiration(_iniParser.get("drivetrain", "Expiration", double.class));
	     _driveTrain.setSensitivity(_iniParser.get("drivetrain", "Sensitivity", double.class));
	     _driveTrain.setMaxOutput(_iniParser.get("drivetrain", "MaxOutput", double.class));
	     _driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

	     _driveTrainLeftQuadEncoder = new Encoder(_iniParser.get("drivetrain", "LeftQuadEncoder_aSource", int.class),
	    		 								  _iniParser.get("drivetrain", "LeftQuadEncoder_bSource", int.class),
	    		 								  _iniParser.get("drivetrain", "LeftQuadEncoderReverse", boolean.class), EncodingType.k4X);
	     _driveTrainLeftQuadEncoder.setDistancePerPulse(_iniParser.get("drivetrain", "EncoderPulseDistance", double.class));
	     _driveTrainLeftQuadEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
	     LiveWindow.addSensor("DriveTrain", "LeftQuadEncoder", _driveTrainLeftQuadEncoder);
	     
	     _driveTrainRightQuadEncoder = new Encoder(_iniParser.get("drivetrain", "RightQuadEncoder_aSource", int.class),
				  								   _iniParser.get("drivetrain", "RightQuadEncoder_bSource", int.class),
				  								   _iniParser.get("drivetrain", "RightQuadEncoderReverse", boolean.class), EncodingType.k4X);
	     _driveTrainRightQuadEncoder.setDistancePerPulse(_iniParser.get("drivetrain", "EncoderPulseDistance", double.class));
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
		 return _iniParser.get("drivetrain", "EncoderPulseDistance", double.class); 
	 }
}
