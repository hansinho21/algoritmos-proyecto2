/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.BinaryTree;

/**
 *
 * @author jeison
 */
public class BinaryNode {
    public Object element;
    public BinaryNode left, right;
    public int counter;

    public BinaryNode(Object element) {
        this.element = element;
        this.left = null;
        this.right = null;
        this.counter++;
    }

    public BinaryNode() {
    }

    @Override
    public String toString() {
        return ""+element ;
    }
    
    

   
    
}
