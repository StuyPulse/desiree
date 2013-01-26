/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.stuy.util.Sonar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The robot drivetrain.
 * @author kevin,arfan
 */
public class Drivetrain {
    
    private static Drivetrain instance;
    private RobotDrive drivetrain;
    private Gyro gyro;
    private Sonar sonar;
    private Compressor compressor;
    
    private Drivetrain() {
        drivetrain = new RobotDrive(Constants.DRIVETRAIN_LEFT_1_CHANNEL, Constants.DRIVETRAIN_LEFT_2_CHANNEL, Constants.DRIVETRAIN_RIGHT_1_CHANNEL, Constants.DRIVETRAIN_RIGHT_2_CHANNEL);
        sonar = new Sonar(Constants.SONAR_CHANNEL,Constants.ANALOG_SUPPLY_VOLTAGE_CHANNEL);
        sonar.start();
        gyro = new Gyro(Constants.GYRO_CHANNEL);
        gyro.setSensitivity(0.007);
        compressor = new Compressor(Constants.PRESSURE_SWITCH_CHANNEL, Constants.COMPRESSOR_RELAY_CHANNEL);
        compressor.start();
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

    public Sonar getSonar() {
        return sonar;
    }
    
    public double getSonarDistance() {
        return sonar.getDistance();
    }
    
    public void putDistance() {
        SmartDashboard.putNumber("Sonar distance:", sonar.getDistance());
    }

    public double getAngle() {
        return gyro.getAngle();
    }
    
    public void startOver() {
        gyro.reset();
    }
    
    public void putAngle() {
        SmartDashboard.putNumber("Gyro angle:", gyro.getAngle());
    }
    
    public void stopCompressor() {
        compressor.stop();
    }
 
    public boolean getPressure() {
        return compressor.getPressureSwitchValue();
    }
}
