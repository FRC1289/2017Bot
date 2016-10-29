package org.usfirst.frc.team1289.robot.commands;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1289.robot.RobotMap;
import org.usfirst.frc.team1289.robot.subsystems.Arm;
import org.usfirst.frc.team1289.robot.subsystems.Arm.ArmState;

public class ArmResetCommandTests {
	private Arm _mockArm;
	private ArmResetCommand _armResetCommand;
	
	@Before
	public void setUp() throws Exception {
		_mockArm = mock(Arm.class);
		
		_armResetCommand = new ArmResetCommand(_mockArm);
	}
	
	@Test
	public void ExecuteTurnsBothDirectionsOff() {
		_armResetCommand.execute();
		verify(_mockArm).MoveVertical(ArmState.OFF);
		verify(_mockArm).MoveHorizontal(ArmState.OFF);
	}
	
	@Test
	public void EndTurnsBothDirectionsOff() {
		_armResetCommand.end();
		verify(_mockArm).MoveVertical(ArmState.OFF);
		verify(_mockArm).MoveHorizontal(ArmState.OFF);
		
	}

}
