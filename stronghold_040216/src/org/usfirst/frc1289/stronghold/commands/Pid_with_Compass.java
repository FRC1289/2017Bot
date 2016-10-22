package org.usfirst.frc1289.stronghold.commands;

import org.usfirst.frc1289.stronghold.Robot;
import org.usfirst.frc1289.stronghold.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Pid_with_Compass extends Command {
	private double setpoint;
	
    public Pid_with_Compass(double setpoint) {

    	requires (Robot.driveTrain);
    	this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

     	RobotMap.CompassPID.setSetpoint(setpoint);
     	RobotMap.CompassPID.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.CompassPID.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.CompassPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

}
