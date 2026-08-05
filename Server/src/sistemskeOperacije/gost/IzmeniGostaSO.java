/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.gost;

import db.DBBroker;
import domen.Gost;
import domen.OpstiDomenskiObjekat;
import izuzeci.ServerskiIzuzetak;
import java.sql.SQLException;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class IzmeniGostaSO extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Gost)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Gost.");
        }
        Gost gost = (Gost) parametar;
        if (gost.getIdGost() <= 0) {
            throw new ServerskiIzuzetak("Gost mora imati validan ID.");
        }
        if (gost.getImeGosta() == null || gost.getImeGosta().trim().isEmpty()
                || gost.getPrezimeGosta() == null || gost.getPrezimeGosta().trim().isEmpty()
                || gost.getTipGosta() == null) {

            throw new ServerskiIzuzetak("Ime, prezime i tip gosta su obavezni!");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        try {
            DBBroker.getInstanca().izmeniObjekat(parametar);
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da zapamti gosta.");
        }
    }

}
