package org.usfirst.frc.team1289.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1289.robot.RobotMap;
import org.usfirst.frc.team1289.robot.subsystems.Arm;
import org.usfirst.frc.team1289.robot.subsystems.Arm.ArmState;


public class ArmDownCommandTests {
	private Arm _mockArm;
	private ArmDownCommand _armDownCommand;
	
	@Before
	public void setUp() throws Exception {
		_mockArm = mock(Arm.class);
		
		_armDownCommand = new ArmDownCommand(_mockArm);
	}
	
	@Test
	public void InitializeCallsArmMoveVerticalOff() {
		_armDownCommand.initialize();
		verify(_mockArm).MoveVertical(Arm.ArmState.OFF);
	}
	
	@Test
	public void IsFinishedReturnsFalse() {
		assertFalse(_armDownCommand.isFinished());
	}

}
