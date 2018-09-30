package forspl;
import forspl.LocalVariableCounting;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 

public class BarChart extends ApplicationFrame {
   
   public BarChart( String applicationTitle , String chartTitle ) {
      super( applicationTitle );        
      JFreeChart barChart = ChartFactory.createBarChart(
         chartTitle,           
         "VARIABLES",            
         "Number Of Variables Found",            
         createDataset(),          
         PlotOrientation.VERTICAL,           
         true, true, false);
         
      ChartPanel chartPanel = new ChartPanel( barChart );        
      chartPanel.setPreferredSize(new java.awt.Dimension( 450 , 600 ) );        
      setContentPane( chartPanel ); 
   }
   
   private CategoryDataset createDataset( ) {
	  final String Int = "int";        
      final String Double = "double";        
      final String Float = "float";        
      final String Char = "char";        
      final String Boolean = "boolean";        
      final String string = "String";     
      
      final String variables = "Variables";   
      
      final DefaultCategoryDataset dataset = 
      new DefaultCategoryDataset( );  
      
      /*int ci, cd, cf, cc, cb, cs;
      ci =LocalVariableCounting.countInt;	cd=LocalVariableCounting.countDouble;
      cf=LocalVariableCounting.countFloat;	cc=LocalVariableCounting.countChar;
      cb=LocalVariableCounting.countBoolean;	cs=LocalVariableCounting.countString;
      
      Int= Int+"\n"+ "["+ci+"x4 = "+ (ci*4)+" Bytes]";
      */
      
      dataset.addValue(LocalVariableCounting.countInt , variables , Int );        
      dataset.addValue( LocalVariableCounting.countDouble , variables , Double );        
      dataset.addValue( LocalVariableCounting.countFloat ,variables ,Float  ); 
      dataset.addValue( LocalVariableCounting.countChar , variables , Char );           
      dataset.addValue( LocalVariableCounting.countBoolean , variables , Boolean  );        
      dataset.addValue( LocalVariableCounting.countString , variables , string  );       
                  

      return dataset; 
   }
   
   public static void main( String[ ] args ) {
      BarChart chart = new BarChart("Variable Count Statistics", 
         "Variables of Primitive Types");
      chart.pack( );        
      RefineryUtilities.centerFrameOnScreen( chart );        
      chart.setVisible( true ); 
   }
}
