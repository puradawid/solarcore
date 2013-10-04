/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore;

import java.util.Scanner;
import solarcore.statistics.StatLoggerManager;
import solarcore.statistics.StatLoggerXML;
import solarcore.therm.Thermometer;
import solarcore.therm.ThermometerMock;
import solarcore.therm.ThermometerRPI;

/**
 *
 * @author Dawid
 */
public class SolarCore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        System.out.println("Program odpalil sie");
        System.out.println("Sprawdzanie termometru...");
        
        StatLoggerManager.addLogger(new StatLoggerXML("newile.xml"));
        StatLoggerManager.addLogger(new StatLoggerXML("anotherfile.xml"));
        Thermometer t = new ThermometerMock();
        while (true) {
            if (t.isAlive()) {
                System.out.println(Double.toString(t.readTemp()));
            }
            Thread.sleep(1000);
        }

    }
}
