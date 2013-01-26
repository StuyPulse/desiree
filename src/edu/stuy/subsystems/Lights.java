/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.wpi.first.wpilibj.DigitalOutput;

/**
 *
 * @author Arfan
 */
public class Lights {
    
    private static Lights instance;
    private DigitalOutput white;
    private DigitalOutput colored;
    
    private Lights() {
        white = new DigitalOutput(Constants.WHITE_LIGHTS_CHANNEL);
        colored = new DigitalOutput(Constants.COLORED_LIGHTS_CHANNEL);
    }
    
    public static Lights getInstance() {
        if (instance == null) {
            instance = new Lights();
        }
        return instance;
    }
    
    /*
     * run this in both tele and auton
     * flash when disc is picked up from ground or feeder
     * flash on color to signal human player for feed white discs as we approach
     * flash different color for colored discs
     * flash pickup frequency with shooting
     */
}
