package org.usfirst.frc.team1289.robot.subsystems;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
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
	private Encoder _mockDriveTrainLeftQuadEncoder;
	private Encoder _mockDriveTrainRightQuadEncoder;
	 
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
		_mockDriveTrainLeftQuadEncoder = mock(Encoder.class);
		_mockDriveTrainRightQuadEncoder = mock(Encoder.class);
		
		when(_mockIoMap.GetRobotDrive()).thenReturn(_mockRobotDrive);
		when(_mockIoMap.GetDriveTrainLeftMotor()).thenReturn(_mockDriveTrainMotorLeft);
		when(_mockIoMap.GetDriveTrainRightMotor()).thenReturn(_mockDriveTrainMotorRight);
		when(_mockIoMap.GetDriveTrainLeftQuadEncoder()).thenReturn(_mockDriveTrainLeftQuadEncoder);
		when(_mockIoMap.GetDriveTrainRightQuadEncoder()).thenReturn(_mockDriveTrainRightQuadEncoder);

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	// tests for:
	// move robot
	// get quad

}
