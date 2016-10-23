
package org.usfirst.frc.team1289.robot.subsystems;

import org.usfirst.frc.team1289.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
    
	private org.usfirst.frc.team1289.robot.RobotMap _ioMap;
	private DigitalInput _limitSwitch;
    private Solenoid _pneumaticHorizontal;
    private Solenoid _pneumaticVertical;
    
    public enum ArmState {ON, OFF};
    private ArmState _horizontalState;
    private ArmState _verticalState;
	
	public Arm(RobotMap map)
	{
		this._ioMap = map;
		this._limitSwitch = _ioMap.GetArmLimitSwitch();
		this._pneumaticHorizontal = _ioMap.GetArmPneumaticHorizontal();
		this._pneumaticVertical = _ioMap.GetArmPneumaticVertical();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public boolean GetSwitchState()
    {
    	return _limitSwitch.get();
    }
    
    public void MoveVertical(ArmState on_off)
    {
       	_verticalState = on_off;
    	if (on_off == ArmState.ON)
    		_pneumaticVertical.set(true);
    	else
    		_pneumaticVertical.set(false);
    	
    }
    
    public ArmState GetVerticalState()
    {
    	return _verticalState;
    }
}

