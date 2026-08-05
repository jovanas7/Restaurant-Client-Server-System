/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package transfer;

import java.io.Serializable;
import konstante.Status;

/**
 *
 * @author Joska Stojanovic
 */
public class ServerskiOdgovor implements Serializable{
    private Object odgovor;
    private Exception exception;
    private Status status;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, Exception exception,Status status) {
        this.odgovor = odgovor;
        this.exception = exception;
        this.status=status;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
}
