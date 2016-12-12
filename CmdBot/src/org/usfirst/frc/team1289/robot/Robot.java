
package org.usfirst.frc.team1289.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.usfirst.frc.team1289.robot.commands.*;
import org.usfirst.frc.team1289.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//private Arm _arm;
	private DriveTrain _driveTrain;
	private OperatorInterface _operatorInterface;
	private RobotMap _ioMap;
	public SendableChooser _autoChooser;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
	
	public Robot()
	{
		this._ioMap = new RobotMap();
	}
	
    public void robotInit()
    {
    	_ioMap.init();
    	
    	//_arm = new Arm(_ioMap);
    	
    	_driveTrain = new DriveTrain(_ioMap);
    	_operatorInterface = new OperatorInterface(_ioMap);
    	
    	_autoChooser = new SendableChooser();
    	_autoChooser.addDefault("ShortForward", new DriveViaQuad(_driveTrain, 0.1, 12.0));
    	_autoChooser.addObject("LongForward", new DriveViaQuad(_driveTrain, 0.5, 96.0));
    	_autoChooser.addObject("StickDrive", new DriveViaStick(_driveTrain, _operatorInterface));
    	SmartDashboard.putData("Auto Mode Chooser", _autoChooser);
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	
    	Command autonomousCommand = (Command) _autoChooser.getSelected();
    	if (autonomousCommand != null)
    		autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        SmartDashboard.putNumber("Left Encoder Count", _driveTrain.GetLeftEncoderCount());
        SmartDashboard.putNumber("Right Encoder Count", _driveTrain.GetRightEncoderCount());
        
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        //if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
 
}
