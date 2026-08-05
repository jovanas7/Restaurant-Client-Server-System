/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joska Stojanovic
 */
public class KonobarKvalifikacija extends OpstiDomenskiObjekat {

    private Konobar konobar;
    private Kvalifikacija kvalifikacija;
    private Date datumDodele;

    public KonobarKvalifikacija() {
    }

    public KonobarKvalifikacija(Konobar konobar, Kvalifikacija kvalifikacija, Date datumDodele) {
        this.konobar = konobar;
        this.kvalifikacija = kvalifikacija;
        this.datumDodele = datumDodele;
    }

    public Date getDatumDodele() {
        return datumDodele;
    }

    public void setDatumDodele(Date datumDodele) {
        this.datumDodele = datumDodele;
    }

    public Konobar getKonobar() {
        return konobar;
    }

    public void setKonobar(Konobar konobar) {
        this.konobar = konobar;
    }

    public Kvalifikacija getKvalifikacija() {
        return kvalifikacija;
    }

    public void setKvalifikacija(Kvalifikacija kvalifikacija) {
        this.kvalifikacija = kvalifikacija;
    }

    @Override
    public String vratiNazivTabele() {
        return "konobarKvalifikacija";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) {
        return null;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "(idKonobar, idKvalifikacija)";
    }

    @Override
    public String vratiNaziveKolona() {
        return "idKonobar, idKvalifikacija, datumDodele";
    }

    @Override
    public String vratiVrednostZaUnos() {
        return "'" + konobar.getIdKonobar() + "', "
                + "'" + kvalifikacija.getIdKvalifikacija() + "', "
                + "'" + new java.sql.Date(datumDodele.getTime()) + "'";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return "(" + konobar.getIdKonobar() + ", " + kvalifikacija.getIdKvalifikacija() + ")";
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumDodele = '" + new java.sql.Date(datumDodele.getTime()) + "'";
    }

    @Override
    public String vratiAlijas() {
        return "kk";
    }

    @Override
    public String vratiUslovZaJoin() {
        return "";
    }

    @Override
    public String vratiUslov() {
        return "";
    }

}
