package net.datagraft.shape;

import java.io.File;
import java.io.IOException;

public interface Shapeable {
	
	 File TEMP_DIR_TO_EXTRACT = new File(
			"F:\\SintefGitRepo\\Graftwerk\\graftwerk-prodm-master\\test\\data\\TMP");
	 String convertToCSV() throws IOException;
	
	 String convertToCSV(String delimiter, String quote, String newLine) throws IOException;
	 
	
}
