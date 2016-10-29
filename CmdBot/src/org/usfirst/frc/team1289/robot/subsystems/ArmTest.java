package org.usfirst.frc.team1289.robot.subsystems;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.usfirst.frc.team1289.robot.RobotMap;
import org.usfirst.frc.team1289.robot.subsystems.Arm;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.HLUsageReporting;
import edu.wpi.first.wpilibj.HLUsageReporting.Null;

public class ArmTest {

	private RobotMap _mockIoMap;
	private DigitalInput _mockDigitalInput;
	private Solenoid _mockPneumaticHorizontal;
	private Solenoid _mockPneumaticVertical;
	private Arm _arm;
	
	@Before
	public void setUp() throws Exception {
		// Needed to create what appears to be a singleton used when instantiating a subsystem entity
		HLUsageReporting _reporting = new HLUsageReporting();
		Null _nullReporting = new Null();
		_reporting.SetImplementation(_nullReporting);
		
		_mockIoMap = mock(RobotMap.class);
		_mockDigitalInput = mock(DigitalInput.class);
		_mockPneumaticHorizontal = mock(Solenoid.class);
		_mockPneumaticVertical = mock(Solenoid.class);
		
		when(_mockIoMap.GetArmLimitSwitch()).thenReturn(_mockDigitalInput);
		when(_mockIoMap.GetArmPneumaticHorizontal()).thenReturn(_mockPneumaticHorizontal);
		when(_mockIoMap.GetArmPneumaticVertical()).thenReturn(_mockPneumaticVertical);
		 
		_arm = new Arm(_mockIoMap);
	}

	@Test
	public void GetSwitchStateReturnsTrueWhenLimitSwitchIsOn() {
		when(_mockDigitalInput.get()).thenReturn(true);
		assertTrue(_arm.GetSwitchState());
	}
	
	@Test
	public void GetSwitchStateReturnsFalseWhenLimitSwitchIsOff() {
		when(_mockDigitalInput.get()).thenReturn(false);
		assertFalse(_arm.GetSwitchState());
	}
	
	@Test
	public void SetArmVerticalStateToOffSetsOffState() {
		_arm.MoveVertical(Arm.ArmState.OFF);
		assertEquals(_arm.GetVerticalState(), Arm.ArmState.OFF);
	}
	
	@Test
	public void SetArmVerticalStateToOffSetsVerticalSolenoidToFalse() {
		_arm.MoveVertical(Arm.ArmState.OFF);
		verify(_mockPneumaticVertical).set(false);
	}
	
	@Test
	public void SetArmVerticalStateToOnSetsVerticalSolenoidToTrue() {
		_arm.MoveVertical(Arm.ArmState.ON);
		verify(_mockPneumaticVertical).set(true);
	}
	
	@Test
	public void SetArmVerticalStateToOnSetsOnState() {
		_arm.MoveVertical(Arm.ArmState.ON);
		assertEquals(_arm.GetVerticalState(), Arm.ArmState.ON);
	}
	
	@Test
	public void SetArmHotizontalStateToOffSetsOffState() {
		_arm.MoveHorizontal(Arm.ArmState.OFF);
		assertEquals(_arm.GetHorizontalState(), Arm.ArmState.OFF);
	}
	
	@Test
	public void SetArmHorizontalStateToOnSetsOnState() {
		_arm.MoveHorizontal(Arm.ArmState.ON);
		assertEquals(_arm.GetHorizontalState(), Arm.ArmState.ON);
	}

	@Test
	public void SetArmHorizontalStateToOffSetsHorizontalSolenoidToFalse() {
		_arm.MoveHorizontal(Arm.ArmState.OFF);
		verify(_mockPneumaticHorizontal).set(false);
	}
	
	@Test
	public void SetArmHorizontalStateToOnSetsHorizontalSolenoidToTrue() {
		_arm.MoveHorizontal(Arm.ArmState.ON);
		verify(_mockPneumaticHorizontal).set(true);
	}
}
