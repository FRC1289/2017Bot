
package org.usfirst.frc.team1289.robot.subsystems;

import org.usfirst.frc.team1289.robot.IOMap;
import org.usfirst.frc.team1289.robot.OperatorInterface;
import org.usfirst.frc.team1289.robot.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Encoder;

/**
 *
 */
public class DriveTrain extends Subsystem 
{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static SpeedController _leftFrontMotor = IOMap.driveTrainMotorLeftFront;
	private static SpeedController _rightFrontMotor = IOMap.driveTrainMotorRightFront;
	private static SpeedController _leftRearMotor = IOMap.driveTrainMotorLeftRear;
	private static SpeedController _rightRearMotor = IOMap.driveTrainMotorRightRear;
//	private static Encoder _leftEncoder = IOMap.driveTrainLeftEncoder;
//	private static Encoder _rightEncoder = IOMap.driveTrainRightEncoder;
	private static RobotDrive _robotDrive = IOMap.driveTrainRobotDrive;

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveViaJoystick());
    }
    
    // Sets the motor speed of all motors to the desired setting
    // no rotation value, so no turning. This moves fwd/bkwd only
    public void Move (double speed)
    {
     _robotDrive.arcadeDrive(speed, 0.0);
    }

    // Scale the raw value into a piecewise linear equation
    private double ScaleValue(double rawValue)
    {
    	double deadBand = 0.1;
    	if (rawValue > -deadBand && rawValue < deadBand)
    		return 0.0;
    	else if (rawValue < 0.0) 
    		// circle 
    		//return Math.sqrt(1.0 - Math.pow(rawValue + deadBand, 2)) - 1.0;
    	    // cubic
    		return Math.pow(rawValue + deadBand, 3);
    	else 
    		// rawValue > 0.0
    		// circle 
    		// return -Math.sqrt(1.0 - Math.pow(rawValue - deadBand, 2)) - 1.0;
    		// cubic
    		return Math.pow(rawValue - deadBand, 3);
    }
    
    public void ArcadeDrive()
    {
    	double moveValue = OperatorInterface.joyStick.getY();
    	double rotateValue = OperatorInterface.joyStick.getX();
    	
    	moveValue = ScaleValue(moveValue);
    	rotateValue = ScaleValue(rotateValue);
    	
    	SmartDashboard.putNumber("stickMoveValue", moveValue);
    	SmartDashboard.putNumber("stickRotateValue", rotateValue);
    	
    	_robotDrive.arcadeDrive(moveValue, rotateValue);
    }
    
    public void Stop()
    {
    	_robotDrive.stopMotor();
    }
    
    public double GetLeftEncoderDistance()
    {
    	return 0.0;//_leftEncoder.getDistance();
    }
    
    public double GetRightEncoderDistance()
    {
    	return 0.0;//_rightEncoder.getDistance();
    }
    
   public void ResetEncoders()
   {
	   //_leftEncoder.reset();
	   //_rightEncoder.reset();
   }
}

