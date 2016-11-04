package org.usfirst.frc.team1289.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

import org.usfirst.frc.team1289.robot.RobotMap;

public class DriveTrain {
	private org.usfirst.frc.team1289.robot.RobotMap _ioMap;
	private RobotDrive _driveTrain;
	
	public DriveTrain(RobotMap ioMap)
	{
		this._ioMap = ioMap;
		this._driveTrain = _ioMap.GetRobotDrive();	
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void MoveForward(double speed)
	{
		
	}
	
	public void MoveBackward(double speed)
	{
		
	}
	
	public void TurnLeft(double speed)
	{
		
	}
	
	public void TurnRight(double speed)
	{
		
	}
	
}
