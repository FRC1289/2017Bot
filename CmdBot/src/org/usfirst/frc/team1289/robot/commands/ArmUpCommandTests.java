package org.usfirst.frc.team1289.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1289.robot.RobotMap;
import org.usfirst.frc.team1289.robot.subsystems.Arm;
import org.usfirst.frc.team1289.robot.subsystems.Arm.ArmState;

public class ArmUpCommandTests {
	private Arm _mockArm;
	private ArmUpCommand _armUpCommand;
	
	@Before
	public void setUp() throws Exception {
		_mockArm = mock(Arm.class);
		
		_armUpCommand = new ArmUpCommand(_mockArm);
	}
	
	@Test
	public void IntializeCallsMoveVerticalOn() {
		_armUpCommand.initialize();
		verify(_mockArm).MoveVertical(Arm.ArmState.ON);
	}
	
	@Test
	public void EndCallsMoveVerticalOff() {
		_armUpCommand.end();
		verify(_mockArm).MoveVertical(Arm.ArmState.OFF);
	}
	
	@Test
	public void IsFinishedReturnsTrueWhenArmIsAtLimit() {
		when(_mockArm.GetSwitchState()).thenReturn(true);
		assertTrue(_armUpCommand.isFinished());
	}
	
	@Test
	public void IsFinishedReturnsFalseWhenArmIsNotAtLimit() {
		when(_mockArm.GetSwitchState()).thenReturn(false);
		assertFalse(_armUpCommand.isFinished());
	}
	
	@Test
	public void InterruptedCallsMoveVerticalOff() {
		_armUpCommand.interrupted();
		verify(_mockArm).MoveVertical(Arm.ArmState.OFF);
	}
}

