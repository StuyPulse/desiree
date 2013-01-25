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
    private static boolean bottomDiscDetected, topDiscDetected;
    
    public static Conveyor getInstance() {
        if (instance == null)
            instance = new Conveyor();
        return instance;
    }
    
    private Conveyor() {
        roller = new Talon(Constants.CONVEYOR_CHANNEL);
        upperSensor = new DigitalInput(Constants.UPPER_CONVEYOR_SENSOR);
        lowerSensor = new DigitalInput(Constants.LOWER_CONVEYOR_SENSOR);
        bottomDiscDetected = false; 
        topDiscDetected = false;
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
    
    public boolean discAtTop() {
        topDiscDetected = true;
        return upperSensor.get();
    }
    
    public boolean discAtBottom() {
        bottomDiscDetected = true;
        return lowerSensor.get();
    }
    
    public boolean isBottomDiscDetected() {
        return bottomDiscDetected;
    }
    
    public boolean isTopDiscDetected() {
        return topDiscDetected;
    }
    
    public double getRoller() {
        return roller.get();
    }
    
    public void conveyAutomatic() {
        Shooter shooter = Shooter.getInstance();
        Acquirer acquirer = Acquirer.getInstance();
        if ((acquirer.isAcquiring() && (isBottomDiscDetected())) || isTopDiscDetected()) {
            convey();
        }
        else {
            stop();
        }               
    } 
    
}

