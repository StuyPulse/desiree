/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import com.sun.squawk.util.MathUtils;
import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author kevin
 */
public class Tilter {
    private static Tilter instance;
    private Talon tilter;
    private ADXL345_I2C accel;
    private Encoder enc;
    private double initialLeadLength;
    
    private Tilter() {
        tilter = new Talon(Constants.TILTER_CHANNEL);
        accel = new ADXL345_I2C(Constants.ACCELEROMETER_CHANNEL, ADXL345_I2C.DataFormat_Range.k16G);
        enc = new Encoder(Constants.TILT_ENCODER_A,Constants.TILT_ENCODER_B);
        initialLeadLength = getInitialLeadscrewLength();
        enc.setDistancePerPulse(Constants.TILTER_DISTANCE_PER_PULSE);
        enc.start();
    }
    
    public static Tilter getInstance() {
        if (instance == null) {
            instance = new Tilter();
        }
        return instance;
    }
    
    public void manualTilterControl(Gamepad gamepad) {
        if(gamepad.getDPadY() > 0) {
            tiltUp();
        }
        else if(gamepad.getDPadY() < 0) {
            tiltDown();
        }
        else {
            stop();
        }
    }
    
    public void tiltUp() {
        tilter.set(1);
    }
    
    public void tiltDown() {
        tilter.set(-1);
    }
    
    public void stop() {
        tilter.set(0);
    }
    
    public double getXAcceleration() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kX);
    }
    
    public double getYAcceleration() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kY);
    }
    
    public double getZAcceleration() {
        return accel.getAcceleration(ADXL345_I2C.Axes.kZ);
    }
    
    public double getAbsoluteAngle() {
        return MathUtils.atan(getYAcceleration() / getZAcceleration()) * 180.0 / Math.PI;
    }
    
    public double getLeadLength() {
        return initialLeadLength + enc.getDistance();
    }
    
    private double square(double x) {
        return x * x;
    }
    
    /* 
     * ======== v(q) uses distance formula ========
     * v(q) = sqrt((zcosq - x)^2 + (zcosq - y)^2)
     * v = leadscrew length
     * q = angle of shooter
     * z = distance from pivot to where leadscrew hits shooter
     * x = distance from pivot to base of base of leadscrew
     * y = height of the leadscrew
     */
    public double getInitialLeadscrewLength() {
        double angle = getAbsoluteAngle() * Math.PI / 180;
        return Math.sqrt(square(Constants.SHOOTER_DISTANCE_TO_LEADSCREW * Math.cos(angle) - Constants.DISTANCE_TO_LEADSCREW_BASE)
                + square(Constants.SHOOTER_DISTANCE_TO_LEADSCREW * Math.sin(angle) - Constants.LEADSCREW_HEIGHT));
    }
    
    /*
     * ======== q(v) adds two angles ========
     * q(v) = atan(y/x) + acos( (v^2 + x^2 + y^2 - z^2) / (2vsqrt(x^2 + y^2)) )
     * variables are defined above
     */
    public double getShooterAngle() {
        double leadscrewLength = getLeadLength();
        double heightSquared = square(Constants.LEADSCREW_HEIGHT);
        double baseSquared = square(Constants.DISTANCE_TO_LEADSCREW_BASE);
        double hypSquared = square(Constants.SHOOTER_DISTANCE_TO_LEADSCREW);
        return MathUtils.atan(Constants.LEADSCREW_HEIGHT / Constants.DISTANCE_TO_LEADSCREW_BASE) + 
               MathUtils.acos((square(leadscrewLength) + baseSquared + heightSquared - hypSquared) / 
               (2 * leadscrewLength * Math.sqrt(baseSquared + heightSquared)));
    }
}
