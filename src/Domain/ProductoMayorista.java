/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author jeison
 */
public class ProductoMayorista {
    
    private int id;
    private String nombre;
    private String unidadMedidas;
    private int valorUnidad;
    private int pesoTotal;
    private String descripcion;
    private int idLote;
    private int idCategoria;
    private double precioTotal;
    private String urlFotografia;

    public ProductoMayorista(int id, String nombre, String unidadMedidas, int valorUnidad, int pesoTotal, String descripcion, int idLote, int idCategoria, double precioTotal, String urlFotografia) {
        this.id = id;//autoincremental
        this.nombre = nombre;
        this.unidadMedidas = unidadMedidas;
        this.valorUnidad = valorUnidad;
        this.pesoTotal = pesoTotal;
        this.descripcion = descripcion;
        this.idLote = idLote;
        this.idCategoria = idCategoria;
        this.precioTotal = precioTotal;
        this.urlFotografia = urlFotografia;
    }

    public ProductoMayorista() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedidas() {
        return unidadMedidas;
    }

    public void setUnidadMedidas(String unidadMedidas) {
        this.unidadMedidas = unidadMedidas;
    }

    public int getValorUnidad() {
        return valorUnidad;
    }

    public void setValorUnidad(int valorUnidad) {
        this.valorUnidad = valorUnidad;
    }

    public int getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(int pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getUrlFotografia() {
        return urlFotografia;
    }

    public void setUrlFotografia(String urlFotografia) {
        this.urlFotografia = urlFotografia;
    }

    @Override
    public String toString() {
        return "ProductoMayorista{" + "id=" + id + ", nombre=" + nombre + ", unidadMedidas="
                + unidadMedidas + ", valorUnidad=" + valorUnidad + ", pesoTotal=" 
                + pesoTotal + ", descripcion=" + descripcion + ", idLote=" + idLote 
                + ", idCategoria=" + idCategoria + ", precioTotal=" + precioTotal 
                + ", urlFotografia=" + urlFotografia + '}';
    }
    
    



    
}
