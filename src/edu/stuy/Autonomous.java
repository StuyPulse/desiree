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
        }
    }
    
    /**
     * Aims for a specified period of time. This is a blocking method.
     * @param angle in degirees
     */
    public static void autoAim(double angle) {
        Tilter.getInstance().setAbsoluteAngle(angle);
        Tilter.getInstance().enableAngleControl();
        Timer.delay(Constants.AUTO_AIM_TIMEOUT);
        Tilter.getInstance().disableAngleControl();
    }
    
    public static void runTilterToBottom() {
        Tilter tilter = Tilter.getInstance();
        while (!tilter.isAtLowerBound()) {
            tilter.setLeadscrewMotor(Tilter.DOWN_VAL);
        }
        tilter.stopLeadscrewMotor();
    }
    
    /**
     * Start in front of pyramid in any position. 
     */
    public static void auton1() {
        autoAim(Constants.FRONT_OF_PYRAMID_ANGLE);
        Timer.delay(autonDelay);
        shootUntilEmpty();
    }
      
    /**
     * Start in front of back bar of pyramid. (Can be used in a variety of positions).
     */
    public static void auton2() {
        Shooter.getInstance().autonShoot();
        runTilterToBottom();
        Timer.delay(autonDelay);
        Shooter.getInstance().fireAutoUntilEmpty();
        Shooter.getInstance().autonStop();
    } 
    
    /**
     * Start in front of the middle of the pyramid.
     * Use CV to shoot 2. Go forward under pyramid and pick up 2. 
     * Drive backwards. Use CV to fire those two. 180 degree spin at end. 
     */
    public static void auton3() {
        // add CV
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        shootUntilEmpty();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().driveStraightInches(-Constants.FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID);
        shootUntilEmpty();
        Drivetrain.getInstance().spin180();
    }
    
    /**
     * Start in front of the middle of the pyramid.
     * Use CV to shoot 2. Move forward and acquire disks under pyramid and at center of field. 
     * Don't stop for pickup. After acquired, drive backwards to the front of pyramid. 
     * Stop running acquirer on the way back. Use CV to shoot the 4 acquired disks. Spin 180 at end.
     */ 
    public static void auton4() {
        // add CV
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        shootUntilEmpty();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_FRONT_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().driveStraightInches(-Constants.CENTER_TO_FRONT_OF_PYRAMID);
        shootUntilEmpty();
        Drivetrain.getInstance().spin180();
    }
    
    /**
     * Start at the back of the pyramid.
     * Shoot 3. Move forward and acquire disks at center of field. 
     * Return and shoot 4.
     * You might have 4 depending on how many people have left there from other robots.
     */
    public static void auton5() {
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        shootUntilEmpty();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_BACK_OF_PYRAMID);
        Drivetrain.getInstance().driveStraightInches(-Constants.CENTER_TO_BACK_OF_PYRAMID);
        Acquirer.getInstance().stop();
        shootUntilEmpty();
    } 
   
    /*
     * Do nothing.
     */
    public static void auton6() {
        
    }   
    
    // DO NOT TEST THESE UNTIL COMPETITION. DRIVER PRACTICE FIRST!
    // DO NOT CROSS COMPLETELY INTO THE OTHER SIDE OF THE FIELD!
    // -------------------------------------------------------------------------
    /**
     * Same as 4 but NO 180 SPIN, move forward to center at end of auton.
     */
    public static void auton7() {
        // add CV
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        shootUntilEmpty();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_FRONT_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().driveStraightInches(-Constants.CENTER_TO_FRONT_OF_PYRAMID);
        shootUntilEmpty();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_FRONT_OF_PYRAMID);
    }
    
    /**
     * Same as 3 but NO 180 SPIN, move forward to center of field. 
     */
    public static void auton8()  {
        // add CV
        Timer.delay(autonDelay);
        Conveyor.getInstance().conveyAutomatic();
        shootUntilEmpty();
        Acquirer.getInstance().acquire();
        Drivetrain.getInstance().driveStraightInches(Constants.FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID);
        Acquirer.getInstance().stop();
        Drivetrain.getInstance().driveStraightInches(-Constants.FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID);
        shootUntilEmpty();
        Drivetrain.getInstance().driveStraightInches(Constants.CENTER_TO_FRONT_OF_PYRAMID);
    }
    
    /**
     * Shoots until the hopper, containing the discs, is empty.
     */
    public static void shootUntilEmpty() {
    }
}
