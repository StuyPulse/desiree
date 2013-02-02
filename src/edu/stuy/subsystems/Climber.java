/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Arfan
 */
public class Climber {
    
    private static Climber instance;
    private Talon wench;
    
    public static Climber getInstance() {
        if (instance == null)
            instance = new Climber();
        return instance;
    }
    
    private Climber() {
        wench = new Talon(Constants.WENCH_CHANNEL);
       
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
       
    
}
