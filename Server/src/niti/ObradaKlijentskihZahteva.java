/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import domen.Artikal;
import domen.Gost;
import domen.Konobar;
import domen.Kvalifikacija;
import domen.Narudzbina;
import domen.TipGosta;
import java.net.Socket;
import java.util.List;
import konstante.Operacije;
import konstante.Status;
import kontroler.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.Posiljalac;
import transfer.Primalac;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Joska Stojanovic
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket soket;
    private List<ObradaKlijentskihZahteva> klijenti;

    public ObradaKlijentskihZahteva(Socket soket, List klijenti) {
        this.soket = soket;
        this.klijenti = klijenti;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                KlijentskiZahtev kz = (KlijentskiZahtev) new Primalac(soket, false).primi();
                ServerskiOdgovor so = obradiZahtev(kz);
                new Posiljalac(soket, false).posalji(so);
            }
        } catch (Exception ex) {
            System.out.println("Veza izmedju klijenta i servera ne postoji.");
        }
    }

    public Socket getSoket() {
        return soket;
    }

    private ServerskiOdgovor obradiZahtev(KlijentskiZahtev kz) {

        switch (kz.getOperacija()) {
            case Operacije.PRIJAVI_KONOBARA:
                return prijaviKonobara(kz);
            case Operacije.ODJAVI_KONOBARA:
                return odjaviKonobara(kz);
            case Operacije.VRATI_SVE_TIPOVE_GOSTA:
                return vratiSveTipoveGosta(kz);
            case Operacije.DODAJ_GOSTA:
                return dodajGosta(kz);
            case Operacije.DODAJ_KVALIFIKACIJU:
                return dodajKvalifikaciju(kz);
            case Operacije.VRATI_SVE_GOSTE:
                return vratiSveGoste(kz);
            case Operacije.PRETRAZI_GOSTE:
                return pretraziGoste(kz);
            case Operacije.VRATI_GOSTA:
                return vratiGosta(kz);
            case Operacije.OBRISI_GOSTA:
                return obrisiGosta(kz);
            case Operacije.IZMENI_GOSTA:
                return izmenaGosta(kz);
            case Operacije.VRATI_SVE_ARTIKLE:
                return vratiSveArtikle(kz);
            case Operacije.VRATI_SVE_KONOBARE:
                return vratiSveKonobare(kz);
            case Operacije.DODAJ_NARUDZBINU:
                return dodajNarudzbinu(kz);
            case Operacije.VRATI_SVE_NARUDZBINE:
                return vratiSveNarudzbine(kz);
            case Operacije.VRATI_NARUDZBINU:
                return vratiNarudzbinu(kz);
            case Operacije.PRETRAZI_NARUDZBINE:
                return pretraziNarudzbine(kz);
            case Operacije.IZMENI_NARUDZBINU:
                return izmeniNarudzbinu(kz);

        }
        return null;
    }

    private ServerskiOdgovor prijaviKonobara(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Konobar konobar = (Konobar) kz.getParametar();

        try {
            Konobar prijavljeniKonobar = Kontroler.getInstanca().prijaviKonobara(konobar);
            System.out.println("Uspesna prijava na sistem.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(prijavljeniKonobar);
        } catch (Exception ex) {
            ex.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(ex);
        }

        return so;
    }

    private ServerskiOdgovor odjaviKonobara(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Konobar konobar = (Konobar) kz.getParametar();

        try {
            Kontroler.getInstanca().odjaviKonobara(konobar);
            System.out.println("Uspesna odjava sa sistema.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(null);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor vratiSveTipoveGosta(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<TipGosta> tipovi;
        try {
            tipovi = Kontroler.getInstanca().vratiSveTipoveGosta();
            System.out.println("Uspesno ucitavanje svih tipova gosta");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(tipovi);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor dodajGosta(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Gost gost = (Gost) kz.getParametar();
        try {
            Gost kreiraniGost = Kontroler.getInstanca().dodajGosta(gost);
            System.out.println("Uspesno dodavanje novog gosta.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(kreiraniGost);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor dodajKvalifikaciju(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Kvalifikacija kvalifikacija = (Kvalifikacija) kz.getParametar();

        try {
            Kontroler.getInstanca().dodajKvalifikaciju(kvalifikacija);
            System.out.println("Uspesno dodavanje nove kvalifikacije");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(null);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor vratiSveGoste(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        List<Gost> gosti;
        try {
            gosti = Kontroler.getInstanca().vratiSveGoste();
            System.out.println("Uspesno ucitavanje svih gostiju");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(gosti);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor pretraziGoste(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Gost gost = (Gost) kz.getParametar();

        List<Gost> gosti;
        try {
            gosti = Kontroler.getInstanca().pretraziGoste(gost);
            System.out.println("Uspesna pretraga gostiju");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(gosti);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor vratiGosta(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Gost izabraniGost = (Gost) kz.getParametar();

        try {
            Gost gost = Kontroler.getInstanca().vratiGosta(izabraniGost);
            System.out.println("Uspesno ucitavanje izabranog gosta.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(gost);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }

        return so;
    }

    private ServerskiOdgovor obrisiGosta(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Gost gost = (Gost) kz.getParametar();

        try {
            Kontroler.getInstanca().obrisiGosta(gost);
            System.out.println("Uspesno brisanje izabranog gosta.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(null);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor izmenaGosta(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Gost gost = (Gost) kz.getParametar();

        try {
            Kontroler.getInstanca().izmeniGosta(gost);
            System.out.println("Uspesna izmena izabranog gosta.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(null);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor vratiSveArtikle(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();

        try {
            List<Artikal> artikli = Kontroler.getInstanca().vratiSveArtikle();
            System.out.println("Uspesno ucitavanje svih artikala.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(artikli);
        } catch (Exception ex) {
            ex.printStackTrace();
            so.setException(ex);
            so.setStatus(Status.NEUSPESNO);
        }
        return so;
    }

    private ServerskiOdgovor vratiSveKonobare(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();

        try {
            List<Konobar> konobari = Kontroler.getInstanca().vratiSveKonobare();
            System.out.println("Uspesno ucitavanje svih konobara.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(konobari);
        } catch (Exception ex) {
            ex.printStackTrace();
            so.setException(ex);
            so.setStatus(Status.NEUSPESNO);
        }
        return so;
    }

    private ServerskiOdgovor dodajNarudzbinu(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Narudzbina narudzbina=(Narudzbina) kz.getParametar();
        try{
            Kontroler.getInstanca().dodajNarudzbinu(narudzbina);
            System.out.println("Uspesno dodavanje nove narudzbine.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(null);
        }catch(Exception ex){
            ex.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(ex);
        }
        return so;
    }
    
    private ServerskiOdgovor vratiSveNarudzbine(KlijentskiZahtev kz) {
        ServerskiOdgovor so= new ServerskiOdgovor();
        List<Narudzbina> narudzbine;
        try{
            narudzbine= Kontroler.getInstanca().vratiSveNarudzbine();
            System.out.println("Uspesno ucitavanje svih narudzbina.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(narudzbine);
        }catch(Exception ex){
            ex.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(ex);
        }
        return so;
    }

    private ServerskiOdgovor vratiNarudzbinu(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Narudzbina izabranaNarudzbina=(Narudzbina) kz.getParametar();
        try {
            Narudzbina narudzbina=Kontroler.getInstanca().vratiNarudzbinu(izabranaNarudzbina);
            System.out.println("Uspesno ucitavanje narudzbine.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(narudzbina);
        } catch (Exception e) {
            e.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(e);
        }
        return so;
    }

    private ServerskiOdgovor pretraziNarudzbine(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Narudzbina narudzbina=(Narudzbina) kz.getParametar();
        
        List<Narudzbina> listaNarudzbina;
        try{
            listaNarudzbina=Kontroler.getInstanca().pretraziNarudzbine(narudzbina);
            System.out.println("Uspesna pretraga narudzbina.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(listaNarudzbina);
        }catch(Exception ex){
            ex.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(ex);
        }
        return so;
    }

    private ServerskiOdgovor izmeniNarudzbinu(KlijentskiZahtev kz) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        Narudzbina narudzbina=(Narudzbina) kz.getParametar();
        try{
            Kontroler.getInstanca().izmeniNarudzbinu(narudzbina);
            System.out.println("Uspesna izmena narudzbine.");
            so.setStatus(Status.USPESNO);
            so.setOdgovor(null);
        }catch(Exception ex){
            ex.printStackTrace();
            so.setStatus(Status.NEUSPESNO);
            so.setException(ex);
        }
        return so;
    }

}
