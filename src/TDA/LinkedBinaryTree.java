/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author jeison
 */
public class LinkedBinaryTree implements BinaryTree {

    private BinaryNode root;
    int height;

    public LinkedBinaryTree() {
        this.root = null;
        this.height = 0;
    }

    @Override
    public void cancel() throws TreeException {
        this.root = null;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public boolean exists(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("El arbol binario no existe");
        } else {
            return exists(root, element);
        }
    }

    private boolean exists(BinaryNode root, Object element) {
        if (root == null) {
            return false;
        } else if (root.element.equals(element)) {
            return true;
        } else {
            return exists(root.left, element) || exists(root.right, element);
        }

    }

    @Override
    public void insert(Object element) throws TreeException {
        this.root = insert(this.root, element);
    }

    private BinaryNode insert(BinaryNode node, Object element) {
        if (node == null) {
            node = new BinaryNode(element);
        } else {
            if (node.left == null) {
                node.left = insert(node.left, element);
            } else {
                if (node.right == null) {
                    node.right = insert(node.right, element);
                } else {

                    Random seed = new Random();
                    int randomValue = seed.nextInt(2);
                    if (randomValue == 1) {
                        node.left = insert(node.left, element);
                    } else {
                        node.right = insert(node.right, element);
                    }

                }
            }
        }
        return node;
    }

    @Override
    public void delete(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("El arbol binario no existe");
        } else {
            this.root = delete(this.root, element);
        }
    }

    private BinaryNode delete(BinaryNode node, Object element) {

        if (node != null) {
            if (node.element.equals(element)) {

                if (node.left == null && node.right == null) {
                    return node = null;
                } else {
                    if (node.left != null && node.right == null) {
                        return node = node.left;
                    } else if (node.left == null && node.right != null) {
                        return node = node.right;
                    } else {
                        if (node.left != null && node.right != null) {
                            Object tempElement = minimum(node.right);
                            node.element = tempElement;
                            node.right = delete(node.right, element);
                        }
                    }
                }
            }
            node.left = delete(node.left, element);
            node.right = delete(node.right, element);
        }

        return node;
    }

    private Object minimum(BinaryNode node) {

        //Cuando no tiene ningún hijo-------------------
        if (node.left != null && node.right != null) {

            if (getType(node) == 1) { //Cuando los valores a comparar son numéricos

                return Math.min(Integer.parseInt(node.element.toString()),
                        Math.min(Integer.parseInt(minimum(node.left).toString()),
                                Integer.parseInt(minimum(node.right).toString())));

            } else if (getType(node) == 2) {

                return minString(node.element.toString(),
                        minString(minimum(node.left).toString(), minimum(node.right).toString()));

            }

        }//---------------------------------------------

        //Cuando hay un hijo izquierdo-------------------
        if (node.left != null && node.right == null) {

            if (getType(node) == 1) {

                return Math.min(Integer.parseInt(node.element.toString()),
                        Integer.parseInt(minimum(node.left).toString()));

            } else if (getType(node.element) == 2) {

                return minString(node.element.toString(), minimum(node.left).toString());

            }

        }//---------------------------------------------

        //Cuando hay un hijo izquierdo-------------------
        if (node.left == null && node.right != null) {

            if (getType(node) == 1) {

                return Math.min(Integer.parseInt(node.element.toString()),
                        Integer.parseInt(minimum(node.right).toString()));

            } else if (getType(node.element) == 2) {

                return minString(node.element.toString(), minimum(node.right).toString());

            }

        }//---------------------------------------------
        return node.element; //No alcanzó ninguna de las condiciones
    }

    private String minString(String elem1, String elem2) {
        if (elem1.compareTo(elem2) < 0) {
            return elem1;
        } else {
            return elem2;
        }
    }

    private int getType(Object element) {
        if (element instanceof Integer) {
            return 1;
        }
        if (element instanceof String) {
            return 2;
        }
        return -1;
    }

    @Override
    public int heigth() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("El arbol binario no existe");
        }

        return heigth(this.root);
    }

    private int heigth(BinaryNode node) {
        if (node == null) {
            return 0;
        } else {
            return Math.max(heigth(node.left), heigth(node.right) + 1);
        }
    }

    @Override
    public int nodeHeight(Object element) throws TreeException {
        if (isEmpty()) {
            throw new TreeException("El arbol binario no existe");
        } else {
            return nodeHeight(this.root, element, 0);
        }
    }

    private int nodeHeight(BinaryNode node, Object element, int counter) {
        if (node == null) {
            return 0;
        } else {
            if (node.element.equals(element)) {
                return counter;
            } else {
                return Math.max(nodeHeight(node.left, element, ++counter),
                        nodeHeight(node.right, element, counter));
            }
        }
    }

    @Override
    public int getSize() throws TreeException {
        if (isEmpty()) {
            throw new TreeException("El arbol binario no existe");
        } else {
            return getSize(root);
        }
    }

    private int getSize(BinaryNode node) {
        if (node == null) {
            return 0;
        } else {
            return getSize(node.left) + 1 + getSize(node.right);
        }
    }

    @Override
    public String toString() {

        String output = " recorridos disponibles";

        output += "\npreorden:\n" + preOrder(this.root);
        output += "\ninorden:\n" + inOrder(this.root);
        output += "\npostorden:\n" + postOrder(this.root);

        return output;
    }

    private String preOrder(BinaryNode node) {
        String temp = "";

        if (node != null) {

            temp += node.element + "\n";
            temp += preOrder(node.left);
            temp += preOrder(node.right);
        }
        return temp;
    }

    private String inOrder(BinaryNode node) {
        String temp = "";

        if (node != null) {

            temp += inOrder(node.left);
            temp += node.element + "\n";
            temp += inOrder(node.right);
        }
        return temp;
    }

    private String postOrder(BinaryNode node) {
        String temp = "";

        if (node != null) {

            temp += postOrder(node.left);
            temp += postOrder(node.right);
            temp += node.element + "\n";
        }
        return temp;

    }

    public Boolean identical(LinkedBinaryTree A, LinkedBinaryTree B) throws TreeException {
        boolean identical = false;
        if (A.isEmpty() || B.isEmpty()) {
            throw new TreeException("Algun arbol no existe");
        }
        if (A.getSize() == B.getSize() && A.heigth() == B.heigth()) {
            if (this.inOrder(A.root).equalsIgnoreCase(this.inOrder(B.root))) {
                identical = true;
            }
        }
        return identical;
    }
 
    public String level(int level) throws TreeException {
        String temp = "";
        temp += level(this.root, level);
        return temp;
    }

    private String level(BinaryNode node, int level) throws TreeException {
        String temp = "";
        if (nodeHeight(node) == level) {
            temp += node.element;
            return temp;
        } else {
            if (nodeHeight(node.left) == level || nodeHeight(node.right.element) == level) {
                temp += node.left.element + "\n";
                temp += node.right.element;
            }

//            level((BinaryNode) node.left, level);
//            level((BinaryNode) node.right, level);

            return temp;
        }     
    }

    //Se define por frontera de un árbol binario, la secuencia formada por los elementos
