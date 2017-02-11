
package org.usfirst.frc.team1289.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1289.robot.IOMap;
import org.usfirst.frc.team1289.robot.Robot;


public class DriveViaEncoder extends Command {
	private double _speed;
	private double _distance;

    public DriveViaEncoder()
    {
    	requires(Robot._drivetrainSubsystem);
    	this._speed = SmartDashboard.getNumber("Auto Speed", 0.1);
    	this._distance = SmartDashboard.getNumber("Auto Distance", 96.0);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot._drivetrainSubsystem.Stop();
    	Robot._drivetrainSubsystem.ResetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	Robot._drivetrainSubsystem.Move(_speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
		double leftDistance = Robot._drivetrainSubsystem.GetLeftEncoderDistance();
		double rightDistance = Robot._drivetrainSubsystem.GetRightEncoderDistance();
		double averageDistance = Math.abs((leftDistance + rightDistance) / 2.0);
		
		SmartDashboard.putNumber("Distance", averageDistance);
		SmartDashboard.putNumber("LeftEncoderCount", Robot._drivetrainSubsystem.GetLeftEncoderCount());
		SmartDashboard.putNumber("RightEncoderCount", Robot._drivetrainSubsystem.GetRightEncoderCount());
		
		if (averageDistance < _distance)
			return false;
		else
			return true;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot._drivetrainSubsystem.Stop();
    	Robot._drivetrainSubsystem.ResetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    }
}
