package net.datagraft.shape;

import java.io.IOException;

import org.geotools.data.simple.SimpleFeatureIterator;

public interface Shapeable {
	
	public SimpleFeatureIterator getRecords() throws IOException;
	
	public String convertToCSV() throws IOException;
	
	public String convertToCSV(String delimiter, String quote, String newLine) throws IOException;
	
}
