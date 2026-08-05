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
import java.util.ArrayList;
import java.util.List;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class VratiSveGosteSO extends OpstaSistemskaOperacija{
    List<Gost> listaGostiju;
    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Gost)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Gost.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        try {
            List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(parametar);
            listaGostiju = new ArrayList<>();

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                listaGostiju.add((Gost) opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da ucita goste.");
        }
    }

    public List<Gost> getListaGostiju() {
        return listaGostiju;
    }
    
}
