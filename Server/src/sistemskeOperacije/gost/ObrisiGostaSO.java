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
public class ObrisiGostaSO extends OpstaSistemskaOperacija {

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Gost)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Gost.");
        }
        Gost gost = (Gost) parametar;

        if (gost.getIdGost() <= 0) {
            throw new ServerskiIzuzetak("Gost mora imati ID.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        try {
            DBBroker.getInstanca().obrisiObjekat(parametar);
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da obrise gosta.");
        }
    }

}
