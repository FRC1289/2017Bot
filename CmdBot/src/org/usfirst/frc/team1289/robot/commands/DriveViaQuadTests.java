package org.usfirst.frc.team1289.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1289.robot.subsystems.DriveTrain;

public class DriveViaQuadTests {

	
	private DriveTrain _mockDriveTrain;
	private DriveViaQuad _driveViaQuad;
	
	@Before
	public void setUp() throws Exception {
		_mockDriveTrain = mock(DriveTrain.class);
		
		_driveViaQuad = new DriveViaQuad(_mockDriveTrain);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
