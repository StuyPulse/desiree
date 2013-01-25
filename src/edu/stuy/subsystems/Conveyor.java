/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.stuy.Constants;

/**
 *
 * @author Arfan
 */
public class Conveyor {
    
    private static Conveyor instance;
    private Talon roller;
    private DigitalInput upperSensor;
    private DigitalInput lowerSensor;
    private boolean hasTimeout;
    private double timeout;
    
    
    public Conveyor() {
        roller = new Talon(Constants.CONVEYOR_CHANNEL);
        upperSensor = new DigitalInput(Constants.UPPER_CONVEYOR_SENSOR);
        lowerSensor = new DigitalInput(Constants.LOWER_CONVEYOR_SENSOR);
        hasTimeout = false;
    }
    
    public void roll(double speed) {
        roller.set(speed);
    }
    
    public void convey() {
        roll(1);
    }
    
    public void reverseConvey() {
        roll(-1);
    }
    
    public void stop() {
        roll(0);
    }   
    
    public boolean topBall() {
        return upperSensor.get();
    }
    
    public boolean bottomBall() {
        return lowerSensor.get();
    }
    
    public double getRoller() {
        return roller.get();
    }
    
    
}

