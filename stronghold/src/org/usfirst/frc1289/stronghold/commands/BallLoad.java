// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1289.stronghold.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc1289.stronghold.Robot;


/**
 *
 */
public class BallLoad extends Command {


    public BallLoad() {

        requires(Robot.ballHandler);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.ballHandler.ballPneu(false);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if (Robot.ballHandler.getSwitch() < 3.5)
    		Timer.delay(0.5);
    		Robot.ballHandler.moveball(0.75);
    	//else
    		//Robot.ballHandler.moveball(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (Robot.ballHandler.getSwitch() < 3.5) return false;
    	else return true;
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.ballHandler.moveball(0);
    	Robot.ballHandler.ballPneu(true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}