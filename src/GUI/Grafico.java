package GUI;



import Domain.Bodega;
import Domain.OrdenDistribucion;
import Logic.Datos;
import TDA.BinaryTree.TreeException;
import TDA.Graph.AdjacencyMatrixGraph;
import TDA.Graph.GraphException;
import java.awt.Color;
import java.awt.Panel;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


/**
 * A simple demonstration application showing how to create a dual axis chart based on data
 * from two {@link CategoryDataset} instances.
 *
 */
public class Grafico extends ApplicationFrame {
    
    LinkedList<OrdenDistribucion> listaOrdenes;
    AdjacencyMatrixGraph grafoBodegas;
    Datos datos;
    
    
    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
    public Grafico(final String title, JPanel jPanel) throws IOException, GraphException, FileNotFoundException, ClassNotFoundException, TreeException {

        super(title);
        datos = new Datos();
        grafoBodegas = datos.getGrafoBodegas();
        listaOrdenes = datos.getListaOrdenes();
        final CategoryDataset dataset1 = createDataset1();

        // create the chart...
        final JFreeChart chart = ChartFactory.createBarChart(
            "Reportes","Bodegas","Cantidad de lotes",dataset1,PlotOrientation.VERTICAL, true, true, false                    
        );
        chart.setBackgroundPaint(Color.white);
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.mapDatasetToRangeAxis(1, 1);

        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        // OPTIONAL CUSTOMISATION COMPLETED.

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        jPanel.add(chartPanel);

    }

    
    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private CategoryDataset createDataset1() throws GraphException {

        // row keys...
        final String series1 = "Abril";
        final String series2 = "Mayo";
        final String series3 = "Junio";
        
        // column keys...
        String bodegas[] = new String[grafoBodegas.getSize()];
        for (int i = 0; i < grafoBodegas.getSize(); i++) {
            Bodega b = (Bodega) grafoBodegas.getVertex(i);
            bodegas[i]=b.getNombre();
        }

        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < bodegas.length; i++) {
            int numero= (int) (Math.random() * listaOrdenes.size()) + 1;
            dataset.addValue(numero, series1, bodegas[i]);
        }
        for (int i = 0; i < bodegas.length; i++) {
            int numero= (int) (Math.random() * listaOrdenes.size()) + 1;
            dataset.addValue(numero, series2, bodegas[i]);
        }
        for (int i = 0; i < bodegas.length; i++) {
            int numero= (int) (Math.random() * listaOrdenes.size()) + 1;
            dataset.addValue(numero, series3, bodegas[i]);
        }
        return dataset;

    }
}