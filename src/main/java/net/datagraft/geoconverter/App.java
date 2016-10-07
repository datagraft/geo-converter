package net.datagraft.geoconverter;

import java.io.IOException;

import net.datagraft.exception.MissingShapeFileException;
import net.datagraft.shape.GeoShapeFromZip;

/**
 * Main class to run the application
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	try {
    		GeoShapeFromZip shape = new GeoShapeFromZip("C:\\Users\\narmi\\AppData\\Local\\Temp\\ring-multipart-7735881700133924913.tmp");
    		
			String str =shape.writeCSV();
			System.out.println(str);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MissingShapeFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	System.out.println("Exiting");

    }
    
    
}
