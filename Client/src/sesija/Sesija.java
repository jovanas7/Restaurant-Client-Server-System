/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sesija;

import domen.Konobar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Joska Stojanovic
 */
public class Sesija {

    private static Sesija instanca;
    private Konobar ulogovaniKonobar;
    private final Map<String, Object> mapa;

    private Sesija() {
        mapa = new HashMap<>();
    }

    public static Sesija getInstanca() {
        if (instanca == null) {
            instanca = new Sesija();
        }
        return instanca;
    }

    public Konobar getUlogovaniKonobar() {
        return ulogovaniKonobar;
    }

    public void setUlogovaniKonobar(Konobar ulogovaniKonobar) {
        this.ulogovaniKonobar = ulogovaniKonobar;
    }

    public Map<String, Object> getMapa() {
        return mapa;
    }

}
