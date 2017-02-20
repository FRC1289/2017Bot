package org.usfirst.frc.team1289.robot.commands;

import org.usfirst.frc.team1289.robot.OperatorInterface;
import org.usfirst.frc.team1289.robot.Robot;
import org.usfirst.frc.team1289.robot.commands.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WinchEnable extends Command 
{
	private static boolean _isDone = false; 

    public WinchEnable() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot._winchSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	Robot._winchSubsystem.Stop();
    	Robot._winchSubsystem.Reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	if (! _isDone)
    		Robot._winchSubsystem.Start();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
    	if (Robot._winchSubsystem.IsAtLimit())
    	{
    		//Timer.delay(2.0);
    		_isDone = true;
    	}
    	return _isDone;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot._winchSubsystem.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}
