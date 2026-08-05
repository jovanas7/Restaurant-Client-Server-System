/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import izuzeci.GasenjeProgramaIzuzetak;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Joska Stojanovic
 */
public class Primalac {

    private Socket soket;
    private boolean klijent;

    public Primalac(Socket soket, boolean klijent) {
        this.soket = soket;
        this.klijent = klijent;
    }

    public Object primi() throws Exception {
        try {
            ObjectInputStream in = new ObjectInputStream(soket.getInputStream());
            return in.readObject();
        } catch (SocketException ex) {
            if (klijent) {
                throw new GasenjeProgramaIzuzetak("Serverski program je ugasen. Klijentski program ce se zatvoriti.");
            }
        } catch (IOException ex) {
            throw new Exception("Greska prilikom citanja podataka: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            throw new Exception("Primljen je nepoznat tip podataka.");
        }

        return null;
    }
}
