/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.tipGosta;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.TipGosta;
import izuzeci.ServerskiIzuzetak;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class VratiSveTipoveGostaSO extends OpstaSistemskaOperacija {

    private List<TipGosta> listaTipova;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof TipGosta)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase TipGosta.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
         try {
            List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(parametar);
            listaTipova = new ArrayList<>();

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                listaTipova.add((TipGosta) opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da ucita tipove gosta.");
        }
    }

    public List<TipGosta> getListaTipova() {
        return listaTipova;
    }

}
