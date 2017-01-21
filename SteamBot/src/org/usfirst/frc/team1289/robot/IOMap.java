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
	private static final int _io_DriveTrainLeftMotor = 0;
	private static final int _io_DriveTrainRightMotor = 1;
	private static final int _io_EncoderLeft_A_Source = 0;
	private static final int _io_EncoderLeft_B_Source = 1;
	private static final int _io_EncoderRight_A_Source = 3;
	private static final int _io_EncoderRight_B_Source = 4;
	
	public static final int _io_JoystickPort = 0;
	
	// 
	public static SpeedController driveTrainMotorLeft;
	public static SpeedController driveTrainMotorRight;
    public static RobotDrive driveTrainRobotDrive;
    public static Encoder driveTrainLeftEncoder;
	public static Encoder driveTrainRightEncoder;
	
	private static final boolean _io_DriveTrainSafetyEnabled = true;
	private static final double _io_DriveTrainExpiration = 0.1;
	private static final double _io_DriveTrainSensitivity = 0.1;
	private static final double _io_DriveTrainMaxOutput = 1.0;
	private static final double _io_WheelDiameter = 8.5;
	private static final boolean _io_EncoderLeftReverse = false;
	private static final boolean _io_EncoderRightReverse = true;
	private static double _encoderPulseDistance;
	
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
    
    public static void init()
    {
        driveTrainMotorLeft = new Talon(_io_DriveTrainLeftMotor);
        LiveWindow.addActuator("DriveTrain", "MotorLeft", (Talon) driveTrainMotorLeft);
        
        driveTrainMotorRight = new Talon(_io_DriveTrainRightMotor);
        LiveWindow.addActuator("DriveTrain", "MotorRight", (Talon) driveTrainMotorRight);
     
        driveTrainRobotDrive = new RobotDrive(driveTrainMotorLeft, driveTrainMotorRight);
        
        driveTrainRobotDrive.setSafetyEnabled(_io_DriveTrainSafetyEnabled);
        driveTrainRobotDrive.setExpiration(_io_DriveTrainExpiration);
        driveTrainRobotDrive.setSensitivity(_io_DriveTrainSensitivity);
        driveTrainRobotDrive.setMaxOutput(_io_DriveTrainMaxOutput);

        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        driveTrainLeftEncoder = new Encoder(_io_EncoderLeft_A_Source, _io_EncoderLeft_B_Source, 
        		_io_EncoderLeftReverse, EncodingType.k4X);

        driveTrainRightEncoder = new Encoder(_io_EncoderRight_A_Source, _io_EncoderRight_B_Source, 
        		_io_EncoderRightReverse, EncodingType.k4X);

        // Distance Per Pulse = Diameter of Wheel (8.5in * Pi)/360 pulses per revolution = .0741765in
        // NOTE right quadrature encoder turns in opposite direction from left.
        _encoderPulseDistance = (_io_WheelDiameter * Math.PI) / 360.0; //8.5" => 0.0741765 "/pulse;

        driveTrainLeftEncoder.setDistancePerPulse(_encoderPulseDistance);
        driveTrainLeftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        LiveWindow.addSensor("DriveTrain", "LeftQuadEncoder", driveTrainLeftEncoder);
        driveTrainRightEncoder.setDistancePerPulse(_encoderPulseDistance);
        driveTrainRightEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
        LiveWindow.addSensor("DriveTrain", "RightQuadEncoder", driveTrainRightEncoder);
    }
}
