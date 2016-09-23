package net.datagraft.convert;

import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

/**
 * CSV hander
 * 
 * @author nive
 *
 */
public class CSV {

	private String delimiter = ",";
	private String quote = "\"";
	private String newLine = "\n";

	/**
	 * Default constructor
	 */
	public CSV() {
	}

	/**
	 * Custom constructor
	 * 
	 * @param delimiter
	 * @param quote
	 * @param newLine
	 */
	public CSV(String delimiter, String quote, String newLine) {
		this.delimiter = delimiter;
		this.quote = quote;
		this.newLine = newLine;
	}

	/**
	 * Encodes {@link Iterable} String collection into {@link CSV} form 
	 * @param iterable
	 * @return CSV String
	 */
	public String encode(final Iterable<?> iterable) {
		StringBuilder out = new StringBuilder();

		String str;
		for (Object obj : iterable) {
			if (out.length() > 0) {
				out.append(this.delimiter);
			}

			if (obj instanceof String) {
				str = (String) obj;
			} else {
				str = obj.toString();
			}

			out.append(escape(str));
		}

		return out.toString();
	}
	
	/**
	 * Encodes {@link SimpleFeature} into {@link CSV} form 
	 * @param iterable
	 * @return CSV String
	 */
	public String encode(final SimpleFeature feature) {
		StringBuilder out = new StringBuilder();
		for (Property p : feature.getProperties()) {
			out.append(p.getValue().toString());
			out.append(this.delimiter);
		}
		return out.toString();
	}

	/**
	 * Escapes string using Quote char if necessary
	 * @param str
	 * @return
	 */
	public String escape(final String str) {
		if (containsReservedCharacter(str)) {
			return this.quote
					+ str.replace(this.quote, this.quote + this.quote)
					+ this.quote;
		} else {
			return str;
		}
	}

	protected boolean containsReservedCharacter(final String str) {
		return str.contains(this.delimiter) || str.contains(this.quote)
				|| str.contains("\r") || str.contains("\n");
	}

	// getters and setters
	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public void setQuoteChar(String quote) {
		this.quote = quote;
	}

	public String getDelimiter() {
		return this.delimiter;
	}

	public String getQuoteChar() {
		return this.quote;
	}

	public void setNewLine(String newLine) {
		this.newLine = newLine;
	}

	public String getNewLine() {
		return this.newLine;
	}
}