/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.OpstiDomenskiObjekat;
import izuzeci.ServerskiIzuzetak;
import java.io.IOException;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Joska Stojanovic
 */
public class DBBroker {

    private static DBBroker instanca;
    private Connection konekcija;

    private DBBroker() throws ServerskiIzuzetak {
    }

    public static DBBroker getInstanca() throws ServerskiIzuzetak {
        if (instanca == null) {
            instanca = new DBBroker();
        }
        return instanca;
    }

    public void otvoriKonekciju() throws ServerskiIzuzetak {
        try {
            String url = Util.getInstanca().getUrl();
            String user = Util.getInstanca().getUser();
            String password = Util.getInstanca().getPassword();

            konekcija = DriverManager.getConnection(url, user, password);
            konekcija.setAutoCommit(false);

        } catch (IOException e) {
            throw new ServerskiIzuzetak("Doslo je do greske prilikom citanja properti fajla.");
        } catch (SQLException e) {
            throw new ServerskiIzuzetak("Doslo je do greske prilikom konekcije sa bazom.");
        }
    }

    public void zatvoriKonekciju() throws ServerskiIzuzetak {
        try {
            konekcija.close();
        } catch (SQLException e) {
            throw new ServerskiIzuzetak("Doslo je do greske prilikom raskidanja konekcije sa bazom.");
        }
    }

    public void ponistiTransakciju() throws ServerskiIzuzetak {
        try {
            konekcija.rollback();
        } catch (SQLException e) {
            throw new ServerskiIzuzetak("Doslo je do greske prilikom ponistavanja transakcije u bazi.");
        }
    }

    public void potvrdiTransakciju() throws ServerskiIzuzetak {
        try {
            konekcija.commit();
        } catch (SQLException e) {
            throw new ServerskiIzuzetak("Doslo je do greske prilikom potvrdjivanja transakcije u bazi.");
        }
    }

    public List<OpstiDomenskiObjekat> vratiSveObjekte(OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {
        String upit = "SELECT * FROM " + opstiDomenskiObjekat.vratiNazivTabele() + " " + opstiDomenskiObjekat.vratiAlijas() + " " + opstiDomenskiObjekat.vratiUslovZaJoin() + " " + opstiDomenskiObjekat.vratiUslov();
        System.out.println("Upit: " + upit);

        Statement statement = konekcija.createStatement();
        ResultSet resultSet = statement.executeQuery(upit);

        List<OpstiDomenskiObjekat> listaObjekata = opstiDomenskiObjekat.vratiListu(resultSet);
        resultSet.close();
        statement.close();

        return listaObjekata;

    }

    public int sacuvajObjekat(OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {
        String upit = "INSERT INT " + opstiDomenskiObjekat.vratiNazivTabele() + " (" + opstiDomenskiObjekat.vratiNaziveKolona() + ") VALUES (" + opstiDomenskiObjekat.vratiVrednostZaUnos() + ")";
        System.out.println("Upit: " + upit);

        PreparedStatement preparedStatement = konekcija.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.executeUpdate();

        ResultSet rs = preparedStatement.getGeneratedKeys();
        int id = -1;

        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        preparedStatement.close();

        return id;
    }

    public void obrisiObjekat(OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {
        String upit = "DELETE FROM " + opstiDomenskiObjekat.vratiNazivTabele() + " WHERE " + opstiDomenskiObjekat.vratiPrimarniKljuc() + " = " + opstiDomenskiObjekat.vratiVrednostPrimarnogKljuca();
        System.out.println("Upit: " + upit);

        PreparedStatement preparedStatement = konekcija.prepareStatement(upit);
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }

    public void izmeniObjekat(OpstiDomenskiObjekat opstiDomenskiObjekat) throws SQLException {
        String upit = "UPDATE " + opstiDomenskiObjekat.vratiNazivTabele() + " SET " + opstiDomenskiObjekat.vratiVrednostZaIzmenu() + " WHERE " + opstiDomenskiObjekat.vratiPrimarniKljuc() + " = " + opstiDomenskiObjekat.vratiVrednostPrimarnogKljuca();
        System.out.println("Upit: " + upit);

        PreparedStatement preparedStatement = konekcija.prepareStatement(upit);
        preparedStatement.executeUpdate();

        preparedStatement.close();
    }
}
