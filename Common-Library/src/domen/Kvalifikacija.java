/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joska Stojanovic
 */
public class Kvalifikacija extends OpstiDomenskiObjekat {

    private int idKvalifikacija;
    private String naziv;
    private String opis;

    public Kvalifikacija() {
    }

    public Kvalifikacija(int idKvalifikacija, String naziv, String opis) {
        this.idKvalifikacija = idKvalifikacija;
        this.naziv = naziv;
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getIdKvalifikacija() {
        return idKvalifikacija;
    }

    public void setIdKvalifikacija(int idKvalifikacija) {
        this.idKvalifikacija = idKvalifikacija;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return naziv;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Kvalifikacija other = (Kvalifikacija) obj;
        return this.idKvalifikacija == other.idKvalifikacija;
    }

    @Override
    public String vratiNazivTabele() {
        return "kvalifikacija";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) {
        List<OpstiDomenskiObjekat> listaKvalifikacija = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Kvalifikacija kvalifikacija = new Kvalifikacija(resultSet.getInt("idKvalifikacija"),
                        resultSet.getString("naziv"),
                        resultSet.getString("opis"));

                listaKvalifikacija.add(kvalifikacija);
            }
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske prilikom ucitavanja liste kvalifikacija iz ResultSet-a.");
            ex.printStackTrace();
        }

        return listaKvalifikacija;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKvalifikacija";
    }

    @Override
    public String vratiNaziveKolona() {
        return "naziv,opis";
    }

    @Override
    public String vratiVrednostZaUnos() {
        return "'" + naziv + "', "
                + "'" + opis + "'";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return "" + idKvalifikacija;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "naziv = '" + naziv + "', "
                + "opis = '" + opis + "'";
    }

    @Override
    public String vratiAlijas() {
        return "kv";
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
