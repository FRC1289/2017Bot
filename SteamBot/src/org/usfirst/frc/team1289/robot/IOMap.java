package org.usfirst.frc.team1289.robot;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The IOMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class IOMap {
	private static final int _io_DriveTrainLeftMotor = 0;
	private static final int _io_DriveTrainRightMotor = 1;
	private static final boolean _io_DriveTrainSafetyEnabled = true;
	private static final double _io_DriveTrainExpiration = 0.1;
	private static final double _io_DriveTrainSensitivity = 0.5;
	private static final double _io_DriveTrainMaxOutput = 1.0;
	private static final double _io_WheelDiameter = 8.5;
	
	public static final int _io_JoystickPort = 0;
	
	public static SpeedController driveTrainMotorLeft;
	public static SpeedController driveTrainMotorRight;
    public static RobotDrive driveTrainRobotDrive;
    
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
    }
}
