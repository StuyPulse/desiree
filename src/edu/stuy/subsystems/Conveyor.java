/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Arfan, R4D4
 */
public class Conveyor {
    
    private static Conveyor instance;
    private Victor roller;
    private DigitalInput lowerSensor;
    private double lastTime = 0.;
    private boolean isConveying;
    
    private static double CONVEY_AUTO_TIME = 2.5;
    
    public static Conveyor getInstance() {
        if (instance == null) {
            instance = new Conveyor();
        }
        return instance;
    }
    
    private Conveyor() {
        roller = new Victor(Constants.CONVEYOR_CHANNEL);
        lowerSensor = new DigitalInput(Constants.LOWER_CONVEYOR_SENSOR_CHANNEL);
        isConveying = false;
    }
    
    public void roll(double speed) {
        roller.set(speed);
    }
    
    public void convey() {
        roll(1);
        isConveying = true;
    }
    
    public void reverseConvey() {
        roll(-1);
        isConveying = false;
    }
    
    public void stop() {
        roll(0);
        isConveying = false;
    }
    
    public void reset() {
        stop();
        lastTime = 0.;
        isConveying = false;
    }
    
    public boolean isBottomDiscDetected() {
        return lowerSensor.get();
    }
    
    /*
     * If the acquirer is conveying and the bottom disc is detected, it starts conveying.
     * It stops conveying after 1 second.
     */
    public void conveyAutomatic() {
        double time = Timer.getFPGATimestamp();
        if (Acquirer.getInstance().isAcquiring() && isBottomDiscDetected()) {
            isConveying = true;
            lastTime = time;
        }
        if (isConveying) {
            convey();        
        }
        if (time - lastTime >= CONVEY_AUTO_TIME) {
            stop();
        }       
    }
    
    public void manualConveyorControl(Gamepad gamepad) {
        if(Math.abs(gamepad.getLeftY()) > 0.1) {
            roll(-gamepad.getLeftY());
        }
        else {
            roll(0);
        }
    }
}

