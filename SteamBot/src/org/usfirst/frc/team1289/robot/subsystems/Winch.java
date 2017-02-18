package org.usfirst.frc.team1289.robot.subsystems;

import org.usfirst.frc.team1289.robot.IOMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Winch extends Subsystem 
{
	private static SpeedController _winchMotor = IOMap.winchMotor;
	private static DigitalInput _limitSwitch = IOMap.winchLimitSwitch;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void Start() 
    {
    	_winchMotor.set(1.0);
    }
    
    public void Stop()
    {
    	_winchMotor.stopMotor();
    }
    
    public boolean IsAtLimit()
    {
    	return !_limitSwitch.get();
    }
}

