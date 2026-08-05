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
import java.util.Objects;

/**
 *
 * @author Joska Stojanovic
 */
public class StavkaNarudzbine extends OpstiDomenskiObjekat {

    private Narudzbina narudzbina;
    private int rb;
    private int kolicina;
    private BigDecimal cenaSaPdv;
    private BigDecimal ukupnaVrednost;
    private Artikal artikal;

    public StavkaNarudzbine() {
    }

    public StavkaNarudzbine(Narudzbina narudzbina, int rb, int kolicina, BigDecimal cenaSaPdv, 
            BigDecimal ukupnaVrednost, Artikal artikal) {
        this.narudzbina = narudzbina;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cenaSaPdv = cenaSaPdv;
        this.ukupnaVrednost = ukupnaVrednost;
        this.artikal = artikal;
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public Narudzbina getNarudzbina() {
        return narudzbina;
    }

    public void setNarudzbina(Narudzbina narudzbina) {
        this.narudzbina = narudzbina;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public BigDecimal getCenaSaPdv() {
        return cenaSaPdv;
    }

    public void setCenaSaPdv(BigDecimal cenaSaPdv) {
        this.cenaSaPdv = cenaSaPdv;
    }

    public BigDecimal getUkupnaVrednost() {
        return ukupnaVrednost;
    }

    public void setUkupnaVrednost(BigDecimal ukupnaVrednost) {
        this.ukupnaVrednost = ukupnaVrednost;
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
        final StavkaNarudzbine other = (StavkaNarudzbine) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.narudzbina, other.narudzbina);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavkaNarudzbine";
    }

    @Override
    public List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet) {
        List<OpstiDomenskiObjekat> listaStavkiNarudzbine = new ArrayList<>();

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
                Artikal artikal = new Artikal(resultSet.getInt("idArtikal"),
                        resultSet.getString("nazivArtikla"),
                        TipArtikla.valueOf(resultSet.getString("tipArtikla")),
                        resultSet.getBigDecimal("cenaBezPdv"),
                        resultSet.getBoolean("dostupan"),
                        resultSet.getBigDecimal("pdv"));

                StavkaNarudzbine stavkaNarudzbine = new StavkaNarudzbine(narudzbina,
                        resultSet.getInt("rb"),
                        resultSet.getInt("kolicina"),
                        resultSet.getBigDecimal("cenaSaPdv"),
                        resultSet.getBigDecimal("ukupnaVrednost"),
                        artikal);

                listaStavkiNarudzbine.add(stavkaNarudzbine);
            }
        } catch (SQLException ex) {
            System.out.println("Doslo je do greske prilikom ucitavanja liste stavki narudzbine iz ResultSet-a.");
            ex.printStackTrace();
        }

        return listaStavkiNarudzbine;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "(idNarudzbina, rb)";
    }

    @Override
    public String vratiNaziveKolona() {
        return "idNarudzbina, rb, kolicina, cenaSaPdv, ukupnaVrednost, idArtikal";
    }

    @Override
    public String vratiVrednostZaUnos() {
        return "'" + narudzbina.getIdNarudzbina() + "', "
                + "'" + rb + "', "
                + "'" + kolicina + "', "
                + "'" + cenaSaPdv + "', "
                + "'" + ukupnaVrednost + "', "
                + "'" + artikal.getIdArtikal() + "'";
    }

    @Override
    public String vratiVrednostPrimarnogKljuca() {
        return "(" + narudzbina.getIdNarudzbina() + ", " + rb + ")";
    }

    @Override
    public String vratiVrednostZaIzmenu() {
        return "rb = '" + rb + "', "
                + "kolicina = '" + kolicina + "', "
                + "cenaSaPdv = '" + cenaSaPdv + "', "
                + "ukupnaVrednost = '" + ukupnaVrednost + "', "
                + "idArtikal = '" + artikal.getIdArtikal() + "'";
    }

    @Override
    public String vratiAlijas() {
        return "sn";
    }

    @Override
    public String vratiUslovZaJoin() {
        return "JOIN artikal a ON (sn.idArtikal = a.idArtikal) "
                + "JOIN narudzbina n ON (sn.idNarudzbina = n.idNarudzbina) "
                + "JOIN konobar k ON (n.idKonobar = k.idKonobar) "
                + "JOIN gost g ON (n.idGost = g.idGost) "
                + "JOIN tipGosta tg ON (g.idTipGosta = tg.idTipGosta)";
    }

    @Override
    public String vratiUslov() {
        return "WHERE sn.idNarudzbina = " + narudzbina.getIdNarudzbina();
    }

}
