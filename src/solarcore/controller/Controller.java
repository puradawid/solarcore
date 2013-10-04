/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore.controller;

import solarcore.pump.Pump;
import solarcore.therm.Thermometer;

/**
 *
 * @author Dawid
 */
public class Controller {
    
    Thermometer solar;
    Thermometer boiler;
    Pump pump;
    
    double diff;
    double histeresis;
    
    public Controller(Thermometer solar, Thermometer boiler, Pump pump,
            double diff, double histeresis)
    {
        this.solar = solar;
        this.boiler = boiler;
        this.pump = pump;
        this.diff = diff;
        this.histeresis = histeresis;
    }
    
    public void doLogic() throws Exception
    {
        if(pump.isOn() == false && solar.readTemp() - boiler.readTemp() > diff + histeresis)
            pump.turnOn();
        if(pump.isOn() &&  solar.readTemp() - boiler.readTemp() < diff - histeresis ) 
            pump.turnOff();
    }
}
