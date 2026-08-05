/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.gost;

import db.DBBroker;
import domen.Gost;
import domen.OpstiDomenskiObjekat;
import izuzeci.ServerskiIzuzetak;
import java.util.List;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class VratiGostaSO extends OpstaSistemskaOperacija {
    private Gost trazeniGost;
    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
         if (parametar == null || !(parametar instanceof Gost)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Gost.");
        }
         Gost gost=(Gost) parametar;
         if (gost.getIdGost() <= 0) {
            throw new ServerskiIzuzetak("Gost mora imati ID.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
         try {
            Gost izabraniGost = (Gost) parametar;
            List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(parametar);

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                Gost gost = (Gost) opstiDomenskiObjekat;

                if (izabraniGost.equals(gost)) {
                    trazeniGost = gost;
                    return;
                }
            }
            throw new Exception();
        } catch (Exception ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da nadje gosta.");
        }
    }

    public Gost getTrazeniGost() {
        return trazeniGost;
    }
    
    
}
