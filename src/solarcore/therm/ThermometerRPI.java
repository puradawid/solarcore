/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package solarcore.therm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import solarcore.therm.exceptions.ThermometerErrorException;

/**
 *
 * @author Dawid
 */
public class ThermometerRPI implements Thermometer {

    String id;
    String baseDir = "/sys/bus/w1/devices/w1_bus_master1";
    String deviceFolder = baseDir;

    public ThermometerRPI(String id) {
        this.id = id;
        deviceFolder = baseDir + "/" + id + "/" + "w1_slave";
    }

    @Override
    public double readTemp() throws ThermometerErrorException {
        double t = 0.0;
        FileReader fr = null;
        try {
            fr = new FileReader(deviceFolder);
        } catch (FileNotFoundException e) {
            throw new ThermometerErrorException("File not found maybe thermometer is broken.");
        }

        //parsing document

        BufferedReader br = new BufferedReader(fr);
        try {
            String str = br.readLine();
            while (str.contains("t=") == false) {
                str = br.readLine();
            }
            str = str.substring(str.lastIndexOf("t=") + "t=".length());
            t = Double.parseDouble(str);
            t /= 1000;

        } catch (Exception e) {
        }
        if (t < 0 || t > 100) {
            throw new ThermometerErrorException("Thermometer case 0 degrees. Propably corrupted");
        }
        return t;
    }

    @Override
    public boolean isAlive() {
        try {
            readTemp();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }
}
