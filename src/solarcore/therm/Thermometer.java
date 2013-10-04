/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore.therm;

import solarcore.therm.exceptions.ThermometerErrorException;

/**
 *
 * @author Dawid
 */
public interface Thermometer {
    public double readTemp() throws ThermometerErrorException;
    public boolean isAlive();
    public void setId(String id);
    public String getId();
}
