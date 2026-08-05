/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Joska Stojanovic
 */
public abstract class OpstiDomenskiObjekat implements Serializable {
     public abstract String vratiNazivTabele();

    public abstract List<OpstiDomenskiObjekat> vratiListu(ResultSet resultSet);

    public abstract String vratiPrimarniKljuc();

    public abstract String vratiNaziveKolona();

    public abstract String vratiVrednostZaUnos();

    public abstract String vratiVrednostPrimarnogKljuca();

    public abstract String vratiVrednostZaIzmenu();

    public abstract String vratiAlijas();

    public abstract String vratiUslovZaJoin();

    public abstract String vratiUslov();
}
