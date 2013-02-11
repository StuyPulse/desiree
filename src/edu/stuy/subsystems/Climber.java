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
    private Solenoid deployer;
    private Solenoid withdrawer;
    
    public static Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }
        return instance;
    }
    
    private Climber() {
        wench = new Victor(Constants.WENCH_CHANNEL);
        deployer = new Solenoid(Constants.CLIMBER_DEPLOYER_CHANNEL);
        withdrawer = new Solenoid(Constants.CLIMBER_WITHDRAWER_CHANNEL);
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
    
//    public void deploy() {
//        deployer.set(true);
//        withdrawer.set(false);
//    }
    
//    public void withdraw() {
//        deployer.set(false);
//        withdrawer.set(true);
//    }
    
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
/**    
    public void manualDeployerControl(Joystick stick) {
        if(stick.getRawButton(1)) {
            deploy();
        }
        else if(stick.getRawButton(1)) {
            withdraw();
        }
    }
*/    
}
