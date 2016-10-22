package org.usfirst.frc1289.stronghold.commands;

import org.usfirst.frc1289.stronghold.Robot;
import org.usfirst.frc1289.stronghold.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToHeading extends Command {

	float NewHeading;
	double TotalError;
	
    public DriveToHeading(float Desired) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	NewHeading = Desired;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	TotalError = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double Heading = RobotMap.navx.getFusedHeading();
    	double Difference = Heading - NewHeading;
    	double speed = Difference * 0.5 + TotalError*0.0001;
    	SmartDashboard.putNumber("DriveToHeading Current heading", Heading);
    	SmartDashboard.putNumber("Drive to Heading Speed", speed);
    	TotalError=TotalError + Difference;
    	Robot.driveTrain.turnrobot(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        Float CurrentHeading = RobotMap.navx.getFusedHeading();
        SmartDashboard.putNumber("Desired Heading", NewHeading);
        SmartDashboard.putNumber("DriveviaCompass Heading", CurrentHeading);
        
        if (Math.abs(CurrentHeading - NewHeading) < 2 ) return true; else return false;
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
