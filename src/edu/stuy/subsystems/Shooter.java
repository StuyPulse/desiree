/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Arfan
 */
public class Shooter {
    //dummy code
    private static Shooter instance;
    private Talon shooter;
    private Talon tilter;
    private AnalogChannel pot;
    
    private Shooter () {
        shooter = new Talon(Constants.SHOOTER_CHANNEL);
        tilter = new Talon(Constants.TILTER_CHANNEL);
        pot = new AnalogChannel(Constants.POT_CHANNEL);
    }
    
    public void tilt(double val) {
        if (val >= 0.5) {
            tilter.set(1);
        } 
        else if (val <= -0.5) {
            tilter.set(-1);
        }
        else {
            tilter.set(0);
        }
    }
    
    public double getPosition() {
        return pot.getVoltage();
    }
    
}
