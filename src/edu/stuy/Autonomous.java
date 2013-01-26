/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.stuy;

/**
 * This is the location of the autonomous routines.
 * We assume that we always begin touching the front edge of the pyramid.
 * This gives us two discs.
 * @author Eric
 */
public class Autonomous {
    public static int getAutonSetting(){
        int setting = 0;
        return setting;
    }
    
    public static void run() {
        switch (getAutonSetting()) {
            case 1: // just shoot two discs
                auton1();
                break;
            case 2: // shoot two discs, back up to pick up 4, move forward, and shoot again
                auton2();
                break;
            default:
                break;
        }
    }
    
    
    /**
     * Shoot two discs and then do nothing
     */
    public static void auton1() {
        // get shooter ready to shoot
        // shoot first disc
        // wait for shooter to be ready again
        // shoot second disc
    }
    
    /**
     * Shoot two discs, then move backwards under the pyramid to pick up the
     * four in the middle of the field, move back to the front edge of the
     * pyramid and shoot the four discs.
     */
    public static void auton2() {
        // get shooter ready to shoot
        // shoot first disc
        // wait for shooter to be ready again
        // shoot second disc
        // back up a certain distance and acquire 2 discs under pyramid
        // backup again at certain distance to center of field to get discs making sure not to cross center
        // Drive forward and under pyramid and shoot
        // wait for shooter and shoot and repeat
    }
}
