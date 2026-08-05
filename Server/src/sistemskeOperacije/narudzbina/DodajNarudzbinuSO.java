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
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class DodajNarudzbinuSO extends OpstaSistemskaOperacija {

    private Narudzbina novaNarudzbina;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Narudzbina)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Narudzbina.");
        }

        Narudzbina n = (Narudzbina) parametar;

        if (n.getDatumNarucivanja() == null || n.getDatumNarucivanja().after(new Date())) {
            throw new ServerskiIzuzetak("Datum narucivanja je obavezan i ne sme biti u buducnosti.");
        }

        if (n.getUkupanIznos() == null || n.getUkupanIznos().compareTo(BigDecimal.ZERO) <= 0) {
            throw new ServerskiIzuzetak("Ukupan iznos mora biti veci od 0.");
        }

        if (n.getNacinPlacanja() == null) {
            throw new ServerskiIzuzetak("Nacin placanja je obavezan.");
        }

        if (n.getGost() == null || n.getKonobar() == null) {
            throw new ServerskiIzuzetak("Gost i konobar su obavezni.");
        }

        if (n.getStavkeNarudzbine() == null || n.getStavkeNarudzbine().isEmpty()) {
            throw new ServerskiIzuzetak("Narudzbina mora imati bar jednu stavku.");
        }

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws Exception {
        try {
            novaNarudzbina = (Narudzbina) parametar;
            int id = DBBroker.getInstanca().sacuvajObjekat(parametar);
            novaNarudzbina.setIdNarudzbina(id);
            sacuvajStavkeNarudzbine();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ServerskiIzuzetak("Sistem ne moze da zapamti narudzbinu.");
        }

    }

    private void sacuvajStavkeNarudzbine() throws Exception {
        for (StavkaNarudzbine stavka : novaNarudzbina.getStavkeNarudzbine()) {
            stavka.setNarudzbina(novaNarudzbina);
            DBBroker.getInstanca().sacuvajObjekat(stavka);
        }
    }

}
