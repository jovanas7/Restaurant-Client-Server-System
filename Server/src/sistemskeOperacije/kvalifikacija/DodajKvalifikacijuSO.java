/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.kvalifikacija;

import db.DBBroker;
import domen.Kvalifikacija;
import domen.OpstiDomenskiObjekat;
import izuzeci.ServerskiIzuzetak;
import java.sql.SQLException;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class DodajKvalifikacijuSO extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Kvalifikacija)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Kvalifikacija.");
        }
        Kvalifikacija k = (Kvalifikacija) parametar;

        if (k.getNaziv() == null || k.getNaziv().trim().isEmpty()) {
            throw new ServerskiIzuzetak("Naziv kvalifikacije je obavezan.");
        }

        if (k.getOpis() == null || k.getOpis().trim().isEmpty()) {
            throw new ServerskiIzuzetak("Opis kvalifikacije je obavezan.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        try {
            DBBroker.getInstanca().sacuvajObjekat(parametar);
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da zapamti kvalifikaciju.");
        }
    }

}
