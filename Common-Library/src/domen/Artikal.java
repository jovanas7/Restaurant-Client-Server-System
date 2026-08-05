/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Artikal extends OpstiDomenskiObjekat {

    private int idArtikal;
    private String nazivArtikla;
    private TipArtikla tipArtikla;
    private BigDecimal cenaBezPdv;
    private boolean dostupan;
    private BigDecimal pdv;

    public Artikal() {
    }

    public Artikal(int idArtikla, String nazivArtikla, TipArtikla tipArtikla, BigDecimal cenaBezPdv, boolean dostupan, BigDecimal pdv) {
        this.idArtikal = idArtikla;
        this.nazivArtikla = nazivArtikla;
        this.tipArtikla = tipArtikla;
        this.cenaBezPdv = cenaBezPdv;
        this.dostupan = dostupan;
        this.pdv = pdv;
    }

    public boolean isDostupan() {
        return dostupan;
    }

    public void setDostupan(boolean dostupan) {
        this.dostupan = dostupan;
    }

    public int getIdArtikal() {
        return idArtikal;
    }

    public void setIdArtikal(int idArtikal) {
        this.idArtikal = idArtikal;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public TipArtikla getTipArtikla() {
        return tipArtikla;
    }

    public void setTipArtikla(TipArtikla tipArtikla) {
        this.tipArtikla = tipArtikla;
    }

    public BigDecimal getCenaBezPdv() {
        return cenaBezPdv;
    }

    public void setCenaBezPdv(BigDecimal cenaBezPdv) {
        this.cenaBezPdv = cenaBezPdv;
    }

    public BigDecimal getPdv() {
        return pdv;
    }

    public void setPdv(BigDecimal pdv) {
        this.pdv = pdv;
    }

    @Override
    public String toString() {
        return nazivArtikla;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Artikal other = (Artikal) obj;
        return this.idArtikal == other.idArtikal;
    }

    @Override
    public String vratiNazivTabele() {
        return "artikal";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) {
        List<OpstiDomenskiObjekat> listaArtikala = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Artikal artikal = new Artikal(resultSet.getInt("idArtikal"),
                        resultSet.getString("nazivArtikla"),
                        TipArtikla.valueOf(resultSet.getString("tipArtikla")),
                        resultSet.getBigDecimal("cenaBezPdv"),
                        resultSet.getBoolean("dostupan"),
                        resultSet.getBigDecimal("pdv"));
               
                listaArtikala.add(artikal);
            }
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske prilikom ucitavanja liste artikala iz ResultSet-a.");
            ex.printStackTrace();
        }

        return listaArtikala;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idArtikal";
    }

    @Override
    public String vratiNaziveKolona() {
        return "nazivArtikla, tipArtikla, cenaBezPdv, dostupan, pdv";
    }

    @Override
    public String vratiVrednostZaUnos() {
        return "'" + nazivArtikla + "', "
                + "'" + tipArtikla + "', "
                + "'" + cenaBezPdv + "', "
                + "'" + dostupan + "', "
                + "'" + pdv + "'";

    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return "" + idArtikal;
    }

    @Override
    public String vratiVrednostZaIzmenu() {

        return "nazivArtikla = '" + nazivArtikla + "', "
                + "tipArtikla = '" + tipArtikla + "', "
                + "cenaBezPdv = '" + cenaBezPdv + "', "
                + "dostupan = '" + dostupan + "', "
                + "pdv = '" + pdv + "'";
    }

    @Override
    public String vratiAlijas() {
        return "a";
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
