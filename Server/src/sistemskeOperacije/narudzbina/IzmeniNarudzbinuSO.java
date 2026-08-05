/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.narudzbina;

import db.DBBroker;
import domen.Narudzbina;
import domen.OpstiDomenskiObjekat;
import domen.StavkaNarudzbine;
import izuzeci.ServerskiIzuzetak;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class IzmeniNarudzbinuSO extends OpstaSistemskaOperacija {

    Narudzbina narudzbinaZaIzmenu;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Narudzbina)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Narudzbina.");
        }
        narudzbinaZaIzmenu = (Narudzbina) parametar;

        if (narudzbinaZaIzmenu.getIdNarudzbina() <= 0) {
            throw new ServerskiIzuzetak("Narudzbina mora imati validan ID.");
        }
        if (narudzbinaZaIzmenu.getUkupanIznos() == null || narudzbinaZaIzmenu.getUkupanIznos().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ServerskiIzuzetak("Ukupan iznos mora biti veci od 0.");
        }

        if (narudzbinaZaIzmenu.getNacinPlacanja() == null) {
            throw new ServerskiIzuzetak("Nacin placanja je obavezan.");
        }

        if (narudzbinaZaIzmenu.getGost() == null || narudzbinaZaIzmenu.getKonobar() == null) {
            throw new ServerskiIzuzetak("Gost i konobar su obavezni.");
        }
        if (narudzbinaZaIzmenu.getDatumNarucivanja() == null) {
            throw new ServerskiIzuzetak("Datum naručivanja je obavezan.");
        }

        if (narudzbinaZaIzmenu.getDatumNarucivanja().after(new Date())) {
            throw new ServerskiIzuzetak("Datum naručivanja ne može biti u budućnosti.");
        }

        if (narudzbinaZaIzmenu.getStavkeNarudzbine() == null || narudzbinaZaIzmenu.getStavkeNarudzbine().isEmpty()) {
            throw new ServerskiIzuzetak("Narudzbina mora imati bar jednu stavku.");
        }

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws Exception {
        try {
            DBBroker.getInstanca().izmeniObjekat(parametar);
            izmeniStavkeNarudzbine();
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da zapamti narudzbinu.");
        }
    }

    private void izmeniStavkeNarudzbine() throws Exception {
        StavkaNarudzbine stavka = new StavkaNarudzbine();
        stavka.setNarudzbina(narudzbinaZaIzmenu);
        List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(stavka);

        for (StavkaNarudzbine stavkaNarudzbine : narudzbinaZaIzmenu.getStavkeNarudzbine()) {
            stavkaNarudzbine.setNarudzbina(narudzbinaZaIzmenu);
            boolean postoji = false;

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                StavkaNarudzbine stavkaIzBaze = (StavkaNarudzbine) opstiDomenskiObjekat;

                if (stavkaIzBaze.getRb() == stavkaNarudzbine.getRb()) {
                    DBBroker.getInstanca().izmeniObjekat(stavkaNarudzbine);
                    postoji = true;
                    break;
                }
            }
            if (postoji == false) {
                DBBroker.getInstanca().sacuvajObjekat(stavkaNarudzbine);
            }
        }

        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
            StavkaNarudzbine stavkaIzBaze = (StavkaNarudzbine) opstiDomenskiObjekat;
            boolean zaBrisanje = true;

            for (StavkaNarudzbine stavkaNarudzbine : narudzbinaZaIzmenu.getStavkeNarudzbine()) {
                if (stavkaNarudzbine.getRb() == stavkaIzBaze.getRb()) {
                    zaBrisanje = false;
                    break;
                }
            }

            if (zaBrisanje == true) {
                DBBroker.getInstanca().obrisiObjekat(stavkaIzBaze);
            }
        }
    }

}
