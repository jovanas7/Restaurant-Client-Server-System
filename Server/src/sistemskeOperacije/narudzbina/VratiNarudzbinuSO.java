/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistemskeOperacije.narudzbina;

import db.DBBroker;
import domen.Narudzbina;
import domen.OpstiDomenskiObjekat;
import domen.StavkaNarudzbine;
import izuzeci.ServerskiIzuzetak;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sistemskeOperacije.OpstaSistemskaOperacija;

/**
 *
 * @author Joska Stojanovic
 */
public class VratiNarudzbinuSO extends OpstaSistemskaOperacija {

    Narudzbina trazenaNarudzbina;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Narudzbina)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Narudzbina.");
        }
        Narudzbina narudzbina = (Narudzbina) parametar;
        if (narudzbina.getIdNarudzbina() <= 0) {
            throw new ServerskiIzuzetak("ID narudzbine nije validan.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        try {
            Narudzbina izabranaNarudzbina = (Narudzbina) parametar;
            List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(parametar);
            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                Narudzbina narudzbina = (Narudzbina) opstiDomenskiObjekat;

                if (izabranaNarudzbina.equals(narudzbina)) {
                    trazenaNarudzbina = narudzbina;
                    vratiStavkeNarudzbine(trazenaNarudzbina);
                    return;
                }
            }
            throw new Exception();
        } catch (Exception e) {
            throw new ServerskiIzuzetak("Sistem ne moze da nadje narudzbinu.");
        }
    }

    public Narudzbina getNarudzbina() {
        return trazenaNarudzbina;
    }

    private void vratiStavkeNarudzbine(Narudzbina trazenaNarudzbina) throws Exception {

        StavkaNarudzbine trazenaStavka = new StavkaNarudzbine();
        trazenaStavka.setNarudzbina(trazenaNarudzbina);
        List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(trazenaStavka);
        List<StavkaNarudzbine> listaStavki = new ArrayList<>();

        for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
            StavkaNarudzbine stavka = (StavkaNarudzbine) opstiDomenskiObjekat;
            listaStavki.add(stavka);
        }
        trazenaNarudzbina.setStavkeNarudzbine(listaStavki);

    }
}
