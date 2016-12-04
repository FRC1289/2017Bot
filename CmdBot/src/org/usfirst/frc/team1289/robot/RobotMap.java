package org.usfirst.frc.team1289.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;
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
	private static final int _io_ArmLimitSwitch = 4;
	private static final int _io_ArmSolenoidModelNumber = 0;
	private static final int _io_ArmHorizontalChannel = 4;
	private static final int _io_ArmVerticalChannel = 7;
	private static final int _io_DriveTrainLeftMotor = 0;
	private static final int _io_DriveTrainRightMotor = 1;
	private static final boolean _io_DriveTrainSafetyEnabled = true;
	private static final double _io_DriveTrainExpiration = 0.1;
	private static final double _io_DriveTrainSensitivity = 0.5;
	private static final double _io_DriveTrainMaxOutput = 1.0;
	private static final int _io_EncoderLeft_A_Source = 0;
	private static final int _io_EncoderLeft_B_Source = 1;
	private static final boolean _io_EncoderLeftReverse = false;
	private static final int _io_EncoderRight_A_Source = 3;
	private static final int _io_EncoderRight_B_Source = 4;
	private static final boolean _io_EncoderRightReverse = true;
	private static final double _io_WheelDiameter = 8.5;
	private static final int _io_JoystickPort = 0;
	private static final GenericHID.Hand _io_JoystickHand = GenericHID.Hand.kRight; 

	 
	 private static DigitalInput _armLimitSwitch; 			
	 private static Solenoid _armPneumaticHorizontal;		
	 private static Solenoid _armPneumaticVertical;
	 private static RobotDrive _driveTrain;
	 private static SpeedController _driveTrainMotorLeft;
	 private static SpeedController _driveTrainMotorRight;
	 private static Encoder _driveTrainLeftQuadEncoder;
	 private static Encoder _driveTrainRightQuadEncoder;
	 private static double _encoderPulseDistance;
	 
	 private static Joystick _joystick;
	 
	 public void init()
	 {
		 Preferences preferences = Preferences.getInstance();
		 
		 _armLimitSwitch = new DigitalInput(_io_ArmLimitSwitch);
	     LiveWindow.addSensor("Arm", "Limit Switch 1", _armLimitSwitch);
	    
	     _armPneumaticHorizontal = new Solenoid(_io_ArmSolenoidModelNumber, _io_ArmHorizontalChannel);
	     LiveWindow.addActuator("Arm", "Pneumatic Horizontal", _armPneumaticHorizontal);
	     
	     _armPneumaticVertical = new Solenoid(_io_ArmSolenoidModelNumber, _io_ArmVerticalChannel);
	     LiveWindow.addActuator("Arm", "Pneumatic Vertical", _armPneumaticVertical);
	    
	     _driveTrainMotorLeft = new Talon(_io_DriveTrainLeftMotor);
	     LiveWindow.addActuator("DriveTrain", "MotorLeft", (Talon) _driveTrainMotorLeft);
	        
	     _driveTrainMotorRight = new Talon(_io_DriveTrainRightMotor);
	     LiveWindow.addActuator("DriveTrain", "MotorRight", (Talon) _driveTrainMotorRight);
	      
	     _driveTrain = new RobotDrive(_driveTrainMotorLeft, _driveTrainMotorRight);
	     _driveTrain.setSafetyEnabled(_io_DriveTrainSafetyEnabled);
	     _driveTrain.setExpiration(_io_DriveTrainExpiration);
	     _driveTrain.setSensitivity(_io_DriveTrainSensitivity);
	     _driveTrain.setMaxOutput(_io_DriveTrainMaxOutput);
	     _driveTrain.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);

	     _driveTrainLeftQuadEncoder = new Encoder(_io_EncoderLeft_A_Source, _io_EncoderLeft_B_Source, 
	    		 								  _io_EncoderLeftReverse, EncodingType.k4X);
	     
	     _driveTrainRightQuadEncoder = new Encoder(_io_EncoderRight_A_Source, _io_EncoderRight_B_Source, 
				   _io_EncoderRightReverse, EncodingType.k4X);
				        
	     //Distance Per Pulse = Diameter of Wheel (8.5in * Pi)/360 pulses per revolution = .0741765in
	     // NOTE right quadrature encoder turns in opposite direction from left.
	     _encoderPulseDistance = (_io_WheelDiameter * Math.PI) / 360.0; //8.5" => 0.0741765 "/pulse;
   		 
	     _driveTrainLeftQuadEncoder.setDistancePerPulse(_encoderPulseDistance);
	     _driveTrainLeftQuadEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
	     LiveWindow.addSensor("DriveTrain", "LeftQuadEncoder", _driveTrainLeftQuadEncoder);
	     

	     _driveTrainRightQuadEncoder.setDistancePerPulse(_encoderPulseDistance);
	     _driveTrainRightQuadEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
	     LiveWindow.addSensor("DriveTrain", "RightQuadEncoder", _driveTrainRightQuadEncoder);
	     
	     _joystick = new Joystick(_io_JoystickPort);
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
	 
	 public Joystick GetJoystick()
	 {
		 return _joystick;
	 }
	 
	 public GenericHID.Hand GetJoystickHand()
	 {
		 return _io_JoystickHand;
	 }
}
