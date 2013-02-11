/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Arfan
 */
public class Climber {
    
    private static Climber instance;
    private Victor wench;
    
    public static Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }
        return instance;
    }
    
    private Climber() {
        wench = new Victor(Constants.WENCH_CHANNEL);
    }
    
    public void forwardWench() {
        wench.set(1);
    }
    
    public void stopWench() {
        wench.set(0);
    }
    
    public void reverseWench() {
        wench.set(-1);
    }
    
    public void setWench(double val) {
        wench.set(val);
    }
    
    public void manualClimberControl(Joystick stick) {
        if(stick.getRawButton(1)) {
            forwardWench();
        }
        else if(stick.getRawButton(1)) {
            reverseWench();
        }
        else {
            stopWench();
        }
    }
    
}
