/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore.pump;

/**
 *
 * @author Dawid
 */
public interface Pump {
    
    public void turnOn();
    
    public void turnOff();
    
    public void turn(boolean on);
    
    public boolean isOn();
}
