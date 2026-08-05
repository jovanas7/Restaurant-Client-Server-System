/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.narudzbina;

import db.DBBroker;
import domen.Narudzbina;
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
public class VratiSveNarudzbineSO extends OpstaSistemskaOperacija {
    List<Narudzbina> listaNarudzbina;
    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if(parametar==null || !(parametar instanceof Narudzbina)){
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Narudzbina.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws Exception {
        try{
        List<OpstiDomenskiObjekat> listaObjekata=DBBroker.getInstanca().vratiSveObjekte(parametar);
        listaNarudzbina=new ArrayList<>();
        for(OpstiDomenskiObjekat opstiDomenskiObjekat: listaObjekata){
            listaNarudzbina.add((Narudzbina) opstiDomenskiObjekat);
        }
        }catch(SQLException ex){
            throw new ServerskiIzuzetak("Sistem ne moze da ucita narudzbine.");
        }
    }

    public List<Narudzbina> getListaNarudzbina() {
        return listaNarudzbina;
    }
    
    
}
