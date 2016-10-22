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
    	Robot.arm.ArmUpDown(true); // lift the arm
    	System.out.println("Initialize Arm Reset.\n");
    }
    
    protected void execute () {
    
    
    	//Timer.delay(1.0);
    	
    	//Timer.delay(0.5);
    	//Robot.arm.ArmUpDown(false); // lower arm
    	
    }
    
    protected boolean isFinished() {
    	return true;
    }
    
    protected void end() {
    	Robot.arm.ArmInOut(false); // Retract the arm
    	System.out.println("leaving Arm reset.\n");
    	//Robot.arm.ArmUpDown(false);
    }
    
    protected void interrupted() {
    	end();
    	
    }
 
}
