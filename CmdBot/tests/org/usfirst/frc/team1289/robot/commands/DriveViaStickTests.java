package org.usfirst.frc.team1289.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.usfirst.frc.team1289.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1289.robot.subsystems.OperatorInterface;
import org.usfirst.frc.team1289.robot.commands.DriveViaStick;

public class DriveViaStickTests {
	private DriveTrain _mockDriveTrain;
	private DriveViaStick _driveViaStick;
	private OperatorInterface _mockOperatorInterface;
	
	@Before
	public void setUp() throws Exception {
		_mockDriveTrain = mock(DriveTrain.class);
		_mockOperatorInterface = mock(OperatorInterface.class);
		
		_driveViaStick = new DriveViaStick(_mockDriveTrain, _mockOperatorInterface);
	}
	
	@Test
	public void InitializeResetsDriveTrain() {
		_driveViaStick.initialize();
		verify(_mockDriveTrain).Reset();
	}
	
	@Test
	public void InitializeSetsDriveTrainSpeed() {
		_driveViaStick.initialize();
		verify(_mockDriveTrain).Initialize(0.0);
	}
	
	@Test
	public void EndResetsTheDriveTrain()
	{
		_driveViaStick.end();
		verify(_mockDriveTrain).Reset();
	}
	
	@Test
	public void EndStopsTheDriveTrain()
	{
		_driveViaStick.end();
		verify(_mockDriveTrain).Stop();
	}
	
	@Test
	public void EndStopsAndResetsInCorrectOrder()
	{
		InOrder inOrder = inOrder(_mockDriveTrain);
		
		_driveViaStick.end();
		inOrder.verify(_mockDriveTrain).Stop();
		inOrder.verify(_mockDriveTrain).Reset();
	}
	
	@Test
	public void InterruptedCallsDriveTrainStop()
	{
		_driveViaStick.interrupted();
		verify(_mockDriveTrain).Stop();
	}
	
	@Test
	public void ExecuteCallsDriveTrainMove()
	{
		_driveViaStick.execute();
		verify(_mockDriveTrain).Move();
	}

	
}
