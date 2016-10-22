package org.usfirst.frc1289.stronghold.commands;

import org.usfirst.frc1289.stronghold.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmReset extends Command {
    
    public  ArmReset() {
    	requires (Robot.arm);
    }
    
    protected void initialize () {
    }
    
    protected void execute () {
    
    	Robot.arm.ArmUpDown(true); // lift the arm
    	Timer.delay(1.0);
    	Robot.arm.ArmInOut(false); // Retract the arm
    	//Timer.delay(0.5);
    	//Robot.arm.ArmUpDown(false); // lower arm
    	
    }
    
    protected boolean isFinished() {
    	return false;
    }
    
    protected void end() {
    	//Robot.arm.ArmUpDown(false);
    }
    
    protected void interrupted() {
    	end();
    	
    }
 
}
