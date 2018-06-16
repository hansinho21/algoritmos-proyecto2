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
public class UnidadTransporte {//LinkedHashMap Java - clave: id, valor: UnidadTransporte
    private int id;//autoincremental
    private String placa;
    private String capacidad;
    private String urlFotografia;

    public UnidadTransporte(int id, String placa, String capacidad, String urlFotografia) {
        this.id = id;
        this.placa = placa;
        this.capacidad = capacidad;
        this.urlFotografia = urlFotografia;
    }

    public UnidadTransporte() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getUrlFotografia() {
        return urlFotografia;
    }

    public void setUrlFotografia(String urlFotografia) {
        this.urlFotografia = urlFotografia;
    }

    @Override
    public String toString() {
        return "UnidadTransporte{" + "id=" + id + ", placa=" + placa + ", capacidad=" + capacidad + ", urlFotografia=" + urlFotografia + '}';
    }
    
    
}
