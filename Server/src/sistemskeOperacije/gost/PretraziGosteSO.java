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
public class PretraziGosteSO extends OpstaSistemskaOperacija {

    private List<Gost> listaGostiju;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Gost)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Gost.");
        }
        Gost gost = (Gost) parametar;
        if ((gost.getImeGosta() == null || gost.getImeGosta().trim().isEmpty())
                && (gost.getPrezimeGosta() == null || gost.getPrezimeGosta().trim().isEmpty())
                && (gost.getTipGosta() == null)) {

            throw new ServerskiIzuzetak("Pretraga gosta zahteva bar jedan kriterijum tip ili ime gosta.");
        }

    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        try {
            List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(parametar);
            listaGostiju = new ArrayList<>();
            Gost gostZaPretragu = (Gost) parametar;

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                Gost gost = (Gost) opstiDomenskiObjekat;

                if (gostZaPretragu.getTipGosta() != null
                        && !gost.getTipGosta().equals(gostZaPretragu.getTipGosta())) {
                    continue;

                }
                if (gostZaPretragu.getImeGosta() != null
                        && !gostZaPretragu.getImeGosta().isEmpty()
                        && !gost.getImeGosta().toLowerCase()
                                .contains(gostZaPretragu.getImeGosta().toLowerCase())) {
                    continue;
                }
                listaGostiju.add(gost);

            }
        } catch (SQLException e) {
            throw new ServerskiIzuzetak("Sistem ne moze da nadje goste po zadatim kriterijumima.");
        }
    }

    public List<Gost> getListaGostiju() {
        return listaGostiju;
    }

}
