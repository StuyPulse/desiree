/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.util;

import edu.wpi.first.wpilibj.AnalogChannel;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class Sonar {
    private AnalogChannel sensor;
    private AnalogChannel analogSupplyInput;
    
    private Vector measurements;
    private Timer updateMeasurements;
    private final int UPDATE_PERIOD_MS = 10;
    
    public Sonar(int sensorChannel, int analogSupplyInputChannel) {
        sensor = new AnalogChannel(sensorChannel);
        analogSupplyInput = new AnalogChannel(analogSupplyInputChannel);
        measurements = new Vector();
    }

    /**
     * Starts the update thread.
     */
    public void start() {
        stop();
        updateMeasurements = new Timer();
        updateMeasurements.schedule(new TimerTask() {

            public void run() {
                synchronized (Sonar.this) {
                    measurements.addElement(new Double(getInstantaneousDistance()));
                    if (measurements.size() > 10) {
                        measurements.removeElementAt(0);
                    }
                }
            }
        }, 0, UPDATE_PERIOD_MS);
    }

    /**
     * Stops the update thread.
     */
    public void stop() {
        if (updateMeasurements != null) {
            updateMeasurements.cancel();
        }
    }

    /**
     * Clear the update buffer.
     */
    public void reset() {
        measurements.removeAllElements();
    }

    /**
     * Gets the average of the last 10 sonar distance measurements.
     * @return Distance in inches
     */
    public double getDistance() {
        if (measurements.isEmpty()) {
            return 0;
        }
        double sum = 0;
        synchronized (Sonar.this) {
            for (int i = 0; i < measurements.size(); i++) {
                sum += ((Double) measurements.elementAt(i)).doubleValue();
            }
            return sum / measurements.size();
        }
    }
    
    /**
     * Gets the distance measurement of the sonar in inches.
     * @return Distance in inches
     */
    public double getInstantaneousDistance() {
        // (Vcc/512) volts per inch; therefore, (512/Vcc) inches per volt
        return sensor.getVoltage() * (512 / analogSupplyInput.getVoltage()); // See datasheet on Sonar Sensor for more information.
    }
}
