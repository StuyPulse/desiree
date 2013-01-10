/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.util;

import edu.stuy.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author admin
 */
public class VictorSpeed {
    
    public static final double ROLLER_RADIUS = 0;
    public static final double KP = 0;
    public static final double KI = 0;
    public static final double KD = 0;
    
    private Encoder encoder;
    private Victor victor;
    private PIDController controller;

    private double speedSetpoint;
    //This is wrong. Need to run calculations for the real one.
    private static final double TOLERANCE_RPM = 4;

    /**
     * Make an actual speed controller complete with a Victor, Encoder and PIDController
     * @param victorChannel The PWM channel for the victor.
     * @param encoderAChannel Digital in for the encoder.
     * @param encoderBChannel Input for the other encoder.
     * @param reverse Not used.  Was for reversing encoder direction.
     */
    public VictorSpeed(int victorChannel) {
        speedSetpoint = 0;
        victor = new Victor(victorChannel);

        controller = new PIDController(KP, KI, KD, Drivetrain.getInstance().getSonar(), victor);
        controller.setOutputRange(-1, 1);
        controller.enable();
    }

    /**
     * Set the PWM value.
     *
     * The PWM value is set using a range of -1.0 to 1.0, appropriately
     * scaling the value for the FPGA.
     *
     * @param output The speed value between -1.0 and 1.0 to set.
     */
    public void pidWrite(double output) {
        victor.set(output);
    }

    /**
     * Set a wheel's speed setpoint.
     * @param speedRPM The desired wheel speed in RPM (revolutions per minute).
     */
    public void set(double speed) {
        speedSetpoint = speed;
        controller.setSetpoint(speed);
    }

    public double get() {
        return victor.get();
    }

    public void disable() {
        victor.disable();
    }

    public void setSpeed(double speed) {
        set(speed);
    }

    public boolean isAtSetPoint() {
        boolean atSetPoint = false;
        if (Math.abs(get() - speedSetpoint) < TOLERANCE_RPM) {
            atSetPoint = true;
        }
        return atSetPoint;
    }

    public void setPID(String prefix) {
        try {
            controller.setPID(SmartDashboard.getDouble(prefix+"P"), SmartDashboard.getDouble(prefix+"I"), SmartDashboard.getDouble(prefix+"D"));
        }
        catch (NetworkTableKeyNotDefined e) {
            SmartDashboard.putDouble(prefix+"P", 0);
            SmartDashboard.putDouble(prefix+"I", 0);
            SmartDashboard.putDouble(prefix+"D", 0);
        }
    }
}