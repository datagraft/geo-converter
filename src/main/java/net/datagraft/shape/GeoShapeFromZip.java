package net.datagraft.shape;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import net.datagraft.exception.MissingShapeFileException;

import org.geotools.data.simple.SimpleFeatureIterator;

/**
 * Wrapper of {@link GeoShape} Represents a Shape File, when a .zip file is
 * provided.
 * 
 * @author nive
 *
 */
public class GeoShapeFromZip implements Shapeable {

	private GeoShape shape;
	private ZipInputStream zipIn;
	private static final int BUFFER_SIZE = 4096;
	private File destDir = new File(
			"F:\\SintefGitRepo\\Graftwerk\\graftwerk-prodm-master\\test\\data\\tes");

	/**
	 * @param filePath
	 * @throws IOException
	 * @throws MissingShapeFileException
	 */
	public GeoShapeFromZip(String filePath) throws IOException,
			MissingShapeFileException {
		 // hard coded. should discuss about a location 
		if (!destDir.exists()) {
			destDir.mkdir();
		}
		File file = new File(filePath);
		this.zipIn = new ZipInputStream(new FileInputStream(file));

		ZipEntry shapeFile = null;

		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null) {
			String destPath = destDir.getAbsolutePath() + File.separator
					+ entry.getName();
			if (!entry.isDirectory()) {
				// if the entry is a file, extracts it
				extractFile(zipIn, destPath);
			} else {
				// if the entry is a directory, make the directory
				File dir = new File(destPath);
				dir.mkdir();
			}
			if (entry.getName().endsWith(".shp")) {
				shapeFile = entry;
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		if (shapeFile == null) {
			throw new MissingShapeFileException(
					"Shape file not found. Compressed file should contain .shp file");
		}
		this.shape = new GeoShape(destDir.getAbsolutePath() + "\\"
				+ shapeFile.getName());
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

	/* (non-Javadoc)
	 * @see net.datagraft.shape.Shapeable#getRecords()
	 */
	public SimpleFeatureIterator getRecords() throws IOException {
		return this.shape.getRecords();
	}

	/* (non-Javadoc)
	 * @see net.datagraft.shape.Shapeable#convertToCSV()
	 */
	public String convertToCSV() throws IOException {
		String csv = this.shape.convertToCSV();
		System.out.println(csv);
		this.zipIn.close();
		return csv;
	}

	/* (non-Javadoc)
	 * @see net.datagraft.shape.Shapeable#convertToCSV(java.lang.String, java.lang.String, java.lang.String)
	 */
	public String convertToCSV(String delimiter, String quote, String newLine)
			throws IOException {
		String csv = this.shape.convertToCSV(delimiter, quote, newLine);
		this.zipIn.close();
		return csv;
	}
	
	@Override
	protected void finalize() throws Throwable {
		destDir.deleteOnExit();
		super.finalize();
	}

}
