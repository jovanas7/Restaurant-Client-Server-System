/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Artikal;
import domen.StavkaNarudzbine;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joska Stojanovic
 */
public class ModelTabeleStavkaNarudzbine extends AbstractTableModel {

    List<StavkaNarudzbine> listaStavki;
    String[] kolone = {"RB", "Naziv artikla", "Cena bez PDV-a", "Cena sa PDV-om", "Kolicina", "Ukupna vrednost"};
    JTextField txtUkupanIznos;

    public ModelTabeleStavkaNarudzbine() {
        listaStavki = new ArrayList<>();
    }

    public void setTxtUkupanIznos(JTextField txtUkupanIznos) {
        this.txtUkupanIznos = txtUkupanIznos;
    }

    @Override
    public int getRowCount() {
        if (listaStavki == null) {
            return 0;
        }
        return listaStavki.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (columnIndex == 4) {
            return true;
        }
        return false;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaNarudzbine stavka = listaStavki.get(rowIndex);
        String unetaKolicina = (String) aValue;

        try {
            int kolicina = Integer.parseInt(unetaKolicina);
            stavka.setKolicina(kolicina);
            stavka.setUkupnaVrednost(stavka.getCenaSaPdv().multiply(BigDecimal.valueOf(stavka.getKolicina())));
            
            postaviUkupanIznos();
            fireTableDataChanged();
        } catch (NumberFormatException e) {
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaNarudzbine stavka = listaStavki.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return stavka.getRb();
            case 1:
                return stavka.getArtikal().getNazivArtikla();
            case 2:
                return stavka.getArtikal().getCenaBezPdv();
            case 3:
                return stavka.getCenaSaPdv();
            case 4:
                return stavka.getKolicina();
            case 5:
                return stavka.getUkupnaVrednost();
            default:
                return "N/A";
        }
    }
    
    private void postaviUkupanIznos() {
        BigDecimal ukupanIznos = BigDecimal.ZERO;
        for (StavkaNarudzbine stavka : listaStavki) {
            ukupanIznos = ukupanIznos.add(stavka.getUkupnaVrednost());
        }

        if (ukupanIznos.compareTo(BigDecimal.ZERO) == 0) {
            txtUkupanIznos.setText("");
        } else {
            txtUkupanIznos.setText(ukupanIznos.toString());
        }
    } 
    
    public List<StavkaNarudzbine> getListaStavki() {
        return listaStavki;
    }

    public void dodajListuStavkiNarudzbina(List<StavkaNarudzbine> listaStavkiNarudzbine) {
        this.listaStavki = listaStavkiNarudzbine;
        postaviUkupanIznos();

        fireTableDataChanged();
    }

    public void dodajStavku(Artikal izabraniArtikal) {
        StavkaNarudzbine stavka = daLiPostojiUTabeli(izabraniArtikal);
        if (stavka != null) {
            stavka.setKolicina(stavka.getKolicina() + 1);
             stavka.setUkupnaVrednost(stavka.getCenaSaPdv().multiply(BigDecimal.valueOf(stavka.getKolicina())));
        } else {
            stavka = new StavkaNarudzbine();
            stavka.setRb(listaStavki.size() + 1);
            stavka.setArtikal(izabraniArtikal);
            stavka.setCenaSaPdv(
                    izabraniArtikal.getCenaBezPdv().multiply(
                            BigDecimal.ONE.add(izabraniArtikal.getPdv()
                                    .divide(BigDecimal.valueOf(100))))
                            .setScale(2, RoundingMode.HALF_UP)
            );
            stavka.setKolicina(1);
            stavka.setUkupnaVrednost(stavka.getCenaSaPdv().multiply(BigDecimal.valueOf(stavka.getKolicina())));

            listaStavki.add(stavka);
        }
        postaviUkupanIznos();
        fireTableDataChanged();
    }

    private StavkaNarudzbine daLiPostojiUTabeli(Artikal izabraniArtikal) {
        for (StavkaNarudzbine stavka : listaStavki) {
            if (stavka.getArtikal().equals(izabraniArtikal)) {
                return stavka;
            }
        }
        return null;
    }

    public void obrisiStavku(int izabraniRed) {
        listaStavki.remove(izabraniRed);
        srediRb();
        postaviUkupanIznos();
        fireTableDataChanged();
    }

    private void srediRb() {
        int rb=1;
        for(StavkaNarudzbine stavka:listaStavki){
            stavka.setRb(rb);
            rb++;
        }
    }
}
