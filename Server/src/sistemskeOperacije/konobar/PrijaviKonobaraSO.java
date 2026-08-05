/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.konobar;

import db.DBBroker;
import domen.Konobar;
import domen.OpstiDomenskiObjekat;
import izuzeci.ServerskiIzuzetak;
import java.sql.SQLException;
import java.util.List;
import kontroler.Kontroler;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class PrijaviKonobaraSO extends OpstaSistemskaOperacija {

    private Konobar ulogovaniKonobar;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Konobar)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Konobar");
        }
        Konobar konobar = (Konobar) parametar;
        if (konobar.getKorisnickoIme() == null || konobar.getKorisnickoIme().trim().isEmpty()) {
            throw new ServerskiIzuzetak("Korisnicko ime je obavezno.");
        }

        if (konobar.getSifra() == null || konobar.getSifra().trim().isEmpty()) {
            throw new ServerskiIzuzetak("Sifra je obavezna.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {

        try {
            Konobar unetiKonobar = (Konobar) parametar;
            List<OpstiDomenskiObjekat> listaKonobara = DBBroker.getInstanca().vratiSveObjekte(parametar);

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaKonobara) {
                Konobar konobar = (Konobar) opstiDomenskiObjekat;
                if (unetiKonobar.getKorisnickoIme().equals(konobar.getKorisnickoIme())
                        && unetiKonobar.getSifra().equals(konobar.getSifra())) {
                    if (Kontroler.getInstanca().getListaUlogovanihKonobara().contains(konobar)) {
                        throw new ServerskiIzuzetak("Konobar sa unetim kredencijalima je vec ulogovan!");
                    } else {
                        ulogovaniKonobar = konobar;
                        Kontroler.getInstanca().getListaUlogovanihKonobara().add(ulogovaniKonobar);
                        Kontroler.getInstanca().getServerskaForma().vratiTabeluUlogovanihKonobara().dodajUlogovanogKonobara(ulogovaniKonobar);
                        return;
                    }
                }
            }
            throw new ServerskiIzuzetak("Korisnicko ime i/ili sifra nisu ispravni.");
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Doslo je do greske u bazi podataka.");
        }
    }

    public Konobar getUlogovaniKonobar() {
        return ulogovaniKonobar;
    }

}
