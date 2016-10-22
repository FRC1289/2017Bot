// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1289.stronghold;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1289.stronghold.commands.*;
import org.usfirst.frc1289.stronghold.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    CameraServer server;
    Compressor OurCompress;
    
    float StartHeading;
    float TempStartHeading;
    
    public static OI oi;
    public static Arm arm;
    //public static Sallyport sallyport;
    public static BallHandler ballHandler;

    public static DriveTrain driveTrain;
    public static Camera cameraServer;

    // Used to get input from SmartDashboard
    // Will use to choose which autonomous program to run
    public static SendableChooser autoChooser;
    public static SendableChooser positionChooser;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
        
        arm = new Arm();
        //sallyport = new Sallyport();
        ballHandler = new BallHandler();
        
        driveTrain = new DriveTrain();
        cameraServer = new Camera();
        OurCompress = new Compressor();
        
        
        //server = CameraServer.getInstance();
        //server.setQuality(10);
        //server.setSize(2);
        

        //the camera name (ex "cam0") can be found through the roborio web interface
        //server.startAutomaticCapture("cam0");

    
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate the command used for the autonomous period

        autonomousCommand = new AutonomousCommand();

    	Robot.ballHandler.ballPneu(true);
    	Robot.arm.ArmUpDown(true);
    	
    	// Report what command is running
    	SmartDashboard.putData(Scheduler.getInstance());
    	

    	
    	//Setting up input from SmartDashboard for Autonomous code selection
    	autoChooser = new SendableChooser();
    	autoChooser.addDefault("Drive Forward Only", new DriveViaQuad(80,0.5));
    	autoChooser.addObject("Cross Low Bar", new AutoLowBar());
    	autoChooser.addObject("Cross Rough Terrain", new DriveViaQuad(250,1.0));
    	autoChooser.addObject("Cross Ramparts", new DriveViaQuad(250,1.0));
    	autoChooser.addObject("Cross Moat", new DriveViaQuad(250,1.0));
    	autoChooser.addObject("Cross Rock Wall", new DriveViaQuad(250,1.0));
    	positionChooser = new SendableChooser();
    	positionChooser.addDefault("Don't Care", 0);
    	positionChooser.addObject("Position 1 (Far Left)", 1);
    	positionChooser.addObject("Position 2", 2);
    	positionChooser.addObject("Position 3", 3);
    	positionChooser.addObject("Position 4", 4);
    	positionChooser.addObject("Position 5 (Far Right)", 5);
    	positionChooser.addObject("Defender", 6);
    	
    	SmartDashboard.putData("Autonomous Mode Action", autoChooser);
    	SmartDashboard.putData("Starting Position", positionChooser);
    	
        SmartDashboard.putData("Move Arm Up" , new ArmUp());
        SmartDashboard.putData("Arm Reset", new ArmReset());
        SmartDashboard.putData("Get Ball", new BallLoad());
        SmartDashboard.putData("Move Arm Down", new ArmDown());
        SmartDashboard.putData("Move Arm In", new ArmIn());
        SmartDashboard.putData("Move Arm Out", new ArmOut());
        SmartDashboard.putData("Ball Handler Down", new BallLower());
        SmartDashboard.putData("Shoot Ball", new BallShoot());
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	//Robot.sallyport.movesally(false);
    	Robot.arm.ArmUpDown(false);
    	Robot.ballHandler.ballPneu(true);

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
    	int StartPosition;
    	//Command AutonomousCMD
    	
        Robot.ballHandler.ballPneu(false);
        Robot.arm.ArmUpDown(true);
        //Set the compass heading here because this is the start of the match
        //If set before this the robot may be moved/adjusted after setting the initial robot heading.
        StartHeading = RobotMap.navx.getFusedHeading();
        
        //Get position of robot and autonomous command
        StartPosition = (int) positionChooser.getSelected();
        SmartDashboard.putNumber("Position Selection: ", StartPosition);
        
     
        //AutonomousCMD = (Command) autoChooser.getSelected();
      
        
        if (autonomousCommand != null) autonomousCommand.start();

    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putBoolean("Collision Detected: ", Robot.driveTrain.Jerk());
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.ballHandler.ballPneu(true);
        Robot.arm.ArmInOut(false);
        Robot.arm.ArmUpDown(true);
        //RobotMap.webcam.startCapture();
       
        //Read the options from the SmartDashboard
        Command autocmd;
        Integer position;
        
        autocmd = (Command) autoChooser.getSelected();
        position = (Integer) positionChooser.getSelected();
        
        SmartDashboard.putData("Auto Chooser",autocmd);
        SmartDashboard.putNumber("Position", position);
        // End of temporary code
        
    	// Set the temporary compass heading for testing purposes only.
    	TempStartHeading = RobotMap.navx.getFusedHeading();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putBoolean("Comp Not Connected", OurCompress.getCompressorNotConnectedFault());
    	//SmartDashboard.putBoolean("Comp Switch", OurCompress.getPressureSwitchValue());
    	//SmartDashboard.putBoolean("Comp Fault", OurCompress.getCompressorShortedFault());
    	//SmartDashboard.putNumber("Comp Current", OurCompress.getCompressorCurrent());
    	//SmartDashboard.putBoolean("Ball Switch", Robot.ballHandler.getSwitch());

    	//SmartDashboard.putNumber("Quad L", RobotMap.driveTrainQuadLeft.getDistance());
    	//SmartDashboard.putNumber("Quad R", RobotMap.driveTrainQuadRight.getDistance());
    	//SmartDashboard.putBoolean("Quad L dir", RobotMap.driveTrainQuadLeft.getDirection());
    	//SmartDashboard.putBoolean("Quad R dir", RobotMap.driveTrainQuadRight.getDirection());
    	SmartDashboard.putNumber("Start Value: ", TempStartHeading);
    	SmartDashboard.putNumber("Compass Heading: ", RobotMap.navx.getFusedHeading());
    	//SmartDashboard.putBoolean("Collision Detected: ", Robot.driveTrain.Jerk());
    	//SmartDashboard.putNumber("Ultra 1 Value", RobotMap.driveTrainUltra1.getValue());
    	SmartDashboard.putNumber("Light Sensore", Robot.ballHandler.getSwitch());

    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	
    	
        LiveWindow.run();
    }
}