//almacenados en las hojas de un árbol binario, tomados de izquierda a derecha. Escribe una
//acción que, dados un árbol binario y una LinkedList vacía (java.utils) compartidos como
//parámetros, devuelva en dicha lista la frontera del árbol.
    
    public LinkedList border(LinkedList lista) throws TreeException{
        if(isEmpty())
            throw new TreeException("el arbol no existe");
        return border(this.root, lista);
    }
    
      private LinkedList border(BinaryNode node,LinkedList<Object> lista) throws TreeException{
            
          if(node.left==null&&node.right==null)
              lista.add(node.element);
          else {
              if(node.left!=null)
                  border(node.left, lista);
              if(node.right!=null)
                  border(node.right, lista);
          }
          return lista;
     }
      
      //----------------------------------------------------------------------------------
      
//      public boolean path(LinkedList camino) throws TreeException{
//          if(isEmpty())
//              throw new TreeException("no existe");          
//          return LinkedBinaryTree.this.path(this.root,camino);    
//      }
//    
//      private boolean path(BinaryNode node,LinkedList camino){
//          boolean flag=false;
//          for (int i = 0; i < camino.size(); i++) {
//            if(node.element.equals(camino.get(i))){
//                if(node.left!=null){
//                    LinkedBinaryTree.this.path(node.left, camino);
//                }
//                if(node.left!=null){
//                    LinkedBinaryTree.this.path(node.right, camino);
//                }
//                flag=true;
//            }
//            else{
//                flag=false;
//            }
//          }
//          return flag;
//      }
      public boolean path(LinkedList camino) throws TreeException {
          if(isEmpty())
              throw new TreeException("no existe");
        return path(this.root, camino, 0);
    }
    
    public boolean path(BinaryNode node, LinkedList camino, int cont) {
        
        if(cont == camino.size()-1){
            return true;
        }
        
        if(!node.left.element.equals(camino.get(cont)) && !node.right.element.equals
        (camino.get(cont))){
            return false;
        }
        
        if(node.left.element.equals(camino.get(cont))){
            node = node.left;
            return path(node, camino, cont++);
        } else if(node.right.element.equals(camino.get(cont))){
            node = node.right;
            return path(node, camino, cont++);
        }
        return false;
    }
}
      
      //-------------------------------------------------------------------------------------
      


