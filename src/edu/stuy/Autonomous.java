/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy;

import edu.stuy.subsystems.Acquirer;
import edu.stuy.subsystems.Conveyor;
import edu.stuy.subsystems.Drivetrain;
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
        tilter.runTilterToAngle(tilter.getLeadscrewBasedAngle() + tilter.getCVRelativeAngle());
    }
    
    /**
     * Start in front of back bar of pyramid. (Can be used in a variety of positions).
     */
    public static void auton1() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();
    }
    
    /**
     * Same as auton 1, only with a backup in the end.
     */
    public static void auton2() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_BACK_OF_PYRAMID);
    }
    
    /**
     * Lower conveyor.
     */
    public static void auton3() {
        runTilterToBottom();
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
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();
    }
    
    /**
     * Start in front of the middle of the pyramid.
     * Use CV to shoot 2. Go forward under pyramid and pick up 2. 
     * Drive backwards. Use CV to fire those two. 180 degree spin at end. 
     */
    public static void auton6() {
        Shooter.getInstance().autonShoot();
        CVAim();
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().driveStraightInches(-Constants.FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID);
        Shooter.getInstance().autonShoot();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();        
        Drivetrain.getInstance().spin180();
    }
    
    /**
     * Start in front of the middle of the pyramid.
     * Use CV to shoot 2. Move forward and acquire disks under pyramid and at center of field. 
     * Don't stop for pickup. After acquired, drive backwards to the front of pyramid. 
     * Stop running acquirer on the way back. Use CV to shoot the 4 acquired disks. Spin 180 at end.
     */ 
    public static void auton7() {
        Shooter.getInstance().autonShoot();
        CVAim();
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();        
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_FRONT_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().driveStraightInches(-Constants.CENTER_TO_FRONT_OF_PYRAMID);
        Shooter.getInstance().autonShoot();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();        
        Drivetrain.getInstance().spin180();
    }
    
    /**
     * Start at the back of the pyramid.
     * Shoot 3. Move forward and acquire disks at center of field. 
     * Return and shoot 4.
     * You might have 4 depending on how many people have left there from other robots.
     */
    public static void auton8() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_BACK_OF_PYRAMID);
        Drivetrain.getInstance().driveStraightInches(-Constants.CENTER_TO_BACK_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Shooter.getInstance().autonShoot();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();     
    }
   
    // DO NOT TEST THESE UNTIL COMPETITION. DRIVER PRACTICE FIRST!
    // DO NOT CROSS COMPLETELY INTO THE OTHER SIDE OF THE FIELD!
    // -------------------------------------------------------------------------
    /**
     * Same as 7 but NO 180 SPIN, move forward to center at end of auton.
     */
    public static void auton9() {
        Shooter.getInstance().autonShoot();
        CVAim();
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();     
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_FRONT_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().driveStraightInches(-Constants.CENTER_TO_FRONT_OF_PYRAMID);
        Shooter.getInstance().autonShoot();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();     
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_FRONT_OF_PYRAMID);
    }
    
    /**
     * Same as 6 but NO 180 SPIN, move forward to center of field. 
     */
    public static void auton10()  {
        Shooter.getInstance().autonShoot();
        CVAim();
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();    
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().driveStraightInches(-Constants.FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID);
        Shooter.getInstance().autonShoot();
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();    
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_FRONT_OF_PYRAMID);
    }
}
