/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bartlomiejfraczak.foodapp.config;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.Preferences;
import org.ini4j.Ini;
import org.ini4j.IniPreferences;

/**
 *
 * @author thefr
 */
public class ConfigReader {

    private static ConfigReader instancja;
    private Preferences prefs;

    public static ConfigReader getInstancja() {
        if (instancja == null) {
            instancja = new ConfigReader();
        }
        return instancja;
    }

    private ConfigReader() {
        Ini ini = null;
        try {
            ini = new Ini(new File("config.ini"));
        } catch (IOException ex) {
            Logger.getLogger(ConfigReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (ini != null) {
            prefs = new IniPreferences(ini);
        }
    }

    public String getApiKey() {
        return prefs.node("spoonacularApi").get("apiKey", "0");
    }

    public int getIloscOdpowiedzi() {
        return Integer.valueOf(
                prefs.node("spoonacularApi").get("iloscOdpowiedzi", "5")
        );
    }

}
