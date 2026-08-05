/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.artikal;

import db.DBBroker;
import domen.Artikal;
import domen.OpstiDomenskiObjekat;
import izuzeci.ServerskiIzuzetak;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class VratiSveArtikleSO extends OpstaSistemskaOperacija {

    List<Artikal> listaArtikala;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Artikal)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Artikal.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        try {
            List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(parametar);
            listaArtikala = new ArrayList<>();

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                listaArtikala.add((Artikal) opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da ucita artikle.");
        }
    }

    public List<Artikal> getListaArtikala() {
        return listaArtikala;
    }
}
