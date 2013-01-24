/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.util;

import edu.wpi.first.wpilibj.AnalogChannel;

/**
 *
 * @author admin
 */
public class Sonar {
    private AnalogChannel sensor;
    private AnalogChannel analogSupplyInput;
    
    public Sonar(int sensorChannel, int analogSupplyInputChannel) {
        sensor = new AnalogChannel(sensorChannel);
        analogSupplyInput = new AnalogChannel(analogSupplyInputChannel);
    }
    
    /**
     * Gets the distance measurement of the sonar in inches
     * @return Distance in inches
     */
    public double getDistance() {
        // (Vcc/512) volts per inch; therefore, (512/Vcc) inches per volt
        return sensor.getVoltage() * (512 / analogSupplyInput.getVoltage()); // See datasheet on Sonar Sensor for more information.
    }
}
