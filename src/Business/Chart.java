/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author lm
 */

import java.awt.Color; 
import java.awt.BasicStroke; 
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class Chart extends JFrame 
{
   public Chart( String applicationTitle, String chartTitle, Object[] price )
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Transaction" ,
         "Price" ,
         createDataset(price) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );

      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel );
      setDefaultCloseOperation(2);
      
   }
   
   private XYDataset createDataset(Object[] price )
   {
      final XYSeries dealPrice = new XYSeries( "FinalPrice" );  
      for(int i=0; i<price.length; i++){
          Double x = Double.parseDouble(String.valueOf(price[i]));
          dealPrice.add(i+1, x);
      }
//      dealPrice.add( 1.0 , 1.0 );          
//      dealPrice.add( 2.0 , 4.0 );          
//      dealPrice.add( 3.0 , 3.0 );                    
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries( dealPrice );          
      return dataset;
   }

   public static void main( String[ ] args ) 
   {
      Chart chart = new Chart("Browser Usage Statistics", "Which Browser are you using?", null);
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true ); 
   }
}

