package org.usfirst.frc.team1289.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	 private static DigitalInput _armLimitSwitch; 			// DIG 4
	 private static Solenoid _armPneumaticHorizontal;				// PNU 3
	 private static Solenoid _armPneumaticVertical;				// PNU 1
	 
	 public static void init() 
	 {
	     _armLimitSwitch = new DigitalInput(4);
	     LiveWindow.addSensor("Arm", "Limit Switch 1", _armLimitSwitch);
	     
	     _armPneumaticHorizontal = new Solenoid(0, 4);
	     LiveWindow.addActuator("Arm", "Pneumatic Horizontal", _armPneumaticHorizontal);
	     
	     _armPneumaticVertical = new Solenoid(0, 7);
	     LiveWindow.addActuator("Arm", "Pneumatic Vertical", _armPneumaticVertical);
		 }
	 
	 public DigitalInput GetArmLimitSwitch()
	 {
		 return _armLimitSwitch;
	 }
	 public Solenoid GetArmPneumaticHorizontal()
	 {
		 return _armPneumaticHorizontal;
	 }
	 public Solenoid GetArmPneumaticVertical()
	 {
		 return _armPneumaticHorizontal;
	 }
}
