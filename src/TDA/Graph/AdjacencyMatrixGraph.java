/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDA.Graph;

/**
 *
 * @author Equipo
 */
public class AdjacencyMatrixGraph implements Graph {

    private Object AdjacencyMatrix[][];
    private int counter;
    private int num;
    private Vertice vertexList[];

    public AdjacencyMatrixGraph(int num) {
        if (num <= 0) {
            System.out.println("Tamaño invalido");
            System.exit(num);
        }
        this.num = num;
        this.AdjacencyMatrix = new Object[num][num];
        this.vertexList = new Vertice[num];
        initializeMatrix();
    }

    private void initializeMatrix() {
        for (int i = 0; i < this.num; i++) {
            for (int j = 0; j < this.num; j++) {
                this.AdjacencyMatrix[i][j] = 0;
            }
        }

    }

    @Override
    public void cancel() throws GraphException {
        for (int i = 0; i < this.num; i++) {
            this.vertexList[i] = null;
        }
        this.initializeMatrix();
        this.counter = 0;
    }

    @Override
    public int getSize() throws GraphException {
        if (isEmpty()) {
            throw new GraphException("El grafo está vacío");
        }
        return counter;
    }

    @Override
    public boolean existVertex(Object element) throws GraphException {
        if (isEmpty()) {
            throw new GraphException("El grafo está vacío");
        }

        for (int i = 0; i < this.counter; i++) {
            if ((vertexList[i].element).equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existEdge(Object v1, Object v2) throws GraphException {
        if (!existVertex(v1) || !existVertex(v2)) {
            throw new GraphException("Vertice no existe");
        }

        if (!AdjacencyMatrix[getPosition(v1)][getPosition(v2)].equals(0)) {
            return true;
        }
        return false;
    }

    private int getPosition(Object v) {
        
        for (int i = 0; i < this.counter; i++) {
            if (this.vertexList[i].element.equals(v)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void insertVertex(Object element) throws GraphException {
        if (this.counter >= this.vertexList.length) {
            throw new GraphException("El grafo está lleno");
        }
        this.vertexList[counter++] = new Vertice(element);
    }

    @Override
    public void insertEdge(Object v1, Object v2) throws GraphException {
        if (!existVertex(v1) || !existVertex(v2)) {
            throw new GraphException("Vertice no existe");
        }
        this.AdjacencyMatrix[getPosition(v1)][getPosition(v2)] = 1;
        this.AdjacencyMatrix[getPosition(v2)][getPosition(v1)] = 1;
    }

    @Override
    public void insertWeight(Object v1, Object v2, int weight) throws GraphException {
        if (!existVertex(v1) || !existVertex(v2)) {
            throw new GraphException("Vertice no existe");
        }
        this.AdjacencyMatrix[getPosition(v1)][getPosition(v2)] = weight;
        this.AdjacencyMatrix[getPosition(v2)][getPosition(v1)] = weight;
    }

    @Override
    public boolean isEmpty() throws GraphException {
        return counter == 0;
    }

    @Override
    public String toString() {
        String output = "GRAFO CON MATRICES DE ADYACENCIA\n";

        for (int i = 0; i < this.counter; i++) {
            output += "Vértice en posición " + i + " es: " + this.vertexList[i].element + "\n";
        }
        output += "\nLISTA DE ARISTAS\n";

        for (int i = 0; i < this.num; i++) {
            for (int j = 0; j < this.num; j++) {
                if (!this.AdjacencyMatrix[i][j].equals(0)) {
                    output += this.vertexList[i].element + "---------" + this.vertexList[j].element + "\n";
                }
            }
        }
        return output;
    }
    
    public String toString2() {
        String output = "";

        for (int i = 0; i < this.counter; i++) {
            output += this.vertexList[i].element.toString()+"\n";
        }
        return output;
    }
    
    

    @Override
    public void deleteVertex(Object element) throws GraphException {
        if (existVertex(element)) {
            for (int i = 0; i < this.counter; i++) {
                if (this.vertexList[i].element.equals(element)) {
                    //eliminamos aristas disponibles
                    for (int j = 0; j < this.counter; j++) {
                        if (existEdge(element, this.vertexList[j].element)) {
                            deleteEdge(element, this.vertexList[j].element);
                        }
                    }
                    //eliminamos el vertice
                    //Mueve una posicion a la izquierda todo el contenido del arreglo
                    for (int j = i; j < this.counter - 1; j++) {
                        this.vertexList[j] = this.vertexList[j + 1];
                    }
                    this.counter--;
                }
            }
        }
    }

    @Override
    public void deleteEdge(Object v1, Object v2) throws GraphException {
        if (!existVertex(v1) || !existVertex(v2)) {
            throw new GraphException("Vertice no existe");
        }
        this.AdjacencyMatrix[getPosition(v1)][getPosition(v2)] = 0;
        this.AdjacencyMatrix[getPosition(v2)][getPosition(v1)] = 0;
    }

    @Override
    public Object getVertex(int position) throws GraphException {
        if (isEmpty()) {
            throw new GraphException("El grafo está vacío");
        }
        if (position >= this.vertexList.length) {
            return null;
        } else {
            return this.vertexList[position].element;
        }

    }

    @Override
    public void setVertex(int position, Object element) throws GraphException {
        if (isEmpty()) {
            throw new GraphException("El grafo está vacío");
        }

        if (position >= this.vertexList.length) {
            throw new GraphException("No existe la posicion en el grafo");
        } else {
            for (int i = 0; i < this.counter; i++) {
                if (i == position) {
                    this.vertexList[i].element = element;
                }
            }
        }
    }

}
