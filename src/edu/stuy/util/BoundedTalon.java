/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.util;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Danny
 */
public class BoundedTalon extends Talon {
    
    DigitalInput fwdSwitch;
    DigitalInput revSwitch;
    
    public BoundedTalon(int port, int fwdLimitSwitchChannel, int revLimitSwitchChannel) {
        super(port);
        fwdSwitch = new DigitalInput(port, fwdLimitSwitchChannel);
        revSwitch = new DigitalInput(port, revLimitSwitchChannel);
    }
    
    public void set(double value) {
        if ((isFwdSwitchTriggered() && value < 0) || (isRevSwitchTriggered() && value > 0)) {
            super.set(value);
        } else {
            super.set(0);
        }
    }
    
    public boolean isFwdSwitchTriggered() {
        return !fwdSwitch.get();
    }
    
    public boolean isRevSwitchTriggered() {
        return !revSwitch.get();
    }
}
