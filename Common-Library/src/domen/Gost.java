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
public class Gost extends OpstiDomenskiObjekat {

    private int idGost;
    private String imeGosta;
    private String prezimeGosta;
    private TipGosta tipGosta;

    public Gost() {
    }

    public Gost(int idGost, String imeGosta, String prezimeGosta, TipGosta tipGosta) {
        this.idGost = idGost;
        this.imeGosta = imeGosta;
        this.prezimeGosta = prezimeGosta;
        this.tipGosta = tipGosta;
    }

    public TipGosta getTipGosta() {
        return tipGosta;
    }

    public void setTipGosta(TipGosta tipGosta) {
        this.tipGosta = tipGosta;
    }

    public int getIdGost() {
        return idGost;
    }

    public void setIdGost(int idGost) {
        this.idGost = idGost;
    }

    public String getImeGosta() {
        return imeGosta;
    }

    public void setImeGosta(String imeGosta) {
        this.imeGosta = imeGosta;
    }

    public String getPrezimeGosta() {
        return prezimeGosta;
    }

    public void setPrezimeGosta(String prezimeGosta) {
        this.prezimeGosta = prezimeGosta;
    }

    @Override
    public String toString() {
        return imeGosta + " " + prezimeGosta;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Gost other = (Gost) obj;
        return this.idGost == other.idGost;
    }

    @Override
    public String vratiNazivTabele() {
        return "gost";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) {
        List<OpstiDomenskiObjekat> listaGostiju = new ArrayList<>();

        try {
            while (resultSet.next()) {
                TipGosta tipGosta = new TipGosta(resultSet.getInt("idTipGosta"), resultSet.getString("nazivTipa"), resultSet.getString("opisTipa"));

                Gost gost = new Gost(resultSet.getInt("idGost"),
                        resultSet.getString("imeGosta"),
                        resultSet.getString("prezimeGosta"),
                        tipGosta);

                listaGostiju.add(gost);
            }
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske prilikom ucitavanja liste gostiju iz ResultSet-a.");
            ex.printStackTrace();
        }

        return listaGostiju;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idGost";
    }

    @Override
    public String vratiNaziveKolona() {
        return "imeGosta, prezimeGosta, idTipGosta";
    }

    @Override
    public String vratiVrednostZaUnos() {
    return "'" + imeGosta + "', "
                + "'" + prezimeGosta + "', "
                + "'" + tipGosta.getIdTipGosta() + "'"; 

    }
   

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return "" + idGost;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "imeGosta = '" + imeGosta + "', "
                + "prezimeGosta = '" + prezimeGosta + "', "
                + "idTipGosta = '" + tipGosta.getIdTipGosta() + "'";
    }

    @Override
    public String vratiAlijas() {
        return "g";
    }

    @Override
    public String vratiUslovZaJoin() {
    return "JOIN tipGosta tg ON (g.idTipGosta = tg.idTipGosta)";
    }

    @Override
    public String vratiUslov() {
        return "";
    }

}
