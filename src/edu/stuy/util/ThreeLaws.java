/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.stuy.util;

/**
 * 
 * @author Isaac Asimov
 */
public interface ThreeLaws {

    /**
     * A robot may not injure a human being or, through inaction, allow a human being to come to harm.
     */
    public void doNoHarm();

    /**
     * A robot must obey the orders given to it by human beings, except where such orders would conflict with the First Law.
     */
    public void obeyOrders();

    /**
     * A robot must protect its own existence as long as such protection does not conflict with the First or Second Laws.
     */
    public void protectSelf();
}
