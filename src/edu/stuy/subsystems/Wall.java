/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy.subsystems;

import edu.stuy.Constants;
import edu.stuy.util.Gamepad;
import edu.wpi.first.wpilibj.Victor;

/**
 *
 * @author Kevin Wang
 */
public class Wall {
    private static Wall instance;
    
    private Victor wench;
    
    public static double UP_VAL = -1;
    public static double DOWN_VAL = 1;
    public static double STOP_VAL = 0;
    
    private Wall() {
        wench = new Victor(Constants.WALL_WENCH_CHANNEL);
    }
    
    public static Wall getInstance() {
        if (instance == null) {
            instance = new Wall();
        }
        return instance;
    }
    
    public void up() {
        wench.set(UP_VAL);
    }
    
    public void down() {
        wench.set(DOWN_VAL);
    }
    
    public void stop() {
        wench.set(STOP_VAL);
    }
    
    public void manualWallControl(Gamepad gamepad) {
        if (gamepad.getLeftY() < -.5) {
            up();
        } else if (gamepad.getLeftY() > .5) {
            down();
        } else {
            stop();
        }
    }
}
