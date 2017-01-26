package org.usfirst.frc.team1289.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The IOMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class IOMap {
	// IO Ports
	private static final int _io_DriveTrainLeftFrontMotor = 0;
	private static final int _io_DriveTrainRightFrontMotor = 1;
	private static final int _io_DriveTrainLeftRearMotor = 2;
	private static final int _io_DriveTrainRightRearMotor = 3;
	private static final int _io_EncoderLeft_A_Source = 0;
	private static final int _io_EncoderLeft_B_Source = 1;
	private static final int _io_EncoderRight_A_Source = 3;
	private static final int _io_EncoderRight_B_Source = 4;
	
	public static final int _io_JoystickPort = 0;
	public static final int _io_JoystickCradleMotorButton = 0;
	public static final int _io_JoystickWinchMotorButton = 1;
	
	public static final int _io_CradleMotor = 9;
	public static final int _io_WinchMotor = 8;
	// 
	public static SpeedController driveTrainMotorLeftFront;
	public static SpeedController driveTrainMotorRightFront;
	public static SpeedController driveTrainMotorLeftRear;
	public static SpeedController driveTrainMotorRightRear;
    public static RobotDrive driveTrainRobotDrive;
    public static Encoder driveTrainLeftEncoder;
	public static Encoder driveTrainRightEncoder;
	
    public static SpeedController cradleMotor;
    public static SpeedController winchMotor;
    
	private static final boolean _io_DriveTrainSafetyEnabled = true;
	private static final double _io_DriveTrainExpiration = 0.1;
	private static final double _io_DriveTrainSensitivity = 0.1;
	private static final double _io_DriveTrainMaxOutput = 1.0;
	private static final double _io_WheelDiameter = 8.5;
	private static final double _io_EncoderPulsesPerRotation = 360.0;
	private static final boolean _io_EncoderLeftReverse = false;
	private static final boolean _io_EncoderRightReverse = true;
	private static double _encoderPulseDistance;
	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    
    public static void init()
    {
        driveTrainMotorLeftFront = new Talon(_io_DriveTrainLeftFrontMotor);
        LiveWindow.addActuator("DriveTrain", "MotorLeftFront", (Talon) driveTrainMotorLeftFront);
        
        driveTrainMotorRightFront = new Talon(_io_DriveTrainRightFrontMotor);
        LiveWindow.addActuator("DriveTrain", "MotorRightFront", (Talon) driveTrainMotorRightFront);
     
        driveTrainMotorLeftRear = new Talon(_io_DriveTrainLeftRearMotor);
        LiveWindow.addActuator("DriveTrain", "MotorLeftRear", (Talon) driveTrainMotorLeftRear);
        
        driveTrainMotorRightRear = new Talon(_io_DriveTrainRightRearMotor);
        LiveWindow.addActuator("DriveTrain", "MotorRightRear", (Talon) driveTrainMotorRightRear);
     
        driveTrainRobotDrive = new RobotDrive(driveTrainMotorLeftFront, driveTrainMotorLeftRear, 
        									driveTrainMotorRightFront, driveTrainMotorRightRear);
        
        driveTrainRobotDrive.setSafetyEnabled(_io_DriveTrainSafetyEnabled);
        driveTrainRobotDrive.setExpiration(_io_DriveTrainExpiration);
        driveTrainRobotDrive.setSensitivity(_io_DriveTrainSensitivity);
        driveTrainRobotDrive.setMaxOutput(_io_DriveTrainMaxOutput);

        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        
        driveTrainLeftEncoder = new Encoder(_io_EncoderLeft_A_Source, _io_EncoderLeft_B_Source, 
        		_io_EncoderLeftReverse, EncodingType.k4X);

        driveTrainRightEncoder = new Encoder(_io_EncoderRight_A_Source, _io_EncoderRight_B_Source, 
        		_io_EncoderRightReverse, EncodingType.k4X);

        // Distance Per Pulse = Diameter of Wheel (8.5in * Pi)/360 pulses per revolution = .0741765in
        // NOTE right quadrature encoder turns in opposite direction from left.
        // (8.5 * PI) / 360 = 0.0741765 inches/pulse;
        _encoderPulseDistance = (_io_WheelDiameter * Math.PI) / _io_EncoderPulsesPerRotation; 

        driveTrainLeftEncoder.setDistancePerPulse(_encoderPulseDistance);
        driveTrainLeftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        LiveWindow.addSensor("DriveTrain", "LeftQuadEncoder", driveTrainLeftEncoder);
        driveTrainRightEncoder.setDistancePerPulse(_encoderPulseDistance);
        driveTrainRightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        LiveWindow.addSensor("DriveTrain", "RightQuadEncoder", driveTrainRightEncoder);
        
        cradleMotor = new Talon(_io_CradleMotor);
        LiveWindow.addActuator("Cradle", "Cradle Motor", (Talon) cradleMotor);
        winchMotor = new Talon(_io_WinchMotor);
        LiveWindow.addActuator("Winch", "Winch Motor", (Talon) winchMotor);
        
    }
}
