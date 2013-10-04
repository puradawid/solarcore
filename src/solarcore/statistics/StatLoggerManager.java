/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore.statistics;

import java.util.LinkedList;
import java.util.List;
import solarcore.statistics.exceptions.StatisticException;
import solarcore.therm.Thermometer;

/**
 *
 * @author Dawid
 */
public class StatLoggerManager {
    static List<StatLogger> loggers = new LinkedList<>();
    
    public static void addLogger(StatLogger logger)
    {
        loggers.add(logger);
    }
    
    public static void registerAction(String action) throws StatisticException
    {
        for(StatLogger log : loggers)
            log.registerAction(action);
    }
    
    public static void registerTemperature(double temperature, Thermometer source) throws StatisticException
    {
        for(StatLogger log : loggers)
            log.registerTemperature(temperature, source);
    } 
}
