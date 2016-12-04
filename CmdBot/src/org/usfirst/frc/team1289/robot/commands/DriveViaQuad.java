package org.usfirst.frc.team1289.robot.commands;

import org.usfirst.frc.team1289.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveViaQuad extends Command {
	private DriveTrain _driveTrain;
	private double _distance;
	private double _speed;
		
	// Constructor
	public DriveViaQuad(DriveTrain drivetrain, double speed, double distance) {
		this._driveTrain = drivetrain;
		this._distance = distance;
		this._speed = speed;
	}
	// Called just before this Command runs the first time
	protected void initialize() {
		_driveTrain.Initialize(_speed);
		_driveTrain.Reset();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		_driveTrain.MoveForward();

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		double distancePerPulse = _driveTrain.GetEncoderPulseDistance();
		
		// convert count to distance & average the two values
		int leftCount = _driveTrain.GetLeftEncoderCount();
		int rightCount = _driveTrain.GetRightEncoderCount();
		double leftDistance = leftCount * distancePerPulse;
		double rightDistance = rightCount * distancePerPulse; 
		double averageDistance = Math.abs((leftDistance + rightDistance) / 2.0);
		
		//SmartDashboard.putNumber("LeftEncoderCount", leftCount);
		//SmartDashboard.putNumber("RightEncoderCount", rightCount);
		//SmartDashboard.putNumber("Distance", averageDistance);
		if (averageDistance < _distance)
			return false;
		else
			return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		_driveTrain.Stop();
		_driveTrain.Reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		_driveTrain.Stop();
	}
}