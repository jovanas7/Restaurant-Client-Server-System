/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Gost;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joska Stojanovic
 */
public class ModelTabeleGost extends AbstractTableModel {
    List<Gost> listaGostiju;
    String [] kolone={"ID","Ime","Prezime"};
    
    public ModelTabeleGost(){
        listaGostiju=new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        if(listaGostiju==null)
            return 0;
        return listaGostiju.size();
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
        Gost gost= listaGostiju.get(rowIndex);
        switch(columnIndex){
            case 0:
                return gost.getIdGost();
            case 1:
                return gost.getImeGosta();
            case 2:
                return gost.getPrezimeGosta();
            default:
                return "n/a";
        }
    }
    public Gost vratiGosta(int red){
        return listaGostiju.get(red);
    }
    public void setListaGostiju(List<Gost> listaGostiju){
        this.listaGostiju=listaGostiju;
        fireTableDataChanged();
    }
    
}
