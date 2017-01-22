
package org.usfirst.frc.team1289.robot.subsystems;

import org.usfirst.frc.team1289.robot.IOMap;
import org.usfirst.frc.team1289.robot.OperatorInterface;
import org.usfirst.frc.team1289.robot.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
public class DriveTrain extends Subsystem 
{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static SpeedController _leftMotor = IOMap.driveTrainMotorLeftFront;
	private static SpeedController _rightMotor = IOMap.driveTrainMotorRightFront;
	private static RobotDrive _robotDrive = IOMap.driveTrainRobotDrive;

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveViaJoystick());
    }
    
    // Sets the motor speed of all motors to the desired setting
    public void Move (double speed)
    {
     _robotDrive.arcadeDrive(speed, 0.0);
    }

    // Sets the motor speed in opposite directions so robot turns
    // Caller has to invert speed to get opposite turning behavior
    public void Turn(double speed) 
    {
    	_leftMotor.set(speed);
    	_rightMotor.set(-speed);
    }

    // Scale the raw move value into a piecewise linear equation
    private double ScaleMoveValue(double rawValue)
    {
    	double slowSlope = 0.4;
    	//double mediumSlope = 1.0;
    	double fastSlope = 2.5;
    	double slowToMediumBreakPoint = 0.5;
    	double mediumToFastBreakPoint = 0.8;
    	double mediumIntercept = 0.3;
    	double fastIntercept = 1.5;
    	
    	// -0.5 < rawValue < 0.5 : y=0.4x+0
    	if (rawValue > -slowToMediumBreakPoint && rawValue < slowToMediumBreakPoint)
    		return slowSlope * rawValue;
    	// 0.5 <= rawValue < 0.8 : y = 1.0x - 0.3 
    	else if (rawValue >= slowToMediumBreakPoint && rawValue < mediumToFastBreakPoint)
    		return rawValue - mediumIntercept;
    	// -0.8 < rawValue <= -0.5 : y = -1.0x - 0.3
    	else if (rawValue > -mediumToFastBreakPoint && rawValue <= -slowToMediumBreakPoint)
    		return rawValue + mediumIntercept;
    	// 0.8 <= rawValue <= 1.0) : y = 2.5x - 1.5
    	else if (rawValue > mediumToFastBreakPoint && rawValue <= 1.0)
    		return fastSlope * rawValue - fastIntercept;
    	// -1.0 <= rawValue < -0.8 : y = -2.5x - 1.5
    	else if (rawValue <= -1.0 && rawValue >= mediumToFastBreakPoint)
    		return fastSlope * rawValue + fastIntercept;
    	else
    		return rawValue;
    }
    
    public void ArcadeDrive()
    {
    	// this is inverted from what the WPI code says X & Y represent.
    	// but on-bot testing shows it works. And you have to negate the Y value...
    	double moveValue = OperatorInterface.joyStick.getY();
    	double rotateValue = OperatorInterface.joyStick.getX();
    	
    	moveValue = ScaleMoveValue(moveValue);
    	
    	SmartDashboard.putNumber("stickMoveValue", moveValue);
    	SmartDashboard.putNumber("stickRotateValue", rotateValue);
    	
    	_robotDrive.arcadeDrive(moveValue, rotateValue, true);
    }
    
    public void Stop()
    {
    	_robotDrive.stopMotor();
    }
}

