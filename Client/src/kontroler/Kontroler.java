/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Artikal;
import domen.Gost;
import domen.Konobar;
import domen.Kvalifikacija;
import domen.NacinPlacanja;
import domen.Narudzbina;
import domen.TipGosta;
import java.util.List;
import komunikacija.KomunikacijaSaServerom;
import konstante.Operacije;
import konstante.Status;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Joska Stojanovic
 */
public class Kontroler {

    private static Kontroler instanca;

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    private Kontroler() {

    }

    public Konobar prijaviKonobara(String korisnickoIme, String sifra) throws Exception {
        Konobar konobar = new Konobar();
        konobar.setKorisnickoIme(korisnickoIme);
        konobar.setSifra(sifra);

        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.PRIJAVI_KONOBARA);
        kz.setParametar(konobar);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().prijaviKonobara(kz);

        if (so.getStatus().equals(Status.USPESNO)) {
            Konobar prijavljeniKonobar = (Konobar) so.getOdgovor();
            return prijavljeniKonobar;
        } else {
            throw so.getException();
        }
    }

    public void odjaviKonobara(Konobar konobar) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.ODJAVI_KONOBARA);
        kz.setParametar(konobar);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().odjaviFarmaceuta(kz);

        if (so.getStatus().equals(Status.NEUSPESNO)) {
            throw so.getException();
        }
    }

    public List<TipGosta> vratiSveTipoveGosta() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_SVE_TIPOVE_GOSTA);
        kz.setParametar(null);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().vratiSveTipoveGosta(kz);

        if (so.getStatus().equals(Status.USPESNO)) {
            List<TipGosta> tipovi = (List<TipGosta>) so.getOdgovor();
            return tipovi;
        } else {
            throw so.getException();
        }
    }

    public Gost dodajGosta(Gost gost) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.DODAJ_GOSTA);
        kz.setParametar(gost);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().dodajGosta(kz);
        if (so.getStatus().equals(Status.USPESNO)) {
            return (Gost) so.getOdgovor();
        } else {
            throw so.getException();
        }
    }

    public void dodajKvalifikaciju(Kvalifikacija kvalifikacija) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.DODAJ_KVALIFIKACIJU);
        kz.setParametar(kvalifikacija);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().dodajKvalifikaciju(kz);

        if (so.getStatus().equals(Status.NEUSPESNO)) {
            throw so.getException();
        }
    }

    public List<Gost> vratiSveGoste() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_SVE_GOSTE);
        kz.setParametar(null);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().vratiSveGoste(kz);
        if (so.getStatus().equals(Status.USPESNO)) {
            List<Gost> gosti = (List<Gost>) so.getOdgovor();
            return gosti;
        } else {
            throw so.getException();
        }
    }

    public List<Gost> pretraziGoste(TipGosta tipGosta, String ime) throws Exception {
        Gost gost = new Gost();
        gost.setTipGosta(tipGosta);
        gost.setImeGosta(ime);

        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.PRETRAZI_GOSTE);
        kz.setParametar(gost);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().pretraziGoste(kz);

        if (so.getStatus().equals(Status.USPESNO)) {
            List<Gost> gosti = (List<Gost>) so.getOdgovor();
            return gosti;
        } else {
            throw so.getException();
        }
    }

    public void obrisiGosta(int idGost) throws Exception {
        Gost gostZaBrisanje = new Gost();
        gostZaBrisanje.setIdGost(idGost);

        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.OBRISI_GOSTA);
        kz.setParametar(gostZaBrisanje);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().obrisiGosta(kz);
        if (so.getStatus().equals(Status.NEUSPESNO)) {
            throw so.getException();
        }
    }

    public Gost vratiGosta(int idGost) throws Exception {
        Gost izabraniGost = new Gost();
        izabraniGost.setIdGost(idGost);

        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_GOSTA);
        kz.setParametar(izabraniGost);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().vratiGosta(kz);
        if (so.getStatus().equals(Status.USPESNO)) {
            Gost gost = (Gost) so.getOdgovor();
            return gost;
        } else {
            throw so.getException();
        }
    }

    public void izmeniGosta(Gost gost) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.IZMENI_GOSTA);
        kz.setParametar(gost);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().izmeniGosta(kz);
        if (so.getStatus().equals(Status.NEUSPESNO)) {
            throw so.getException();
        }
    }

    public List<Artikal> vratiSveArtikle() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_SVE_ARTIKLE);
        kz.setParametar(null);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().vratiSveArtikle(kz);
        if (so.getStatus().equals(Status.USPESNO)) {
            List<Artikal> artikli = (List<Artikal>) so.getOdgovor();
            return artikli;
        } else {
            throw so.getException();
        }
    }

    public List<Konobar> vratiSveKonobare() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_SVE_KONOBARE);
        kz.setParametar(null);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().vratiSveKonobare(kz);
        if (so.getStatus().equals(Status.USPESNO)) {
            List<Konobar> sviKonobari = (List<Konobar>) so.getOdgovor();
            return sviKonobari;
        } else {
            throw so.getException();
        }
    }

    public void dodajNarudzbinu(Narudzbina narudzbina) throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.DODAJ_NARUDZBINU);
        kz.setParametar(narudzbina);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().dodajNarudzbinu(kz);
        if (so.getStatus().equals(Status.NEUSPESNO)) {
            throw so.getException();
        }
    }

    public List<Narudzbina> vratiSveNarudzbine() throws Exception {
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_SVE_NARUDZBINE);
        kz.setParametar(null);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().vratiSveNarudzbine(kz);

        if (so.getStatus().equals(Status.USPESNO)) {
            List<Narudzbina> narudzbine = (List<Narudzbina>) so.getOdgovor();
            return narudzbine;
        } else {
            throw so.getException();
        }
    }

    public Narudzbina vratiNarudzbinu(int idNarudzbina) throws Exception {
        Narudzbina izabranaNarudzbina = new Narudzbina();
        izabranaNarudzbina.setIdNarudzbina(idNarudzbina);
        KlijentskiZahtev kz = new KlijentskiZahtev();
        kz.setOperacija(Operacije.VRATI_NARUDZBINU);
        kz.setParametar(izabranaNarudzbina);

        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().vratiNarudzbinu(kz);
        if (so.getStatus().equals(Status.USPESNO)) {
            Narudzbina narudzbina = (Narudzbina) so.getOdgovor();
            return narudzbina;
        } else {
            throw so.getException();
        }
    }

    public List<Narudzbina> pretraziNarudzbinu(Gost izabraniGost, Konobar izabraniKonobar, NacinPlacanja izabraniNacin) throws Exception {
        Narudzbina narudzbina = new Narudzbina();
        narudzbina.setGost(izabraniGost);
        narudzbina.setKonobar(izabraniKonobar);
        narudzbina.setNacinPlacanja(izabraniNacin);
        
        KlijentskiZahtev kz= new KlijentskiZahtev();
        kz.setOperacija(Operacije.PRETRAZI_NARUDZBINE);
        kz.setParametar(narudzbina);
        
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().pretraziNarudzbine(kz);
        
        if(so.getStatus().equals(Status.USPESNO)){
            List<Narudzbina> listaNarudzbina=(List<Narudzbina>) so.getOdgovor();
            return listaNarudzbina;
        }else{
            throw so.getException();
        }
    }

    public void izmeniNarudzbinu(Narudzbina narudzbina) throws Exception {
        KlijentskiZahtev kz= new KlijentskiZahtev();
        kz.setOperacija(Operacije.IZMENI_NARUDZBINU);
        kz.setParametar(narudzbina);
        
        ServerskiOdgovor so = KomunikacijaSaServerom.getInstanca().izmeniNarudzbinu(kz);
        if(so.getStatus().equals(Status.NEUSPESNO)){
            throw so.getException();
        }
    }
}
