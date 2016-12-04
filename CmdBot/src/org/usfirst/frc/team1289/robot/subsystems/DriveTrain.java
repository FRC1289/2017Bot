package org.usfirst.frc.team1289.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team1289.robot.RobotMap;

public class DriveTrain {
	private org.usfirst.frc.team1289.robot.RobotMap _ioMap;
	private RobotDrive _driveTrain;
	private SpeedController _leftMotor;
	private SpeedController _rightMotor;
	private Encoder _leftEncoder;
	private Encoder _rightEncoder;
	private double _speed, _encoderPulseDistance;
	
	public DriveTrain(RobotMap ioMap)
	{
		this._ioMap = ioMap;
		this._leftMotor = _ioMap.GetDriveTrainLeftMotor();
		this._rightMotor = _ioMap.GetDriveTrainRightMotor();			
		this._leftEncoder = _ioMap.GetDriveTrainLeftQuadEncoder();
		this._rightEncoder = _ioMap.GetDriveTrainRightQuadEncoder();
		this._encoderPulseDistance = _ioMap.GetDistancerPerEncoderPulse();
	}

	public void Reset() {
		_leftMotor.stopMotor();
		_leftEncoder.reset();
		_leftMotor.setInverted(true);
		_leftEncoder.setReverseDirection(true);
		
		_rightMotor.stopMotor();
		_rightEncoder.reset();
		_rightMotor.setInverted(false);
		_rightEncoder.setReverseDirection(false);
	}
	
	public void Initialize(double speed) 
	{
		_speed = speed;
	}
	
	public double GetSpeed()
	{
		return _speed;
	}
	
	public int GetLeftEncoderCount()
	{
		return _leftEncoder.get();
	}
	
	public int GetRightEncoderCount()
	{
		return _rightEncoder.get();
	}
	
	public double GetEncoderPulseDistance()
	{
		return _ioMap.GetDistancerPerEncoderPulse();
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public void MoveForward()
	{
		_leftMotor.setInverted(true);
		_rightMotor.setInverted(false);
		_leftMotor.set(_speed);
		_rightMotor.set(_speed);
	}
	
	public void MoveBackward()
	{
		_leftMotor.setInverted(false);
		_rightMotor.setInverted(true);
		_leftMotor.set(_speed);
		_rightMotor.set(_speed);
	}
	
	public void Stop()
	{
		_leftMotor.stopMotor();
		_rightMotor.stopMotor();
	}
	
}
