/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import izuzeci.GasenjeProgramaIzuzetak;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 *
 * @author Joska Stojanovic
 */
public class Posiljalac {
    private Socket soket;
    private boolean klijent;

    public Posiljalac(Socket soket, boolean klijent) {
        this.soket = soket;
        this.klijent = klijent;
    }
    
     public void posalji(Object object) throws Exception {
        try {
            ObjectOutputStream out = new ObjectOutputStream(soket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (SocketException ex) {
            if (klijent) {
                throw new GasenjeProgramaIzuzetak("Server je ugasen. Klijentski program ce se zatvoriti.");
            }
        } catch (IOException ex) {
            throw new Exception("Greska prilikom slanja podataka: " + ex.getMessage());
        }
    }
}
