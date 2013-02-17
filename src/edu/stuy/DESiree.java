/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.stuy;

import edu.stuy.subsystems.*;
import edu.stuy.util.Gamepad;
import edu.stuy.util.ThreeLaws;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class DESiree extends IterativeRobot implements ThreeLaws {
    /* SUBSYSTEMS */
    Drivetrain drivetrain;
    Acquirer acquirer;
    Conveyor conveyor;
    Lights lights;
    Shooter shooter;
    Tilter tilter;
    Climber climber;
    
    /* CONTROLLERS */
    Gamepad driverPad;
    Gamepad operatorPad;
    Joystick climberStick;
    
    SendableChooser autonChooser;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        /* SUBSYSTEMS */
        drivetrain = Drivetrain.getInstance();
        acquirer = Acquirer.getInstance();
        conveyor = Conveyor.getInstance();
        lights = Lights.getInstance();
        shooter = Shooter.getInstance();
        tilter = Tilter.getInstance();
        climber = Climber.getInstance();

        /* CONTROLLERS */
        driverPad = new Gamepad(Constants.DRIVER_PAD_PORT);
        operatorPad = new Gamepad(Constants.OPERATOR_PAD_PORT);
        climberStick = new Joystick(Constants.CLIMBER_STICK_PORT);
        
        /* AUTON SENDABLECHOOSER */
        autonChooser.addDefault("1 - Shoot 2 from front of pyramid", Integer.valueOf(1));
        autonChooser.addObject("2 - Shoot 3 from back of pyramid, spin 180", Integer.valueOf(2));
        autonChooser.addObject("3 - Shoot 2 from front of pyramid, acquire 2, shoot 2, spin 180", Integer.valueOf(3));
        autonChooser.addObject("4 - Shoot 2 from front of pyramid, acquire 4, shoot 4, spin 180", Integer.valueOf(4));
        autonChooser.addObject("5 - Shoot 3 from back of pyramid, acquire 4, shoot 4", Integer.valueOf(5));
        autonChooser.addObject("6 - Do nothing", Integer.valueOf(6));
        autonChooser.addObject("7 - Routine 4 without 180, drive to center of field", Integer.valueOf(7));
        autonChooser.addObject("8 - Routine 3 without 180, drive to center of field", Integer.valueOf(8));
    }

    public void autonomousInit() {
        Autonomous.run(((Integer)autonChooser.getSelected()).intValue());
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        shooter.runPistonLogic();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drivetrain.tankDrive(driverPad);
        
        acquirer.manualAcquirerControl(operatorPad);
        conveyor.manualConveyorControl(operatorPad);
        shooter.manualShooterControl(operatorPad);
        tilter.manualTilterControl(operatorPad);
        climber.manualClimberControl(climberStick);
        
        shooter.runPistonLogic();
        lights.runLogic(operatorPad);
        
        SmartDashboard.putBoolean("Acquirer sensor",conveyor.isBottomDiscDetected());
        SmartDashboard.putBoolean("Hopper empty",shooter.isHopperNotEmpty());
        SmartDashboard.putNumber("Gyro angle",drivetrain.getAngle());
        SmartDashboard.putNumber("Left encoder",drivetrain.getLeftEnc());
        SmartDashboard.putNumber("Right encoder",drivetrain.getRightEnc());
        SmartDashboard.putNumber("Acceleromter",tilter.getInstantAngle());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }

    public void doNoHarm() {
    }

    public void obeyOrders() {
    }

    public void protectSelf() {
    }
}
