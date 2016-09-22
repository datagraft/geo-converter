package net.datagraft.geoconverter;

import java.io.IOException;

import net.datagraft.exception.MissingShapeFileException;
import net.datagraft.shape.GeoShape;
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
    		GeoShapeFromZip shape = new GeoShapeFromZip("F:\\SintefGitRepo\\Graftwerk\\graftwerk-prodm-master\\test\\data\\NOR_adm_shp.zip");
			String str =shape.convertToCSV();
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	catch (MissingShapeFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
