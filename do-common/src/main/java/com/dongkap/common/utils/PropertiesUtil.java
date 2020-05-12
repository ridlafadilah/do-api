package com.dongkap.common.utils;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Properties;

public class PropertiesUtil {
	public static String toString(Properties input) {
		Writer writer = new StringWriter();
		toWriter(input, writer);
		return writer.toString();
	}

	public static Properties fromDelimeter(String input) {
		Properties props = new Properties();

		if (input != null) {
			String array[] = input.split(";");
			for (int i = 0; i < array.length; i++) {
				String keyVal[] = array[i].split("=");
				props.put(keyVal[0], keyVal[1]);
			}
		}
		return props;
	}

	public static Properties fromString(String input) {
		if (input == null)
			return null;
		Properties props = new Properties();
		Reader reader = new StringReader(input);

		try {
			props.load(reader);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
			}
		}

		return props;
	}

	public static Properties fromFile(String fileName) {
		Properties props = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(fileName);
			props.load(in);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}
		return props;
	}

	public static void toFile(Properties input, String fileName) {
		try {
			toWriter(input, new FileWriter(fileName));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Write properties into writer.
	 *
	 * @param input
	 *            Properties
	 * @param writer
	 *            Writer to write into. Writer is automatically closed.
	 */
	public static void toWriter(Properties input, Writer writer) {
		try {
			input.store(writer, null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
			}
		}
	}
}
