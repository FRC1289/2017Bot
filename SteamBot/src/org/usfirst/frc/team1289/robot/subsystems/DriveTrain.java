
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
	private static Encoder _leftEncoder = IOMap.driveTrainLeftEncoder;
	private static Encoder _rightEncoder = IOMap.driveTrainRightEncoder;
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
    	return Math.pow(rawValue, 3);
    	
//    	double slowSlope = 0.375;
//    	double mediumSlope = 3.0;
//    	double fastSlope = 4;
//    	double slowToMediumBreakPoint = 0.8;
//    	double mediumToFastBreakPoint = 0.9;
//    	double mediumIntercept = 2.1;
//    	double fastIntercept = 3.0;
//    	
//    	// -0.5 < rawValue < 0.5 : y=0.4x+0
//    	if (rawValue > -slowToMediumBreakPoint && rawValue < slowToMediumBreakPoint)
//    		return slowSlope * rawValue;
//    	// 0.5 <= rawValue < 0.8 : y = 1.0x - 0.3 
//    	else if (rawValue >= slowToMediumBreakPoint && rawValue < mediumToFastBreakPoint)
//    		return mediumSlope * rawValue - mediumIntercept;
//    	// -0.8 < rawValue <= -0.5 : y = 1.0x + 0.3
//    	else if (rawValue > -mediumToFastBreakPoint && rawValue <= -slowToMediumBreakPoint)
//    		return rawValue + mediumIntercept;
//    	// 0.8 <= rawValue <= 1.0) : y = 2.5x - 1.5
//    	else if (rawValue > mediumToFastBreakPoint && rawValue <= 1.0)
//    		return fastSlope * rawValue - fastIntercept;
//    	// -1.0 <= rawValue < -0.8 : y = -2.5x - 1.5
//    	else if (rawValue <= -1.0 && rawValue >= mediumToFastBreakPoint)
//    		return fastSlope * rawValue + fastIntercept;
//    	else
//    		return rawValue;
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
    	return _leftEncoder.getDistance();
    }
    
    public double GetRightEncoderDistance()
    {
    	return _rightEncoder.getDistance();
    }
    
   public void ResetEncoders()
   {
	   _leftEncoder.reset();
	   _rightEncoder.reset();
   }
}

