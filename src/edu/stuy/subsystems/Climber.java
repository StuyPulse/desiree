/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author abdullah, R4D4
 */
public class Climber {
    
    private static Climber instance;
    private Solenoid deployOut;
    private Solenoid deployIn;
    private DigitalInput forward;
    private DigitalInput reverse;
    
    public static Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }
        return instance;
    }
    
    private Climber() {
        deployOut = new Solenoid(Constants.CLIMBER_DEPLOYER_CHANNEL);
        deployIn = new Solenoid(Constants.CLIMBER_WITHDRAWER_CHANNEL);
        forward = new DigitalInput(Constants.CLIMBER_IN_CONTACT_CHANNEL);
        reverse = new DigitalInput(Constants.ClIMBER_NOT_IN_CONTACT_CHANNEL);
    }
    
    public void deploy() {
        deployIn.set(false);
        deployOut.set(true);
    }
    
    public void undeploy() {
        deployIn.set(true);
        deployOut.set(false);
    }
    
    public void reset() {
        undeploy();
    }
    
    public boolean isFWDTriggered() {
        return !forward.get();
    }
    
    public boolean isRevTriggered() {
        return !reverse.get();
    }
    
    public void limitSwitchLight() {
        if (isFWDTriggered() || isRevTriggered()) {
            Lights.getInstance().flashRedSignalLight();
        }
    }
    
    public void manualClimberControl(Gamepad gamepad) {
        if(gamepad.getLeftBumper() && gamepad.getRightBumper()) {
            deploy();
        }
        if(gamepad.getLeftTrigger() && gamepad.getRightTrigger()) {
            undeploy();
        }
    }
}
