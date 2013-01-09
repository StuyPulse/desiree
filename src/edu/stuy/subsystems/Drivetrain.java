/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The robot drivetrain.
 * @author kevin
 */
public class Drivetrain {
    private static Drivetrain instance;
    
    private RobotDrive drivetrain;
    
    private Drivetrain() {
        drivetrain = new RobotDrive(Constants.DRIVETRAIN_LEFT_CHANNEL, Constants.DRIVETRAIN_RIGHT_CHANNEL);
    }
    
    public static Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain();
        }
        return instance;
    }
    
    public void tankDrive(double leftValue, double rightValue) {
        drivetrain.tankDrive(leftValue, rightValue);
    }
    
    /**
     * Tank drive using a gamepad's left and right analog sticks.
     * @param gamepad Gamepad to tank drive with
     */
    public void tankDrive(Gamepad gamepad) {
        tankDrive(-gamepad.getLeftY(), -gamepad.getRightY());
    }
}
