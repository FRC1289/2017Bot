package org.usfirst.frc1289.stronghold.commands;

import org.usfirst.frc1289.stronghold.Robot;
import org.usfirst.frc1289.stronghold.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoLowBar extends Command {

    public AutoLowBar() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveTrainQuadLeft.reset();
    	RobotMap.driveTrainQuadRight.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.ballHandler.ballPneu(false);
    	Robot.arm.ArmUpDown(false);
    	Timer.delay(0.5);
    	
    	Robot.driveTrain.moverobot(-0.5);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
      	double quadl = Robot.driveTrain.getquad(1);
    	double quadr = Robot.driveTrain.getquad(2);
    	SmartDashboard.putNumber("DrivewithQuad Left",quadl);
    	SmartDashboard.putNumber("DrivewithQuad Right",quadr);
    	SmartDashboard.putNumber("calc distance", (quadl+quadr)/2);
    	if ( Math.abs(((quadl + quadr)/2)) > 250) return true; else return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.moverobot(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
