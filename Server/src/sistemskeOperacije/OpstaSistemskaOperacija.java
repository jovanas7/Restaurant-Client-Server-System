/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import izuzeci.ServerskiIzuzetak;

/**
 *
 * @author Joska Stojanovic
 */
public abstract class OpstaSistemskaOperacija {
    public synchronized void izvrsiOperaciju(OpstiDomenskiObjekat parametar) throws Exception{
        try {
            proveriPreduslov(parametar);
            otvoriKonekciju();
            izvrsiKonkretnuOperaciju(parametar);
            potvrdiTransakciju();
        } catch (ServerskiIzuzetak ex) {
            ponistiTransakciju();
            throw ex;
        }finally{
            zatvoriKonekciju();
        }
    }

    protected abstract void proveriPreduslov(OpstiDomenskiObjekat parametar)  throws ServerskiIzuzetak;

    private void otvoriKonekciju() throws ServerskiIzuzetak {
        DBBroker.getInstanca().otvoriKonekciju();
    }

    protected abstract void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws Exception ;

    private void potvrdiTransakciju() throws ServerskiIzuzetak {
        DBBroker.getInstanca().potvrdiTransakciju();
    }

    private void ponistiTransakciju() throws ServerskiIzuzetak {
        DBBroker.getInstanca().ponistiTransakciju();
    }

    private void zatvoriKonekciju() throws ServerskiIzuzetak {
        DBBroker.getInstanca().zatvoriKonekciju();
    }
     
        
  
}
