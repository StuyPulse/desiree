/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author abdullah, Arfan
 */
public class Climber {
    
    private static Climber instance;
    //private Victor wench;
    //private Servo deployer1;
    //private Servo deployer2;
    private Solenoid deployOut;
    private Solenoid deployIn;
    
    
    public static Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }
        return instance;
    }
    
    private Climber() {
        //wench = new Victor(Constants.WENCH_CHANNEL);
        //deployer1 = new Servo(Constants.CLIMBER_SERVO_1_CHANNEL);
        //deployer2 = new Servo(Constants.CLIMBER_SERVO_2_CHANNEL);
        deployOut = new Solenoid(Constants.CLIMBER_DEPLOYER_CHANNEL);
        deployIn = new Solenoid(Constants.CLIMBER_WITHDRAWER_CHANNEL);
    }
    
    public void forwardWench() {
        //wench.set(1);
    }
    
    public void stopWench() {
        //wench.set(0);
    }
    
    public void reverseWench() {
        //wench.set(-1);
    }
    
    public void setWench(double val) {
        //wench.set(val);
    }
    
    public void deploy() {
        //deployer1.set(1.0);
        //deployer2.set(1.0);
        deployIn.set(false);
        deployOut.set(true);
    }
    
    public void undeploy() {
        //deployer1.set(0.0);
        //deployer2.set(0.0);
        deployIn.set(true);
        deployOut.set(false);
    }
    
    public void manualClimberControl(Joystick stick) {
        if(stick.getRawButton(3)) {
            forwardWench();
        }
        if(stick.getTrigger()) {
            deploy();
        }
        if(stick.getRawButton(10)) {
            reverseWench();
        }
        if(stick.getRawButton(11)) {
            undeploy();
        }
    }
}
