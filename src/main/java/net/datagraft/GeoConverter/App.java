package net.datagraft.GeoConverter;

import java.io.IOException;

import net.datagraft.shape.GeoShape;

/**
 * Main class to run the application
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	try {
			GeoShape shape = new GeoShape("F:\\SintefGitRepo\\datagraft\\GeoConverter\\src\\test\\data\\50m_cultural\\ne_50m_admin_0_boundary_lines_land.shp");
			String str =shape.convertToCSV();
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
