/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.Narudzbina;
import izuzeci.GasenjeProgramaIzuzetak;
import java.io.IOException;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.Posiljalac;
import transfer.Primalac;
import transfer.ServerskiOdgovor;

/**
 *
 * @author Joska Stojanovic
 */
public class KomunikacijaSaServerom {

    private static KomunikacijaSaServerom instanca;
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;

    private KomunikacijaSaServerom() throws GasenjeProgramaIzuzetak {
        try {
            soket = new Socket("localhost", 9000);
            posiljalac = new Posiljalac(soket, true);
            primalac = new Primalac(soket, true);
            System.out.println("Klijent se povezao!");
        } catch (IOException e) {
            throw new GasenjeProgramaIzuzetak("Server je ugasen.");
        }
    }

    public static KomunikacijaSaServerom getInstanca() throws GasenjeProgramaIzuzetak {
        if (instanca == null) {
            instanca = new KomunikacijaSaServerom();
        }
        return instanca;
    }

    public ServerskiOdgovor prijaviKonobara(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za prijavu na sistem je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor odjaviFarmaceuta(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za odjavu sa sistema je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor vratiSveTipoveGosta(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za ucitavanje svih tipova gostiju je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor dodajGosta(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za dodavanje novog gosta je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor dodajKvalifikaciju(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za dodavanje nove kvalifikacije je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor vratiSveGoste(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za ucitavanje svih gostiju je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor pretraziGoste(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za pretragu gostiju je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor vratiGosta(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za ucitavanje gosta je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor obrisiGosta(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za brisanje gosta je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor izmeniGosta(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za izmenu gosta je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor vratiSveArtikle(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za ucitavanje svih artikala je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor vratiSveKonobare(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za ucitavanje svih konobara je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor dodajNarudzbinu(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za dodavanje nove narudzbine je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor vratiSveNarudzbine(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za ucitavanje narudzbina je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor vratiNarudzbinu(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za ucitavanje narudzbine je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor pretraziNarudzbine(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za pretragu narudzbina je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

    public ServerskiOdgovor izmeniNarudzbinu(KlijentskiZahtev kz) throws Exception {
        posiljalac.posalji(kz);
        System.out.println("Zahtev za izmenu narudzbine je poslat..");
        return (ServerskiOdgovor) primalac.primi();
    }

}
