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
    private Talon winch;
    
    public static Climber getInstance() {
        if (instance == null)
            instance = new Climber();
        return instance;
    }
    
    private Climber() {
        winch = new Talon(Constants.WINCH_CHANNEL);
       
    }
    
    public void forwardWinch() {
        winch.set(1);
    }
    
    public void stopWinch() {
        winch.set(0);
    }
    
    public void reverseWinch() {
        winch.set(-1);
    }
    
    public void setWinch(double val) {
        winch.set(val);
    }
       
    
}
