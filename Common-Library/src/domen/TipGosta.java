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
public class TipGosta extends OpstiDomenskiObjekat {

    private int idTipGosta;
    private String nazivTipa;
    private String opisTipa;

    public TipGosta() {
    }

    public TipGosta(int idTipGosta, String nazivTipa, String opisTipa) {
        this.idTipGosta = idTipGosta;
        this.nazivTipa = nazivTipa;
        this.opisTipa = opisTipa;
    }

    public String getOpisTipa() {
        return opisTipa;
    }

    public void setOpisTipa(String opisTipa) {
        this.opisTipa = opisTipa;
    }

    public int getIdTipGosta() {
        return idTipGosta;
    }

    public void setIdTipGosta(int idTipGosta) {
        this.idTipGosta = idTipGosta;
    }

    public String getNazivTipa() {
        return nazivTipa;
    }

    public void setNazivTipa(String nazivTipa) {
        this.nazivTipa = nazivTipa;
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
        final TipGosta other = (TipGosta) obj;
        return this.idTipGosta == other.idTipGosta;
    }

    @Override
    public String toString() {
        return nazivTipa;
    }

    @Override
    public String vratiNazivTabele() {
        return "tipGosta";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) {
        List<OpstiDomenskiObjekat> listaTipovaGostiju = new ArrayList<>();

        try {
            while (resultSet.next()) {
                TipGosta tipGosta = new TipGosta(resultSet.getInt("idtipgosta"),
                        resultSet.getString("nazivTipa"),
                        resultSet.getString("opisTipa"));

                listaTipovaGostiju.add(tipGosta);
            }
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske prilikom ucitavanja liste tipova gostiju iz ResultSet-a.");
            ex.printStackTrace();
        }

        return listaTipovaGostiju;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idTipGosta";
    }

    @Override
    public String vratiNaziveKolona() {
        return "nazivTipa,opisTipa";
    }

    @Override
    public String vratiVrednostZaUnos() {
        return "'" + nazivTipa + "' ,"
                + "'" + opisTipa + "' ";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return "" + idTipGosta;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "nazivTipa = '" + nazivTipa +"', "
                + "opisTipa = '"+opisTipa+"'";
    }

    @Override
    public String vratiAlijas() {
        return "tg";
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
