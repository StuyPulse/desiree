/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy;

import edu.stuy.subsystems.Acquirer;
import edu.stuy.subsystems.Conveyor;
import edu.stuy.subsystems.Drivetrain;
import edu.stuy.subsystems.Lights;
import edu.stuy.subsystems.Shooter;
import edu.stuy.subsystems.Tilter;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is the location of the autonomous routines.
 * We assume that we always begin touching the front edge of the pyramid.
 * This gives us two discs.
 * @author Eric, R4D4
 */
public class Autonomous {
    
    static double autonDelay;
    
    public static final int AUTON_FIRING_AMOUNT_LONG = 5;
    public static final int AUTON_FIRING_AMOUNT_SHORT = 3;
    
    public static void run(int setting, double delay) {
        autonDelay = delay;
        switch (setting) {
            case 1: 
                auton1();
                break;
            case 2: 
                auton2();
                break;
            case 3:
                auton3();
                break;
            case 4:
                auton4();
                break;
            case 5:
                auton5();
                break;
            case 6:
                auton6();
                break;
            case 7:
                auton7();
                break;
            case 8:
                auton8();
                break;
            case 9:
                auton9();
                break;
            case 10:
                auton10();
                break;
            case 11:
                auton11();
                break;
        }
    }
    
    public static void runTilterToBottom() {
        Tilter tilter = Tilter.getInstance();
        while (DESiree.getInstance().isAutonomous() && !tilter.isAtLowerBound()) {
            tilter.setLeadscrewMotor(Tilter.DOWN_VAL);
        }
        tilter.stopLeadscrewMotor();
    }
    
    public static void CVAim() {
        Tilter tilter = Tilter.getInstance();
        while(DESiree.getInstance().isAutonomous() && Tilter.getInstance().getCVRelativeAngle() == 694) {
            tilter.setLeadscrewMotor(Tilter.DOWN_VAL);
        }
        tilter.stopLeadscrewMotor();
        tilter.runTilterToAngle(tilter.getAngle() + tilter.getCVRelativeAngle());
    }
    
    /**
     * Start in front of back bar of pyramid. (Can be used in a variety of positions).
     */
    public static void auton1() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Lights.getInstance().setDirectionLight(Lights.DIRECTION_LIGHT_INTENSITY);
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty(AUTON_FIRING_AMOUNT_LONG);
        Shooter.getInstance().autonStop();
        Lights.getInstance().setDirectionLight(0);
    }
    
    /**
     * Same as auton 1, only with a backup in the end.
     */
    public static void auton2() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Lights.getInstance().setDirectionLight(Lights.DIRECTION_LIGHT_INTENSITY);
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty(AUTON_FIRING_AMOUNT_LONG);
        Shooter.getInstance().autonStop();
        Lights.getInstance().setDirectionLight(0);
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_BACK_OF_PYRAMID);
    }
    
    /**
     * Lower conveyor.
     */
    public static void auton3() {
        Lights.getInstance().setDirectionLight(Lights.DIRECTION_LIGHT_INTENSITY);
        runTilterToBottom();
        Lights.getInstance().setDirectionLight(0);
    }
    
    /**
     * Do nothing.
     */
    public static void auton4() {
    }
    
    /**
     * Start in front of pyramid in any position. 
     */
    public static void auton5() {
        Shooter.getInstance().autonShoot();
        Tilter.getInstance().runTilterToAngle(Constants.FRONT_OF_PYRAMID_ANGLE);
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty(AUTON_FIRING_AMOUNT_LONG);
        Shooter.getInstance().autonStop();
    }
    
    public static void auton6() {
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_BACK_OF_PYRAMID);
    }
    
    /**
     * Auton 2 with partial drive back.
     */
    public static void auton7() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Lights.getInstance().setDirectionLight(Lights.DIRECTION_LIGHT_INTENSITY);
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty(AUTON_FIRING_AMOUNT_LONG);
        Shooter.getInstance().autonStop();
        Lights.getInstance().setDirectionLight(0);
        Drivetrain.getInstance().driveStraightInches(Constants.PARTIAL_DRIVE_TO_CENTER_DISTANCE);
    }
    
    /**
     * Lower leadscrew, fire three, drive to center fast.
     */
    public static void auton8() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Lights.getInstance().setDirectionLight(Lights.DIRECTION_LIGHT_INTENSITY);
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty(AUTON_FIRING_AMOUNT_SHORT);
        Shooter.getInstance().autonStop();
        Lights.getInstance().setDirectionLight(0);
        Drivetrain.getInstance().driveFast(Constants.CENTER_TO_BACK_OF_PYRAMID);
    }
    
    /**
     * Auton 1 from the side, turning and driving fast to center. Dead reckons with time.
     */
    public static void auton9() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Lights.getInstance().setDirectionLight(Lights.DIRECTION_LIGHT_INTENSITY);
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty(AUTON_FIRING_AMOUNT_LONG);
        Shooter.getInstance().autonStop();
        Lights.getInstance().setDirectionLight(0);
        Drivetrain.getInstance().spin90T();
        Drivetrain.getInstance().driveFast(Constants.CENTER_TO_BACK_OF_PYRAMID);
    }
    
    /**
     * Auton 9, but with dead reckoning based on distance.
     */
    public static void auton10() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Lights.getInstance().setDirectionLight(Lights.DIRECTION_LIGHT_INTENSITY);
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty(AUTON_FIRING_AMOUNT_LONG);
        Shooter.getInstance().autonStop();
        Lights.getInstance().setDirectionLight(0);
        Drivetrain.getInstance().spin90D();
        Drivetrain.getInstance().driveFast(Constants.CENTER_TO_BACK_OF_PYRAMID);
    }
    
    /**
     * Auton 8, but doesn't reach centre.
     */
    public static void auton11() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Lights.getInstance().setDirectionLight(Lights.DIRECTION_LIGHT_INTENSITY);
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty(AUTON_FIRING_AMOUNT_SHORT);
        Shooter.getInstance().autonStop();
        Lights.getInstance().setDirectionLight(0);
        Drivetrain.getInstance().driveFast(Constants.CENTER_TO_BACK_OF_PYRAMID * (2/3));
    }
}
