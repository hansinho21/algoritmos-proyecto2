/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;

/**
 * Objeto donde se guardan los datos de cada Orden de Distribución
 *
 * @author jeison
 */
public class OrdenDistribucion implements Serializable {

    private int id;//autoincremental
    private String codigo;
    private int idBodegaProcedencia;
    private int idBodegaDestino;
    private double montoTotal;
    private float pesoTotal;//Kilos
    private LinkedList<ProductoMayorista> listaProductos;
    private int idOperador;
    private Date fecha;

    public OrdenDistribucion(int id, String codigo, int idBodegaProcedencia, int idBodegaDestino, double montoTotal, float pesoTotal, LinkedList<ProductoMayorista> listaProductos, int idOperador, Date fecha) {
        this.id = id;
        this.codigo = codigo;
        this.idBodegaProcedencia = idBodegaProcedencia;
        this.idBodegaDestino = idBodegaDestino;
        this.montoTotal = montoTotal;
        this.pesoTotal = pesoTotal;
        this.listaProductos = listaProductos;
        this.idOperador = idOperador;
        this.fecha = fecha;
    }

    public OrdenDistribucion() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdBodegaProcedencia() {
        return idBodegaProcedencia;
    }

    public void setIdBodegaProcedencia(int idBodegaProcedencia) {
        this.idBodegaProcedencia = idBodegaProcedencia;
    }

    public int getIdBodegaDestino() {
        return idBodegaDestino;
    }

    public void setIdBodegaDestino(int idBodegaDestino) {
        this.idBodegaDestino = idBodegaDestino;
    }

    public double getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(double montoTotal) {
        this.montoTotal = montoTotal;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public LinkedList<ProductoMayorista> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(LinkedList<ProductoMayorista> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public int getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(int idOperador) {
        this.idOperador = idOperador;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "OrdenDistribucion{" + "id=" + id + ", codigo=" + codigo + ", idBodegaProcedencia=" + idBodegaProcedencia + ", idBodegaDestino=" + idBodegaDestino + ", montoTotal=" + montoTotal + ", pesoTotal=" + pesoTotal + ", listaProductos=" + listaProductos + ", idOperador=" + idOperador + ", fecha=" + fecha + '}';
    }

}
