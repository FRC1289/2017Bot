package org.usfirst.frc1289.stronghold.subsystems;

import org.usfirst.frc1289.stronghold.RobotMap;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {
    
	private final Servo ServoUpDown = RobotMap.cameraUpDown;
	private final Servo ServoLeftRight = RobotMap.cameraLeftRight;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void ZeroCamera () {
    	ServoUpDown.set(.5);
    	ServoLeftRight.set(.5);
    }
    
    public void setCamera (double UpDown, double LeftRight) {
    	ServoUpDown.set(UpDown);
    	ServoLeftRight.set(LeftRight);
    }
}

