
package org.usfirst.frc.team1289.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team1289.robot.Robot;
import org.usfirst.frc.team1289.robot.subsystems.Arm;

/**
 *
 */
public class ArmUpCommand extends Command {
	private Arm _arm;

	// Constructor
    public ArmUpCommand(Arm arm) {
      this._arm = arm;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	_arm.MoveVertical(Arm.ArmState.ON);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _arm.GetSwitchState();
    }

    // Called once after isFinished returns true
    protected void end() {
    	_arm.MoveVertical(Arm.ArmState.OFF);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	_arm.MoveVertical(Arm.ArmState.OFF);
    }
}

