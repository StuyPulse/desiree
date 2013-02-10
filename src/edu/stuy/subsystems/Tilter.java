/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import com.sun.squawk.util.MathUtils;
import edu.stuy.Constants;
import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.Talon;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;


/**
 *
 * @author kevin
 */
public class Tilter {
    private static Tilter instance;
    private Talon tilter;
    private ADXL345_I2C accel;
    
    private Vector measurements;
    private Timer updateMeasurements;
    private final int MEASUREMENT_SIZE = 10; //Number of measurements to average
    private final int UPDATE_PERIOD_MS = 10; //Time between measurements. DO NOT USE ANY VALUE LESS THAN 10.
    
    private Tilter() {
        tilter = new Talon(Constants.TILTER_CHANNEL);
        accel = new ADXL345_I2C(Constants.ACCELEROMETER_CHANNEL, ADXL345_I2C.DataFormat_Range.k2G);
        measurements = new Vector();
        start();
    }
    
    public static Tilter getInstance() {
        if (instance == null) {
            instance = new Tilter();
        }
        return instance;
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
    
     /**
     * Starts the update thread.
     */
    public void start() {
        accelStop();
        updateMeasurements = new Timer();
        updateMeasurements.schedule(new TimerTask() {
            public void run() {
                synchronized (measurements) {
                    measurements.addElement(new Double(getInstantAngle()));
                    if (measurements.size() > MEASUREMENT_SIZE) {
                        measurements.removeElementAt(0);
                    }
                }
            }
        }, 0, UPDATE_PERIOD_MS);
    }
    
    public void accelStop() {
        if (updateMeasurements != null) {
            updateMeasurements.cancel();
        }
    }
    
    public void reset() {
        measurements.removeAllElements();
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
    
    /* Gets the angle from the measurements of the last 10 accelerations */
    public double getAbsoluteAngle() {
        if (measurements.isEmpty()) {
            return 0;
        }
        double sum = 0;
        synchronized (measurements) {
            for (int i = 0; i < measurements.size(); i++) {
                sum += ((Double) measurements.elementAt(i)).doubleValue();
            }
            return sum / measurements.size();
        }
    }
    
    /* Gets instantaneous angle */
    public double getInstantAngle() {
        return MathUtils.atan(getYAcceleration() / getZAcceleration()) * 180.0 / Math.PI;
    }
    
}
