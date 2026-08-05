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
public class DodajGostaSO extends OpstaSistemskaOperacija {
    private Gost kreiraniGost;
    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
         if (parametar == null || !(parametar instanceof Gost)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Gost.");
        }
         Gost gost= (Gost) parametar;
         if(gost.getTipGosta()==null){
             throw new ServerskiIzuzetak("Tip gosta je obavezan.");
         }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
         try {            
            Gost gost=(Gost) parametar;
            int id=DBBroker.getInstanca().sacuvajObjekat(parametar);
            gost.setIdGost(id);
            kreiraniGost=gost;
            
        } catch (SQLException ex) {
            throw new ServerskiIzuzetak("Sistem ne moze da kreira gosta.");
        }
    }

    public Gost getKreiraniGost() {
        return kreiraniGost;
    }
    
}
