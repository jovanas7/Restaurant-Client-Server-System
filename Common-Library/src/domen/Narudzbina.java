/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joska Stojanovic
 */
public class Narudzbina extends OpstiDomenskiObjekat {

    private int idNarudzbina;
    private Date datumNarucivanja;
    private BigDecimal ukupanIznos;
    private NacinPlacanja nacinPlacanja;
    private String napomena;
    private Konobar konobar;
    private Gost gost;
    private List<StavkaNarudzbine> stavkeNarudzbine;

    public Narudzbina() {
    }

    public Narudzbina(int idNarudzbina, Date datumNarucivanja, BigDecimal ukupanIznos, NacinPlacanja nacinPlacanja, 
            String napomena, Konobar konobar, Gost gost) {
        this.idNarudzbina = idNarudzbina;
        this.datumNarucivanja = datumNarucivanja;
        this.ukupanIznos = ukupanIznos;
        this.nacinPlacanja = nacinPlacanja;
        this.napomena = napomena;
        this.konobar = konobar;
        this.gost = gost;
        this.stavkeNarudzbine = new ArrayList<>();
    }

    public Gost getGost() {
        return gost;
    }

    public void setGost(Gost gost) {
        this.gost = gost;
    }

    public int getIdNarudzbina() {
        return idNarudzbina;
    }

    public void setIdNarudzbina(int idNarudzbina) {
        this.idNarudzbina = idNarudzbina;
    }

    public Date getDatumNarucivanja() {
        return datumNarucivanja;
    }

    public void setDatumNarucivanja(Date datumNarucivanja) {
        this.datumNarucivanja = datumNarucivanja;
    }

    public BigDecimal getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(BigDecimal ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public NacinPlacanja getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(NacinPlacanja nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Konobar getKonobar() {
        return konobar;
    }

    public void setKonobar(Konobar konobar) {
        this.konobar = konobar;
    }

    public List<StavkaNarudzbine> getStavkeNarudzbine() {
        return stavkeNarudzbine;
    }

    public void setStavkeNarudzbine(List<StavkaNarudzbine> stavkeNarudzbine) {
        this.stavkeNarudzbine = stavkeNarudzbine;
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
        final Narudzbina other = (Narudzbina) obj;
        return this.idNarudzbina == other.idNarudzbina;
    }

    @Override
    public String vratiNazivTabele() {
        return "narudzbina";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) {
        List<OpstiDomenskiObjekat> listaNarudzbina = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Konobar konobar = new Konobar(resultSet.getInt("idKonobar"),
                        resultSet.getString("korisnickoIme"),
                        resultSet.getString("sifra"),
                        resultSet.getString("imeKonobara"),
                        resultSet.getString("prezimeKonobara"),
                        resultSet.getDate("datumZaposlenja"),
                        resultSet.getDate("datumIstekaUgovora"));

                TipGosta tipGosta = new TipGosta(resultSet.getInt("idtipgosta"),
                        resultSet.getString("nazivTipa"),
                        resultSet.getString("opisTipa"));

                Gost gost = new Gost(resultSet.getInt("idGost"),
                        resultSet.getString("imeGosta"),
                        resultSet.getString("prezimeGosta"),
                        tipGosta);

                Narudzbina narudzbina = new Narudzbina(resultSet.getInt("idNarudzbina"),
                        resultSet.getDate("datumNarucivanja"),
                        resultSet.getBigDecimal("ukupanIznos"),
                        NacinPlacanja.valueOf(resultSet.getString("nacinPlacanja")),
                        resultSet.getString("napomena"),
                        konobar,
                        gost);

                listaNarudzbina.add(narudzbina);
            }
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske prilikom ucitavanja liste narudzbina iz ResultSet-a.");
            ex.printStackTrace();
        }

        return listaNarudzbina;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idNarudzbina";
    }

    @Override
    public String vratiNaziveKolona() {
        return "datumNarucivanja, ukupanIznos, nacinPlacanja, napomena, idKonobar, idGost";
    }

    @Override
    public String vratiVrednostZaUnos() {
        return "'" + new java.sql.Date(datumNarucivanja.getTime()) + "', "
                + "'" + ukupanIznos + "', "
                + "'" + nacinPlacanja + "', "
                + "'" + napomena + "', "
                + "'" + konobar.getIdKonobar() + "', "
                + "'" + gost.getIdGost() + "'";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return "" + idNarudzbina;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "datumNarucivanja = '" + new java.sql.Date(datumNarucivanja.getTime()) + "', "
                + "ukupanIznos = '" + ukupanIznos + "', "
                + "nacinPlacanja = '" + nacinPlacanja + "', "
                + "napomena = '" + napomena + "', "
                + "idKonobar = '" + konobar.getIdKonobar() + "', "
                + "idGost = '" + gost.getIdGost() + "'";
    }

    @Override
    public String vratiAlijas() {
        return "n";
    }

    @Override
    public String vratiUslovZaJoin() {
        return "JOIN konobar k ON (n.idKonobar = k.idKonobar) "
                + "JOIN gost g ON (n.idGost = g.idGost) "
                + "JOIN tipGosta tg on (g.idTipGosta=tg.idTipGosta)";
    }

    @Override
    public String vratiUslov() {
        return "";
    }

}
