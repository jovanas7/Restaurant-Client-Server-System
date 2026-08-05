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
public class PretraziNarudzbineSO extends OpstaSistemskaOperacija {

    private List<Narudzbina> listaNarudzbina;

    @Override
    protected void proveriPreduslov(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        if (parametar == null || !(parametar instanceof Narudzbina)) {
            throw new ServerskiIzuzetak("Prosledjeni objekat nije instanca klase Narudzbina.");
        }
        Narudzbina n = (Narudzbina) parametar;
        if (n.getGost() == null && n.getKonobar() == null && n.getNacinPlacanja() == null) {
            throw new ServerskiIzuzetak("Pretraga narudžbina zahteva bar jedan kriterijum gost, konobar ili način plaćanja.");
        }
    }

    @Override
    protected void izvrsiKonkretnuOperaciju(OpstiDomenskiObjekat parametar) throws ServerskiIzuzetak {
        try {
            Narudzbina narudzbinaZaPretragu = (Narudzbina) parametar;
            List<OpstiDomenskiObjekat> listaObjekata = DBBroker.getInstanca().vratiSveObjekte(parametar);
            listaNarudzbina = new ArrayList<>();

            for (OpstiDomenskiObjekat opstiDomenskiObjekat : listaObjekata) {
                Narudzbina narudzbina = (Narudzbina) opstiDomenskiObjekat;

                if (narudzbinaZaPretragu.getGost() != null
                        && !narudzbina.getGost().equals(narudzbinaZaPretragu.getGost())) {
                    continue;
                }
                if (narudzbinaZaPretragu.getKonobar() != null
                        && !narudzbina.getKonobar().equals(narudzbinaZaPretragu.getKonobar())) {
                    continue;
                }
                if (narudzbinaZaPretragu.getNacinPlacanja() != null && !narudzbina.getNacinPlacanja().equals(narudzbinaZaPretragu.getNacinPlacanja())) {
                    continue;
                }

                listaNarudzbina.add(narudzbina);

            }
        } catch (SQLException e) {
            throw new ServerskiIzuzetak("Sistem ne moze da pronadje narudzbine po zadatim kriterijumima.");
        }
    }

    public List<Narudzbina> getListaNarudzbina() {
        return listaNarudzbina;
    }

}
