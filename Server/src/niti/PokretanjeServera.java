/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import db.Util;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joska Stojanovic
 */
public class PokretanjeServera extends Thread {

    private ServerSocket serverSocket;
    public static int brojPorta;
    private List<ObradaKlijentskihZahteva> klijenti = new ArrayList();
    private static boolean pokrenut = false;

    public PokretanjeServera() {
        try {
            brojPorta = Integer.parseInt(Util.getInstanca().getPort());
            serverSocket= new ServerSocket(brojPorta);
        } catch (IOException e) {
              System.out.println("Doslo je do greske prilikom kreiranja serverskog soketa.");
        }
    }

    @Override
    public void run() {
        try {
            while(!isInterrupted()){
                System.out.println("Cekanje klijenta...");
                Socket socket= serverSocket.accept();
                System.out.println("Klijent se povezao!");
                
                ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(socket,klijenti);
                nit.start();
                klijenti.add(nit);
            }
        } catch (Exception e) {
            System.out.println("Serverski program je ugasen.");
        }
    }
    public void prekiniKlijentskePrograme(){
        try {
            serverSocket.close();
            for(ObradaKlijentskihZahteva klijent: klijenti){
                klijent.getSoket().close();
            }
            System.out.println("Svi klijentski programi su prekinuti.");
        } catch (Exception e) {
            System.out.println("Doslo je do greske prilikom prekidanja klijentskih programa.");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static boolean isPokrenut() {
        return pokrenut;
    }

    public static void setPokrenut(boolean pokrenut) {
        PokretanjeServera.pokrenut = pokrenut;
    }

}
