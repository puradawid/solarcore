/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore.therm;

import java.util.logging.Logger;
import solarcore.statistics.StatLoggerManager;
import solarcore.statistics.exceptions.StatisticException;

/**
 *
 * @author Dawid
 */
public class ThermometerMock implements Thermometer {

    double temp = 13.5;
    boolean alive = true;
    String id;

    @Override
    public double readTemp() {
        try {
        StatLoggerManager.registerTemperature(temp, this);
        } catch (StatisticException e)
        {}
        return temp;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setId(String id) {
        Logger.getLogger(getClass().toString()).info("Id of thermometer: " + id);
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
    
    public void setTemp(double temp)
    {
        this.temp = temp;
    }
    
    public void setAlivness(boolean alive)
    {
        this.alive = alive;
    }
}
