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
 * @author kevin
 */
public class Tilter {
    private static Tilter instance;
    private Talon tilter;
    private AnalogChannel pot;
    
    private Tilter() {
        tilter = new Talon(Constants.TILTER_CHANNEL);
        pot = new AnalogChannel(Constants.TILTER_POT_CHANNEL);
    }
    
    public static Tilter getInstance() {
        if (instance == null) {
            instance = new Tilter();
        }
        return instance;
    }
    
    public void tiltUp() {
        tilter.set(1);
    }
    
    public void tiltDown() {
        tilter.set(-1);
    }
    
    public void stop() {
        tilter.set(0);
    }
    
    public double getPosition() {
        return pot.getVoltage();
    }
    
    
}
