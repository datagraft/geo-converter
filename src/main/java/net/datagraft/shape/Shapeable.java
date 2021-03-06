package net.datagraft.shape;

import java.io.File;
import java.io.IOException;

/**
 * Represents shape file conversion related tasks
 * 
 * Requires FilePermission and PropertyPermission
 * @author nive
 *
 */
public interface Shapeable {

	/**
	 * Temporary location to extract compressed file contents
	 */
	File TEMP_DIR_TO_EXTRACT = new File(
			System.getProperty("java.io.tmpdir"));

	/**
	 * Converts a Shape object into equivalent CSV value
	 * @return equivalent csv string
	 * @throws IOException
	 */
	String convertToCSV() throws IOException;

	/**
	 * Converts a Shape object into equivalent CSV value using given CSV format parameters
	 * @param delimiter
	 * @param quote
	 * @param newLine
	 * @return equivalent csv string
	 * @throws IOException
	 */
	String convertToCSV(String delimiter, String quote, String newLine)
			throws IOException;

}
