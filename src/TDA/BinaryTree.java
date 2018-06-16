/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;


/**
 *
 * @author jeison
 */
public interface BinaryTree {
    public void cancel()throws TreeException;
    public boolean isEmpty()throws TreeException;
    public boolean exists(Object element)throws TreeException;
    public void insert(Object element) throws TreeException;
    public void delete(Object element) throws TreeException;
    public int heigth()throws TreeException;
    public int nodeHeight(Object element) throws TreeException;
    public int getSize() throws TreeException;
    
}
