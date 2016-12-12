package org.usfirst.frc.team1289.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;

import org.usfirst.frc.team1289.robot.RobotMap;

public class DriveTrain {
	private org.usfirst.frc.team1289.robot.RobotMap _ioMap;
	private RobotDrive _driveTrain;
	private Encoder _leftEncoder;
	private Encoder _rightEncoder;
	private double _speed, _encoderPulseDistance;
	
	public DriveTrain(RobotMap ioMap)
	{
		this._ioMap = ioMap;
		this._driveTrain = _ioMap.GetRobotDrive();
		this._leftEncoder = _ioMap.GetDriveTrainLeftQuadEncoder();
		this._rightEncoder = _ioMap.GetDriveTrainRightQuadEncoder();
		this._encoderPulseDistance = _ioMap.GetDistancerPerEncoderPulse();
	}

	public void Reset() {
		_driveTrain.stopMotor();
		_driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, false);
		_driveTrain.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
		_leftEncoder.reset();
		_rightEncoder.reset();
		_leftEncoder.setReverseDirection(false);
		_rightEncoder.setReverseDirection(true);
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
		_driveTrain.setLeftRightMotorOutputs(_speed, _speed);
	}
	
	public void MoveBackward()
	{
		_driveTrain.setLeftRightMotorOutputs(-_speed, -_speed);
	}
	
	public void Move()
	{
		_driveTrain.arcadeDrive(_ioMap.GetJoystick());
	}
	
	public void Stop()
	{
		_driveTrain.stopMotor();
	}
	
}
