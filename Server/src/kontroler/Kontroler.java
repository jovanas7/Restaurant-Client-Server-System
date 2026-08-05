/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import domen.Artikal;
import domen.Gost;
import domen.Konobar;
import domen.Kvalifikacija;
import domen.Narudzbina;
import domen.TipGosta;
import forme.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import sistemskeOperacije.artikal.VratiSveArtikleSO;
import sistemskeOperacije.kvalifikacija.DodajKvalifikacijuSO;
import sistemskeOperacije.gost.DodajGostaSO;
import sistemskeOperacije.gost.IzmeniGostaSO;
import sistemskeOperacije.gost.ObrisiGostaSO;
import sistemskeOperacije.gost.PretraziGosteSO;
import sistemskeOperacije.gost.VratiGostaSO;
import sistemskeOperacije.gost.VratiSveGosteSO;
import sistemskeOperacije.konobar.PrijaviKonobaraSO;
import sistemskeOperacije.konobar.VratiSveKonobareSO;
import sistemskeOperacije.narudzbina.DodajNarudzbinuSO;
import sistemskeOperacije.narudzbina.IzmeniNarudzbinuSO;
import sistemskeOperacije.narudzbina.PretraziNarudzbineSO;
import sistemskeOperacije.narudzbina.VratiNarudzbinuSO;
import sistemskeOperacije.narudzbina.VratiSveNarudzbineSO;
import sistemskeOperacije.tipGosta.VratiSveTipoveGostaSO;

/**
 *
 * @author Joska Stojanovic
 */
public class Kontroler {

    private static Kontroler instanca;
    private ServerskaForma serverskaForma;
    private List<Konobar> listaUlogovanihKonobara;
    private Kontroler() {
        listaUlogovanihKonobara= new ArrayList<>();
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public ServerskaForma getServerskaForma() {
        return serverskaForma;
    }

    public void setServerskaForma(ServerskaForma serverskaForma) {
        this.serverskaForma = serverskaForma;
    }

    public List<Konobar> getListaUlogovanihKonobara() {
        return listaUlogovanihKonobara;
    }
    public void ocistiListuUlogovanihKonobara(){
        if(listaUlogovanihKonobara!=null && !listaUlogovanihKonobara.isEmpty()){
            listaUlogovanihKonobara.clear();
        }
    }
    public Konobar prijaviKonobara(Konobar konobar) throws Exception {
        PrijaviKonobaraSO prijaviKonobaraSO= new PrijaviKonobaraSO();
        prijaviKonobaraSO.izvrsiOperaciju(konobar);
        
        return prijaviKonobaraSO.getUlogovaniKonobar();
    }

    public void odjaviKonobara(Konobar konobar) {
        listaUlogovanihKonobara.remove(konobar);
        serverskaForma.vratiTabeluUlogovanihKonobara().obrisiUlogovanogKonobara(konobar);
    }

    public List<TipGosta> vratiSveTipoveGosta() throws Exception {
        VratiSveTipoveGostaSO vratiSveTipoveGostaSO= new VratiSveTipoveGostaSO();
        vratiSveTipoveGostaSO.izvrsiOperaciju(new TipGosta());
        
        return vratiSveTipoveGostaSO.getListaTipova();
    }

    public Gost dodajGosta(Gost gost) throws Exception {
        DodajGostaSO dodajGostaSO= new DodajGostaSO();
        dodajGostaSO.izvrsiOperaciju(gost);
        
        return dodajGostaSO.getKreiraniGost();
    }

    public void dodajKvalifikaciju(Kvalifikacija kvalifikacija) throws Exception {
        DodajKvalifikacijuSO dodajKvalifikacijuSO= new DodajKvalifikacijuSO();
        dodajKvalifikacijuSO.izvrsiOperaciju(kvalifikacija);
    }

    public List<Gost> vratiSveGoste() throws Exception {
        VratiSveGosteSO vratiSveGosteSO= new VratiSveGosteSO();
        vratiSveGosteSO.izvrsiOperaciju(new Gost());
        
        return vratiSveGosteSO.getListaGostiju();
    }

    public List<Gost> pretraziGoste(Gost gost) throws Exception {
        PretraziGosteSO pretraziGosteSO= new PretraziGosteSO();
        pretraziGosteSO.izvrsiOperaciju(gost);
        
        return pretraziGosteSO.getListaGostiju();
    }

    public void obrisiGosta(Gost gost) throws Exception {
        ObrisiGostaSO obrisiGostaSO= new ObrisiGostaSO();
        obrisiGostaSO.izvrsiOperaciju(gost);
    }

    public Gost vratiGosta(Gost gost) throws Exception {
        VratiGostaSO vratiGostaSO= new VratiGostaSO();
        vratiGostaSO.izvrsiOperaciju(gost);
        
        return vratiGostaSO.getTrazeniGost();
    }

    public void izmeniGosta(Gost gost) throws Exception {
        IzmeniGostaSO izmeniGostaSO= new IzmeniGostaSO();
        izmeniGostaSO.izvrsiOperaciju(gost);
    }

    public List<Artikal> vratiSveArtikle() throws Exception {
        VratiSveArtikleSO vratiSveArtikleSO= new VratiSveArtikleSO();
        vratiSveArtikleSO.izvrsiOperaciju(new Artikal());
        
        return vratiSveArtikleSO.getListaArtikala();
    }

    public List<Konobar> vratiSveKonobare() throws Exception {
        VratiSveKonobareSO vratiSveKonobareSO= new VratiSveKonobareSO();
        vratiSveKonobareSO.izvrsiOperaciju(new Konobar());
        
        return vratiSveKonobareSO.getListaKonobara();
    }

    public void dodajNarudzbinu(Narudzbina narudzbina) throws Exception {
        DodajNarudzbinuSO dodajNarudzbinuSO= new DodajNarudzbinuSO();
        dodajNarudzbinuSO.izvrsiOperaciju(narudzbina);
    }

    public List<Narudzbina> vratiSveNarudzbine() throws Exception {
        VratiSveNarudzbineSO vratiSveNarudzbineSO = new VratiSveNarudzbineSO();
        vratiSveNarudzbineSO.izvrsiOperaciju(new Narudzbina());
        
        return vratiSveNarudzbineSO.getListaNarudzbina();
    }

    public Narudzbina vratiNarudzbinu(Narudzbina narudzbina) throws Exception {
        VratiNarudzbinuSO vratiNarudzbinuSO= new VratiNarudzbinuSO();
        vratiNarudzbinuSO.izvrsiOperaciju(narudzbina);
        
        return vratiNarudzbinuSO.getNarudzbina();
    }

    public List<Narudzbina> pretraziNarudzbine(Narudzbina narudzbina) throws Exception {
        PretraziNarudzbineSO pretraziNarudzbineSO= new PretraziNarudzbineSO();
        pretraziNarudzbineSO.izvrsiOperaciju(narudzbina);
        
        return pretraziNarudzbineSO.getListaNarudzbina();
    }

    public void izmeniNarudzbinu(Narudzbina narudzbina) throws Exception {
        IzmeniNarudzbinuSO izmeniNarudzbinuSO= new IzmeniNarudzbinuSO();
        izmeniNarudzbinuSO.izvrsiOperaciju(narudzbina);
    }
    
}
