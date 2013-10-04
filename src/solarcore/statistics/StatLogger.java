/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore.statistics;

import solarcore.statistics.exceptions.StatisticException;
import solarcore.therm.Thermometer;

/**
 *
 * @author Dawid
 */

//Singleton alert
public interface StatLogger {
    public void registerAction(String action) throws StatisticException;
    public void registerTemperature(double temperature, Thermometer source) throws StatisticException;
}
