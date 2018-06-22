/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author jeison
 */
public class Lote implements Serializable{//TreeMap Java - clave: id, valor: Lote
    
    private int id;//autoincremental
    private String codigoLote;
    private Date fechaEmpacado;//fecha y hora
    private Date fechaVecimiento;//solo fecha

    public Lote(int id, String codigoLote, Date fechaEmpacado, Date fechaVecimiento) {
        this.id = id;
        this.codigoLote = codigoLote;
        this.fechaEmpacado = fechaEmpacado;
        this.fechaVecimiento = fechaVecimiento;
    }

    public Lote() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }

    public Date getFechaEmpacado() {
        return fechaEmpacado;
    }

    public void setFechaEmpacado(Date fechaEmpacado) {
        this.fechaEmpacado = fechaEmpacado;
    }

    public Date getFechaVecimiento() {
        return fechaVecimiento;
    }

    public void setFechaVecimiento(Date fechaVecimiento) {
        this.fechaVecimiento = fechaVecimiento;
    }

    @Override
    public String toString() {
        return "Lote{" + "id=" + id + ", codigoLote=" + codigoLote + ", fechaEmpacado=" + fechaEmpacado + ", fechaVecimiento=" + fechaVecimiento + '}';
    }


    
}

