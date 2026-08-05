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
import java.util.ArrayList;
import java.util.List;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class VratiSveKonobareSO extends OpstaSistemskaOperacija {
    private List<Konobar> listaKonobara;
    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if(parametar==null || !(parametar instanceof Konobar)){
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Konobar.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
         try {
            List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(parametar);
            listaKonobara = new ArrayList<>();

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                listaKonobara.add((Konobar) opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da ucita konobare.");
        }
    }

    public List<Konobar> getListaKonobara() {
        return listaKonobara;
    }
    
}
