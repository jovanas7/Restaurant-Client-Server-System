/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Joska Stojanovic
 */
public class Konobar extends OpstiDomenskiObjekat {

    private int idKonobar;
    private String korisnickoIme;
    private String sifra;
    private String imeKonobara;
    private String prezimeKonobara;
    private Date datumZaposlenja;
    private Date datumIstekaUgovora;

    public Konobar() {
    }

    public Konobar(int idKonobar) {
        this.idKonobar = idKonobar;
    }

    public Konobar(int idKonobar, String korisnickoIme, String sifra, String imeKonobara, String prezimeKonobara, Date datumZaposlenja, Date datumIstekaUgovora) {
        this.idKonobar = idKonobar;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.imeKonobara = imeKonobara;
        this.prezimeKonobara = prezimeKonobara;
        this.datumZaposlenja = datumZaposlenja;
        this.datumIstekaUgovora = datumIstekaUgovora;
    }

    public Date getDatumIstekaUgovora() {
        return datumIstekaUgovora;
    }

    public void setDatumIstekaUgovora(Date datumIstekaUgovora) {
        this.datumIstekaUgovora = datumIstekaUgovora;
    }

    public int getIdKonobar() {
        return idKonobar;
    }

    public void setIdKonobar(int idKonobar) {
        this.idKonobar = idKonobar;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getImeKonobara() {
        return imeKonobara;
    }

    public void setImeKonobara(String imeKonobara) {
        this.imeKonobara = imeKonobara;
    }

    public String getPrezimeKonobara() {
        return prezimeKonobara;
    }

    public void setPrezimeKonobara(String prezimeKonobara) {
        this.prezimeKonobara = prezimeKonobara;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return imeKonobara + " " + prezimeKonobara;
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
        final Konobar other = (Konobar) obj;
        return this.idKonobar == other.idKonobar;
    }

    @Override
    public String vratiNazivTabele() {
        return "konobar";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) {
        List<OpstiDomenskiObjekat> listaKonobara = new ArrayList<>();

        try {
            while (resultSet.next()) {
                Konobar konobar = new Konobar(resultSet.getInt("idKonobar"),
                        resultSet.getString("korisnickoIme"),
                        resultSet.getString("sifra"),
                        resultSet.getString("imeKonobara"),
                        resultSet.getString("prezimeKonobara"),
                        resultSet.getDate("datumZaposlenja"),
                        resultSet.getDate("datumIstekaUgovora"));

                listaKonobara.add(konobar);
            }
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske prilikom ucitavanja liste konobara iz ResultSet-a.");
            ex.printStackTrace();
        }

        return listaKonobara;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "idKonobar";
    }

    @Override
    public String vratiNaziveKolona() {
        return "korisnickoIme, sifra, imeKonobara, prezimeKonobara, datumZaposlenja, datumIstekaUgovora";
    }

    @Override
    public String vratiVrednostZaUnos() {
        return "'" + korisnickoIme + "', "
                + "'" + sifra + "', "
                + "'" + imeKonobara + "', "
                + "'" + prezimeKonobara + "', "
                + "'" + datumZaposlenja + "', "
                + "'" + datumIstekaUgovora + "'";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return "" + idKonobar;
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "korisnickoIme = '" + korisnickoIme + "', "
                + "sifra = '" + sifra + "', "
                + "imeKonobara = '" + imeKonobara + "', "
                + "prezimeKonobara = '" + prezimeKonobara + "', "
                + "datumZaposlenja = '" + datumZaposlenja + "', "
                + "datumIstekaUgovora = '" + datumIstekaUgovora + "'";
    }

    @Override
    public String vratiAlijas() {
        return "k";
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
