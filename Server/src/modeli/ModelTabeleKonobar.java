/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Konobar;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joska Stojanovic
 */
public class ModelTabeleKonobar extends AbstractTableModel {

    List<Konobar> listaUlogovanihKonobara;
    String[] kolone = {"ID", "Ime i prezime", "Korisnicko ime"};

    public ModelTabeleKonobar() {
        listaUlogovanihKonobara = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        if(listaUlogovanihKonobara==null)
            return 0;
        return listaUlogovanihKonobara.size();
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Konobar konobar=listaUlogovanihKonobara.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return konobar.getIdKonobar();
            case 1:
                return konobar.toString();
            case 2:
                return konobar.getKorisnickoIme();
            default:
                return "n/a";
        }
    }
    public void dodajUlogovanogKonobara(Konobar ulogovani){
        listaUlogovanihKonobara.add(ulogovani);
        fireTableDataChanged();
    }

    public void obrisiUlogovanogKonobara(Konobar ulogovani) {
        listaUlogovanihKonobara.remove(ulogovani);
        fireTableDataChanged();
    }
    
}
