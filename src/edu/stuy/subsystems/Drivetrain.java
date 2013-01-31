/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The robot drivetrain.
 * @author kevin
 */
public class Drivetrain{
    private static Drivetrain instance;
    
    PIDController straightController;
    Gyro gyro;
    
    private RobotDrive drivetrain;

    private Drivetrain() {
        drivetrain = new RobotDrive(Constants.DRIVETRAIN_LEFT_CHANNEL, Constants.DRIVETRAIN_RIGHT_CHANNEL);
        gyro = new Gyro(Constants.GYRO_CHANNEL);
                
        straightController = new PIDController(Constants.PVAL, Constants.IVAL, Constants.DVAL, gyro, new PIDOutput() {
            public void pidWrite(double output) {
               drivetrain.arcadeDrive(1, output);
            }
        }, 0.005);
        straightController.setInputRange(-360.0, 360.0);
        straightController.setPercentTolerance(1 / 90. * 100);
        straightController.disable();
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

    public void enableDriveStraight(){
           straightController.setSetpoint(0);
           straightController.enable();
    }

    public void disableDriveStraight(){
       straightController.disable();
    }
}
