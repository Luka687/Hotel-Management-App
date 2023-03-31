/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Luka
 */
public class Settings {
    private static Settings instance;
    private Properties properties;
    
    private Settings() {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("config/dbconfig.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Settings getInstance() {
        if(instance == null){
            instance = new Settings();
        }
        return instance;
    }
    
    public void setURL(String url){
        properties.setProperty("url", url);
    }
    
    public void setUsername(String username){
        properties.setProperty("username", username);
    }
    
    public void setPassword(String password){
        properties.setProperty("password", password);
    }
    
    public void save(){
        try {
            properties.store(new FileOutputStream("config/dbconfig.properties"), "Property file changed!");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Settings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
}
