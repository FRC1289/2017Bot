package org.usfirst.frc.team1289.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1289.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1289.robot.commands.DriveViaQuad;

public class DriveViaQuadTests {
	private DriveTrain _mockDriveTrain;
	private DriveViaQuad _driveViaQuad;
	private double _speed, _distance, _encoderPulseDistance;
	
	@Before
	public void setUp() throws Exception {
		_mockDriveTrain = mock(DriveTrain.class);
		
		_speed = 0.7;
		_distance = 12.5;

		_driveViaQuad = new DriveViaQuad(_mockDriveTrain, _speed, _distance);
	}
	
	@Test
	public void InitializeResetsDriveTrain() {
		_driveViaQuad.initialize();
		verify(_mockDriveTrain).Reset();
	}
	
	@Test
	public void InitializeSetsDriveTrainSpeed() {
		_driveViaQuad.initialize();
		verify(_mockDriveTrain).Initialize(0.7);
	}
	
	@Test
	public void ExecuteCallsMoveForward() {
		_driveViaQuad.execute();
		verify(_mockDriveTrain).MoveForward();
	}
	
	@Test
	public void IsFinishedReturnsFalseWhenDistanceTravelledIsLessThanTheDistanceSetInConstructor() {
		when(_mockDriveTrain.GetLeftEncoderCount()).thenReturn(160);
		when(_mockDriveTrain.GetRightEncoderCount()).thenReturn(160);
		when(_mockDriveTrain.GetEncoderPulseDistance()).thenReturn(0.0741765);
		assertFalse(_driveViaQuad.isFinished());
	}
	
	@Test
	public void IsFinishedReturnsTrueWhenDistanceTravelledIsMoreThanTheDistanceSetInConstructor() {
		when(_mockDriveTrain.GetLeftEncoderCount()).thenReturn(260);
		when(_mockDriveTrain.GetRightEncoderCount()).thenReturn(260);
		when(_mockDriveTrain.GetEncoderPulseDistance()).thenReturn(0.0741765);
		assertTrue(_driveViaQuad.isFinished());
	}
	
	@Test
	public void EndResetsTheDriveTrain()
	{
		_driveViaQuad.end();
		verify(_mockDriveTrain).Reset();
	}
	
	@Test
	public void InterruptedCallsDriveTrainStop()
	{
		_driveViaQuad.interrupted();
		verify(_mockDriveTrain).Stop();
	}

	
}
