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
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class DESiree extends IterativeRobot implements ThreeLaws {
    public static DESiree instance;
    
    /* SUBSYSTEMS */
    Drivetrain drivetrain;
    Acquirer acquirer;
    Conveyor conveyor;
    Lights lights;
    Shooter shooter;
    Tilter tilter;
    Wall wall;
    Climber climber;
    
    /* CONTROLLERS */
    Gamepad driverPad;
    Gamepad operatorPad;
    Joystick climberStick;
    
    SendableChooser autonChooser;

    public static DESiree getInstance() {
        return instance;
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        instance = this;
        /* SUBSYSTEMS */
        drivetrain = Drivetrain.getInstance();
        acquirer = Acquirer.getInstance();
        conveyor = Conveyor.getInstance();
        lights = Lights.getInstance();
        shooter = Shooter.getInstance();
        tilter = Tilter.getInstance();
        climber = Climber.getInstance();
        wall = Wall.getInstance();
        
        /* CONTROLLERS */
        driverPad = new Gamepad(Constants.DRIVER_PAD_PORT);
        operatorPad = new Gamepad(Constants.OPERATOR_PAD_PORT);
        climberStick = new Joystick(Constants.CLIMBER_STICK_PORT);
        
        /* AUTON SENDABLECHOOSER */
        autonChooser = new SendableChooser();
        autonChooser.addDefault("1 - Shoot 3 from back of pyramid", Integer.valueOf(1));
        autonChooser.addObject("2 - Shoot 3 from back of pyramid, drive to center of field", Integer.valueOf(2));
        autonChooser.addObject("3 - Lower conveyor", Integer.valueOf(3));
        autonChooser.addObject("4 - Do nothing", Integer.valueOf(4));
        autonChooser.addObject("5 - Shoot 2 from the front", Integer.valueOf(5));
        autonChooser.addObject("6 - Drivestraight to center", Integer.valueOf(6));
        autonChooser.addObject("7 - Shoot 3 from back of pyramid, drive partially to center of field", Integer.valueOf(7));
        SmartDashboard.putData("Autonomous routine", autonChooser);
        SmartDashboard.putNumber("Autonomous Delay",0.0);
    }

    public void autonomousInit() {
        resetAll();
        Tilter.getInstance().setInitialLeadscrewLength();
        double autonDelay = 0.0;
        try {
            autonDelay = SmartDashboard.getNumber("Autonomous Delay");
        }
        catch (TableKeyNotDefinedException e) {
            SmartDashboard.putNumber("Autonomous Delay",0.0);        
        }
        Thread autonPistonLogicThread = new Thread(new Runnable() {

            public void run() {
                while (isAutonomous() && isEnabled()) {
                    shooter.runPistonLogic();
                    if (shooter.isShooterRunning()) {
                        shooter.runShooterOut();
                    }
                    else {
                        shooter.stop();
                    }
                }
            }
            
        });
        autonPistonLogicThread.start();
        Autonomous.run(((Integer)autonChooser.getSelected()).intValue(),autonDelay);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    }
    
    public void teleopInit() {
        resetAll();
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
        climber.manualClimberControl(driverPad);
        wall.manualWallControl(operatorPad);
        
        shooter.runPistonLogic();
        lights.runLogic(operatorPad);
        
        SmartDashboard.putBoolean("Acquirer sensor",conveyor.isBottomDiscDetected());
        SmartDashboard.putBoolean("Hopper empty",shooter.isHopperNotEmpty());
        SmartDashboard.putNumber("Gyro angle",drivetrain.getAngle());
        SmartDashboard.putNumber("Left encoder",drivetrain.getLeftEnc());
        SmartDashboard.putNumber("Right encoder",drivetrain.getRightEnc());
        SmartDashboard.putNumber("Leadscrew encoder", tilter.getLeadscrewEncoderDistance());
        SmartDashboard.putBoolean("Tilter at upper bound", tilter.isAtUpperBound());
        SmartDashboard.putBoolean("Tilter at lower bound", tilter.isAtLowerBound());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    }
    
    /**
     * Reset the states of all subsystems.
     */
    public void resetAll() {
        drivetrain.reset();
        acquirer.reset();
        conveyor.reset();
        lights.reset();
        shooter.reset();
        tilter.reset();
        climber.reset();
    }

    public void doNoHarm() {
    }

    public void obeyOrders() {
    }

    public void protectSelf() {
    }
}
