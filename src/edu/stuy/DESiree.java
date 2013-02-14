/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.stuy;

import edu.stuy.subsystems.*;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class DESiree extends IterativeRobot {
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
    }

    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        drivetrain.tankDrive(driverPad);
        
        conveyor.conveyAutomatic();
        
        tilter.manualTilterControl(operatorPad);
        acquirer.manualAcquirerControl(operatorPad);
        shooter.manualShooterControl(operatorPad);
        climber.manualClimberControl(climberStick);
        
        lights.runLogic(operatorPad);
        
        SmartDashboard.putNumber("Gyro angle:", drivetrain.getAngle());
        SmartDashboard.putNumber("Accel angle Instant:", tilter.getInstantAngle());
        SmartDashboard.putNumber("Accel angle Average:", tilter.getAbsoluteAngle());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
}
