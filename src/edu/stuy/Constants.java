/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy;

/**
 * Port numbers and constants.
 * @author kevin
 */
public class Constants {
    /* USB PORTS */
    public static final int DRIVER_PAD_PORT = 1;
    public static final int OPERATOR_PAD_PORT = 2;
    
    /* PWM OUTPUTS */
    public static final int DRIVETRAIN_LEFT_1_CHANNEL = 1;
    public static final int DRIVETRAIN_LEFT_2_CHANNEL = 2;
    public static final int DRIVETRAIN_RIGHT_1_CHANNEL = 3;
    public static final int DRIVETRAIN_RIGHT_2_CHANNEL = 4;
    public static final int ACQUIRER_CHANNEL = 5;
    public static final int SHOOTER_CHANNEL = 6;
    public static final int TILTER_CHANNEL = 7;
    public static final int CONVEYOR_CHANNEL = 8;
    
    
    /* RELAY OUTPUTS */
    
    /* DIGITAL INPUTS */
    public static final int SONAR_CHANNEL_PING = 1;
    public static final int SONAR_CHANNEL_ECHO = 2;
    public static final int UPPER_CONVEYOR_SENSOR = 9;
    public static final int LOWER_CONVEYOR_SENSOR = 10;
    
    /* DIGITAL OUTPUTS */
    public static final int WHITE_LIGHTS_CHANNEL = 3;
    public static final int COLORED_LIGHTS_CHANNEL = 4;
     
    /* ANALOG INPUTS */
    public static final int POT_CHANNEL = 2;
    public static final int GYRO_CHANNEL = 3;
    public static final int ACCELEROMETER_CHANNEL = 4;
    
    /* SHOOTING ANGLES */
    public static final int DUMMY_ANGLE = 69;
}
