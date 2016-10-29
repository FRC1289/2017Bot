package org.usfirst.frc.team1289.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import org.usfirst.frc.team1289.robot.RobotMap;

public class DriveTrain {
	private org.usfirst.frc.team1289.robot.RobotMap _ioMap;
	private RobotDrive _driveTrain;
	
	public DriveTrain(RobotMap ioMap)
	{
		this._driveTrain = _ioMap.GetRobotDrive();	
	}

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
}
