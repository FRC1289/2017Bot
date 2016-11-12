package org.usfirst.frc.team1289.robot.subsystems;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.usfirst.frc.team1289.robot.RobotMap;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.HLUsageReporting.Null;

public class DriveTrainTests {

	private RobotMap _mockIoMap;
	private RobotDrive _mockRobotDrive;
	private SpeedController _mockDriveTrainMotorLeft;
	private SpeedController _mockDriveTrainMotorRight;
	private Encoder _mockDriveTrainEncoderLeft;
	private Encoder _mockDriveTrainEncoderRight;
	private DriveTrain _driveTrain;
	 
	@Before
	public void setUp() throws Exception {
		// Needed to create what appears to be a singleton used when instantiating a subsystem entity
		HLUsageReporting _reporting = new HLUsageReporting();
		Null _nullReporting = new Null();
		_reporting.SetImplementation(_nullReporting);
		
		_mockIoMap = mock(RobotMap.class);
		_mockRobotDrive = mock(RobotDrive.class);
		_mockDriveTrainMotorLeft = mock(SpeedController.class);
		_mockDriveTrainMotorRight = mock(SpeedController.class);
		_mockDriveTrainEncoderLeft = mock(Encoder.class);
		_mockDriveTrainEncoderRight = mock(Encoder.class);
		
		when(_mockIoMap.GetRobotDrive()).thenReturn(_mockRobotDrive);
		when(_mockIoMap.GetDriveTrainLeftMotor()).thenReturn(_mockDriveTrainMotorLeft);
		when(_mockIoMap.GetDriveTrainRightMotor()).thenReturn(_mockDriveTrainMotorRight);
		when(_mockIoMap.GetDriveTrainLeftQuadEncoder()).thenReturn(_mockDriveTrainEncoderLeft);
		when(_mockIoMap.GetDriveTrainRightQuadEncoder()).thenReturn(_mockDriveTrainEncoderRight);

		_driveTrain = new DriveTrain(_mockIoMap);
	}

	@Test
	public void ResetResetsLeftSideDevicesInCorrectOrder()
	{
		InOrder inOrder = inOrder(_mockDriveTrainMotorLeft, _mockDriveTrainEncoderLeft);
		
		_driveTrain.Reset();
		inOrder.verify(_mockDriveTrainMotorLeft).stopMotor();
		inOrder.verify(_mockDriveTrainEncoderLeft).reset();
		inOrder.verify(_mockDriveTrainMotorLeft).setInverted(false);
		inOrder.verify(_mockDriveTrainEncoderLeft).setReverseDirection(false);
	}
	
	@Test
	public void ResetResetsRightSideDevicesInCorrectOrder()
	{
		InOrder inOrder = inOrder(_mockDriveTrainMotorRight, _mockDriveTrainEncoderRight);
		
		_driveTrain.Reset();
		inOrder.verify(_mockDriveTrainMotorRight).stopMotor();
		inOrder.verify(_mockDriveTrainEncoderRight).reset();
		inOrder.verify(_mockDriveTrainMotorRight).setInverted(true);
		inOrder.verify(_mockDriveTrainEncoderRight).setReverseDirection(true);
	}
	@Test
	public void InitializeSetsMotorSpeedToAGivenValue()
	{
		_driveTrain.Initialize(1.0);
		assertEquals(1.0, _driveTrain.GetSpeed(), 0.001);
	}
	
	@Test
	public void InitializeSetsMotorSpeedToAnotherGivenValue()
	{
		_driveTrain.Initialize(-1.0);
		assertEquals(-1.0, _driveTrain.GetSpeed(), 0.001);
	}

	@Test 
	public void GetLeftEncoderCountReturnsCorrectCount() 
	{
		when(_mockDriveTrainEncoderLeft.get()).thenReturn(25);
		assertEquals(25, _driveTrain.GetLeftEncoderCount());
	}
	
	@Test 
	public void GetRightEncoderCountReturnsCorrectCount() 
	{
		when(_mockDriveTrainEncoderRight.get()).thenReturn(10);
		assertEquals(10, _driveTrain.GetRightEncoderCount());
	}
	
	@Test
	public void  GetEncoderPulseDistanceReturnsCorrectValue()
	{
		when(_mockIoMap.GetDistancerPerEncoderPulse()).thenReturn(0.0741765);
		assertEquals(0.0741765, _driveTrain.GetEncoderPulseDistance(), 0.0000001);
	}
	
	@Test
	public void MoveForwardSetsCorrectMotorInversion() 
	{
		_driveTrain.MoveForward();
		verify(_mockDriveTrainMotorLeft).setInverted(false);
		verify(_mockDriveTrainMotorRight).setInverted(true);
	}
	
	@Test
	public void MoveForwardCallsMotorSetWithSpeedOf0point5() 
	{
		_driveTrain.Initialize(0.5);
		_driveTrain.MoveForward();
		verify(_mockDriveTrainMotorLeft).set(0.5);
		verify(_mockDriveTrainMotorRight).set(0.5);
	}
	
	@Test
	public void MoveForwardCallsMotorSetWithSpeedOf0point2 () 
	{
		_driveTrain.Initialize(0.2);
		_driveTrain.MoveForward();
		verify(_mockDriveTrainMotorLeft).set(0.2);
		verify(_mockDriveTrainMotorRight).set(0.2);
	}
	
	@Test
	public void MoveBackwardSetsCorrectMotorInversion() 
	{
		_driveTrain.MoveBackward();
		verify(_mockDriveTrainMotorLeft).setInverted(true);
		verify(_mockDriveTrainMotorRight).setInverted(false);
	}
	
	@Test
	public void MoveBackwardCallsMotorSetWithSpeedOf0point4() 
	{
		_driveTrain.Initialize(0.4);
		_driveTrain.MoveBackward();
		verify(_mockDriveTrainMotorLeft).set(0.4);
		verify(_mockDriveTrainMotorRight).set(0.4);
	}
	
	@Test
	public void MoveBackwardCallsMotorSetWithSpeedOf0point9 () 
	{
		_driveTrain.Initialize(0.9);
		_driveTrain.MoveBackward();
		verify(_mockDriveTrainMotorLeft).set(0.9);
		verify(_mockDriveTrainMotorRight).set(0.9);
	}
	
	@Test
	public void StopStopsMotors()
	{
		_driveTrain.Stop();
		verify(_mockDriveTrainMotorLeft).stopMotor();
		verify(_mockDriveTrainMotorRight).stopMotor();
	}
}
