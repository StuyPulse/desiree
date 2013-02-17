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
    /* DEBUG FLAG */
    public static boolean DEBUG = true; //Set to false for production
    
    /* USB PORTS */
    public static final int DRIVER_PAD_PORT = 1;
    public static final int OPERATOR_PAD_PORT = 2;
    public static final int CLIMBER_STICK_PORT = 3;
    
    /* PWM OUTPUTS */
    public static final int DRIVETRAIN_LEFT_CHANNEL = 1;
    public static final int DRIVETRAIN_RIGHT_CHANNEL = 2;
    public static final int CLIMBER_SERVO_1_CHANNEL = 3;
    public static final int CLIMBER_SERVO_2_CHANNEL = 4;
    public static final int ACQUIRER_CHANNEL = 5;
    public static final int SHOOTER_CHANNEL = 6;
    public static final int SHOOTER_SERVO_CHANNEL = 7;
    public static final int TILTER_CHANNEL = 8;
    public static final int CONVEYOR_CHANNEL = 9;
    public static final int WENCH_CHANNEL = 10;
   
    /* RELAY OUTPUTS */
    public static final int COMPRESSOR_RELAY_CHANNEL = 1;
    public static final int CAMERA_AND_DIRECTION_RELAY_CHANNEL = 2;
    public static final int SIGNAL_LIGHT_RELAY_CHANNEL = 3;
    
    /* GPIO */
    public static final int DRIVE_ENCODER_LEFT_A = 1;
    public static final int DRIVE_ENCODER_LEFT_B = 2;
    public static final int DRIVE_ENCODER_RIGHT_A = 3;
    public static final int DRIVE_ENCODER_RIGHT_B = 4;
    public static final int TILT_ENCODER_A = 5;
    public static final int TILT_ENCODER_B = 6;
    public static final int UPPER_LIMIT_SWITCH_CHANNEL = 7;
    public static final int LOWER_LIMIT_SWITCH_CHANNEL = 8;
    public static final int HOPPER_SENSOR = 9;
    public static final int LOWER_CONVEYOR_SENSOR = 10;
    public static final int PRESSURE_SWITCH_CHANNEL = 11;
    
    /* I2C */
    public static final int ACCELEROMETER_CHANNEL = 1;
     
    /* ANALOG INPUTS */
    public static final int GYRO_CHANNEL = 1;
    
    /* SOLENOID */
    public static final int SHOOTER_PLUNGER_OUT_CHANNEL = 1;
    public static final int SHOOTER_PLUNGER_IN_CHANNEL = 2;
    public static final int CLIMBER_DEPLOYER_CHANNEL = 3;
    public static final int CLIMBER_WITHDRAWER_CHANNEL = 4;
    
    /* SHOOTING ANGLES */
    public static final int FEEDER_STATION_ANGLE = 69;
    public static final int DEFAULT_SHOOTER_ANGLE = 60;
    
    /* GYRO DRIVE STRAIGHT PID VALUES */
    public static final double PVAL_D = .01;
    public static final double IVAL_D = .01;
    public static final double DVAL_D = .01;
    
    /* DRIVETRAIN DISTANCE MEASUREMENT CONSTANTS */
    private static final double ENCODER_PULSE_PER_REV = 360.; // From encoder
    private static final double GEAR_RATIO = 42.0 / 39.0; // Wheels turn 39 times every time gear turns 42
    private static final double WHEEL_RADIUS = 2.0; // inches of the wheel radius
    public static final double ENCODER_DISTANCE_PER_PULSE = 1 / (ENCODER_PULSE_PER_REV * GEAR_RATIO / ( 2 * Math.PI * WHEEL_RADIUS));
    
    /* AUTON DRIVE TIMEOUTS */
    public static final double DRIVE_STRAIGHT_TIMEOUT = 7.0;
    public static final long SPIN_TIME = 500; //Time to spin 180 in milliseconds
    
    /* TILTER CONSTANTS */
    public static final double DISTANCE_TO_LEADSCREW_BASE = 10.;
    public static final double SHOOTER_DISTANCE_TO_LEADSCREW = 16.;
    public static final double LEADSCREW_HEIGHT = 5.;
    public static final double TILTER_INCHES_PER_REV = 0.1;
    public static final double TILTER_DISTANCE_PER_PULSE = TILTER_INCHES_PER_REV / ENCODER_PULSE_PER_REV;
    
    /* TILTER ANGLE CONTROL PID VALUES */
    public static final double PVAL_T = 0.0;
    public static final double IVAL_T = 0.0;
    public static final double DVAL_T = 0.0;
    
    /* AUTONOMOUS DISTANCES */
    public static final double CENTER_TO_BACK_OF_PYRAMID = 108.;
    public static final double PYRAMID_BASE_LENGTH = 94.;
    public static final double CENTER_TO_FRONT_OF_PYRAMID = PYRAMID_BASE_LENGTH + CENTER_TO_BACK_OF_PYRAMID;
    public static final double MIDDLE_OF_PYRAMID_OVERSHOOT_DISTANCE = 5;
    public static final double FRONT_OF_PYRAMID_TO_MIDDLE_OF_PYRAMID = PYRAMID_BASE_LENGTH / 2. + MIDDLE_OF_PYRAMID_OVERSHOOT_DISTANCE;
    
    /* NETWORKING AND CV VALUES */
    public static final int CV_PORT = 6940;
    public static final String CV_IP = "10.6.94.14";
    public static final int CV_DEFAULT_VALUE = 694; 
    public static final long CV_TIMEOUT = 3000; //Timeout in ms

    /* The DESarray */
    public static final char DES[] = {'i', 'r', 'e', 'e'};
}
