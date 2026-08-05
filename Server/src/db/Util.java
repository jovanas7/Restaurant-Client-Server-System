/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Joska Stojanovic
 */
public class Util {

    private Properties properties;
    private static Util instanca;
    FileInputStream in;
    FileOutputStream out;

    private Util() throws FileNotFoundException, IOException {
        in = new FileInputStream("config/config.properties");
        properties = new Properties();
        properties.load(in);
    }

    public static Util getInstanca() throws IOException {
        if (instanca == null) {
            instanca = new Util();
        }
        return instanca;
    }

    public void setOut(FileOutputStream out) {
        this.out = out;
    }

    public boolean daLiJePrazanProperti() {
        return properties.isEmpty();
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public void setUrl(String url) {
        properties.setProperty("url", url);
    }

    public String getUser() {
        return properties.getProperty("user");
    }

    public void setUser(String user) {
        properties.setProperty("user", user);
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public void setPassword(String password) {
        properties.setProperty("password", password);
    }

    public String getPort() {
        return properties.getProperty("port");
    }

    public void setPort(String port) {
        properties.setProperty("port", port);
    }

    public void zatvoriIn() throws IOException {
        in.close();
    }

    public void zatvoriOut() throws IOException {
        out.close();
    }

    public void store() throws IOException {
        properties.store(out, null);
    }
}
