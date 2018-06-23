/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.Graph;

import java.io.Serializable;

/**
 *
 * @author Equipo
 */
public class Vertice implements Serializable{
    
    public Object element;
    public boolean isVisited;
    
    public Vertice(Object elemet){
        this.element = elemet;
        this.isVisited = false;
    }

    @Override
    public String toString() {
        return "Vertice{" + "element=" + element + ", isVisited=" + isVisited + '}';
    }
    
}
