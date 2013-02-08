/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.stuy.Constants;
import edu.stuy.util.Gamepad;

/**
 *
 * @author Arfan
 */
public class Conveyor {
    
    private static Conveyor instance;
    private Talon roller;
    private DigitalInput upperSensor;
    private DigitalInput lowerSensor;
    
    public static Conveyor getInstance() {
        if (instance == null)
            instance = new Conveyor();
        return instance;
    }
    
    private Conveyor() {
        roller = new Talon(Constants.CONVEYOR_CHANNEL);
        upperSensor = new DigitalInput(Constants.UPPER_CONVEYOR_SENSOR);
        lowerSensor = new DigitalInput(Constants.LOWER_CONVEYOR_SENSOR);
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
    
    public boolean isBottomDiscDetected() {
        return lowerSensor.get();
    }
    
    public boolean isTopDiscDetected() {
        return upperSensor.get();
    }
    
    public double getRoller() {
        return roller.get();
    }
    
    public void conveyAutomatic() {
        if ((Acquirer.getInstance().isAcquiring() && (isBottomDiscDetected())) || isTopDiscDetected()) {
            convey();
        }
        else {
            stop();
        }               
    }
    
    public void manualConveyorControl(Gamepad gamepad) {
        if(gamepad.getTopButton()){
            convey();
        }
        else if(gamepad.getBottomButton()){
            reverseConvey();
        }
        else {
            stop();
        }
    }
    
    public void useLights() {
        
    }
    
}

