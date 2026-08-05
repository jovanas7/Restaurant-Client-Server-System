/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Artikal;
import java.math.BigDecimal;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joska Stojanovic
 */
public class ModelTabeleArtikal extends AbstractTableModel {

    List<Artikal> listaArtikala;
    String[] kolone = {"ID", "Naziv artikla", "Tip artikla", "Cena bez PDV-a", "Dostupan","Iznos PDV-a"};

    public ModelTabeleArtikal() {
        listaArtikala = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        if (listaArtikala == null) {
            return 0;
        }
        return listaArtikala.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Artikal artikal = listaArtikala.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return artikal.getIdArtikal();
            case 1:
                return artikal.getNazivArtikla();
            case 2:
                return artikal.getTipArtikla();
            case 3:
                return artikal.getCenaBezPdv();
            case 4:
                return artikal.isDostupan();
            case 5:
                return artikal.getPdv();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setListaArtikala(List<Artikal> listaArtikala) {
        this.listaArtikala = listaArtikala;
        fireTableDataChanged();
    }
    
    public Artikal vratiArtikal(int izabraniRed){
        return listaArtikala.get(izabraniRed);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case 0:
            return Integer.class;   
        case 1:
            return String.class;    
        case 2:
            return String.class;    
        case 3:
            return BigDecimal.class; 
        case 4:
            return Boolean.class;   
        case 5:
            return BigDecimal.class; 
        default:
            return Object.class;
    }
    }
    
}
