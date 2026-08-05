/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeli;

import domen.Narudzbina;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Joska Stojanovic
 */
public class ModelTabeleNarudzbina extends AbstractTableModel {
    List<Narudzbina> listaNarudzbina;
    String[] kolone={"ID","Datum narucivanja","Gost","Ukupan iznos","Nacin placanja","Kreirao"};
    public ModelTabeleNarudzbina(){
        listaNarudzbina=new ArrayList<>();
    }
    @Override
    public int getRowCount() {
        if(listaNarudzbina==null)
            return 0;
        else return listaNarudzbina.size();
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
        Narudzbina narudzbina=listaNarudzbina.get(rowIndex);
        SimpleDateFormat sdf= new SimpleDateFormat("dd.MM.yyyy");
        
        switch(columnIndex){
            case 0:
                return narudzbina.getIdNarudzbina();
            case 1:
                return sdf.format(narudzbina.getDatumNarucivanja());
            case 2:
                return narudzbina.getGost();
            case 3:
                return narudzbina.getUkupanIznos();
            case 4:
                return narudzbina.getNacinPlacanja();
            case 5:
                return narudzbina.getKonobar();
            default:
                return "N/A";
        }
    }

    public List<Narudzbina> getListaNarudzbina() {
        return listaNarudzbina;
    }

    public void setListaNarudzbina(List<Narudzbina> listaNarudzbina) {
        this.listaNarudzbina = listaNarudzbina;
        fireTableDataChanged();
    }
  
    public Narudzbina vratiNarudzbinu(int red){
        return listaNarudzbina.get(red);
    }
}
