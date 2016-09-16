package net.datagraft.shape;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import net.datagraft.convert.CSV;

import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

/**
 * Represents a Shape File, by default it supports .shp files for now.
 * User is required to have the .shx file also in the same directory as .shp file. 
 * 
 * As an alternative use {@link GeoShapeFromZip} to process .zip file
 * @author nive
 *
 */
public class GeoShape extends ShapefileDataStore{
	
	// corresponding CSV value
	private StringBuffer csvValue;
	
	/**
	 * Creates a GeoShape object using given filepath
	 * @param absolute filePath 
	 * @throws MalformedURLException
	 */
	public GeoShape(String filePath) throws MalformedURLException {
		super(new File(filePath).toURI().toURL());
	}
	
	/**
	 * Returns Geo data in the form of {@link SimpleFeature} collection
	 * @return
	 * @throws IOException
	 */
	public SimpleFeatureIterator getRecords() throws IOException
	{
		return this.getFeatureSource(this.getTypeNames()[0]).getFeatures().features();
	}

	/**
	 * Transforms the geo records into a vector form that contains headers as the first row followed by each record values in each rows
	 * @param Collection of {@link SimpleFeature} records
	 * @return {@link List<List<String>>} of provided geo SimpleFeature data
	 */
	protected List<List<String>> getVectorForm(SimpleFeatureIterator records)
	{
		LinkedList<List<String>> vectorForm = new LinkedList<List<String>>();
		List<String> headers = null;
		List<String> keys;
		List<String> values;
		while(records.hasNext())
		{
			SimpleFeature feature = records.next();

			keys = new ArrayList<String>();
			values = new ArrayList<String>();

			for (Property p : feature.getProperties()) {
				keys.add(p.getName().toString());
				values.add(p.getValue().toString());
			}

			vectorForm.add(values);

			if (headers == null) {
				headers = keys;
			}
			else {
				if (!keys.equals(headers)) {
					throw new RuntimeException("Keys vary in number, order or content");
				}
			}
			
		}
		if (headers != null) {
			vectorForm.addFirst(headers);
		}

		return vectorForm;
	}
	
	/**
	 * Converts geo data into corresponding CSV format using default {@link CSV} format 
	 * @return CSV string of given vector data
	 * @throws IOException
	 */
	public String convertToCSV() throws IOException
	{
		CSV csv = new CSV();
		List<List<String>> vector = this.getVectorForm(this.getRecords());
		csvValue = new StringBuffer();

		for (List<String> entry : vector) {
			csvValue.append(csv.encode(entry));
			csvValue.append(csv.getNewLine());
		}

		return csvValue.toString();
	}
	
	/**
	 * Converts geo data into corresponding CSV format using custom {@link CSV} format 
	 * @param delimiter
	 * @param quote
	 * @param newLine
	 * @return CSV string of given vector data
	 * @throws IOException
	 */
	public String convertToCSV(String delimiter, String quote, String newLine) throws IOException
	{
		CSV csv = new CSV(delimiter, quote, newLine);
		List<List<String>> vector = this.getVectorForm(this.getRecords());
		

		for (List<String> entry : vector) {
			csvValue.append(csv.encode(entry));
			csvValue.append(csv.getNewLine());
		}

		return csvValue.toString();
	}

}
