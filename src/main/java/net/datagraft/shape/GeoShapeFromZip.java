package net.datagraft.shape;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.datagraft.exception.MissingShapeFileException;

/**
 * Wrapper of {@link GeoShape} Represents a Shape File, when a .zip file is
 * provided.
 * 
 * @author nive
 *
 */
public class GeoShapeFromZip implements Shapeable {

	private List<GeoShape> shapes ;

	private static final int BUFFER_SIZE = 4096;
	
	private File extractedLocation;

	/**
	 * @param filePath
	 * @throws IOException
	 * @throws MissingShapeFileException
	 */
	public GeoShapeFromZip(String filePath) throws IOException,
			MissingShapeFileException {
		shapes = new ArrayList<GeoShape>();
		File inputFile = new File(filePath);
		extractedLocation = new File(TEMP_DIR_TO_EXTRACT.getAbsolutePath()
				+ File.separator + inputFile.getName());
		if (!extractedLocation.exists()) {
			extractedLocation.mkdir();
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(inputFile));

		ZipEntry entry = zipIn.getNextEntry();

		// iterates over entries in the zip file
		while (entry != null) {
			String destPath = extractedLocation.getAbsolutePath() + File.separator
					+ entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, destPath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(destPath);
				dir.mkdir();
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		

		File[] files = extractedLocation.listFiles(); 
		for(File file: files)
		{
			if(file.getName().endsWith(".shp"))
			{
				System.out.println(file.getName());
				GeoShape shape = new GeoShape(file.getAbsolutePath());
				shapes.add(shape);
			}
		}
		
		zipIn.close();
	}

	/**
	 * Extracts a zip entry (file entry)
	 * 
	 * @param zipIn
	 * @param filePath
	 * @throws IOException
	 */
	private void extractFile(ZipInputStream zipIn, String filePath)
			throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(filePath));
		byte[] bytesIn = new byte[BUFFER_SIZE];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datagraft.shape.Shapeable#convertToCSV()
	 */
	public String convertToCSV() throws IOException {
		StringBuilder csv = new StringBuilder();
		for(GeoShape shape : this.shapes)
		{
			csv.append(shape.convertToCSV());
		}
		
		return csv.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datagraft.shape.Shapeable#convertToCSV(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	public String convertToCSV(String delimiter, String quote, String newLine)
			throws IOException {
		StringBuilder csv = new StringBuilder();
		for(GeoShape shape : this.shapes)
		{
			csv.append(shape.convertToCSV(delimiter, quote, newLine));
		}
		
		return csv.toString();
	}

	@Override
	protected void finalize() throws Throwable {
		TEMP_DIR_TO_EXTRACT.deleteOnExit();
	}


	public String writeCSV() throws IOException {
		
		for(GeoShape shape : shapes)
		{
			shape.writeCSV(extractedLocation);
		}
		return extractedLocation.getAbsolutePath();
	}

}
