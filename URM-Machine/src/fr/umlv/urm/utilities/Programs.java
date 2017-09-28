package fr.umlv.urm.utilities;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import fr.umlv.urm.exception.URMException;

/**
 * 
 * @author vrasquie
 * @version 1
 */
public final class Programs {
	/**
	 * Check if filename is from the specified extension
	 * 
	 * @param filename
	 * @param type
	 * @return boolean
	 */
	public static boolean checkExtension(String filename, String type) {
		int i = filename.lastIndexOf('.');
		
		if (i < 1) {
			return false;
		}
		
		String ext = filename.substring(i + 1);
		
		if (!ext.equals(type)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Parse the file (based on filename) into a list of string
	 * 
	 * @param filename
	 * @param type
	 * @return list of string contained inside the file
	 * @throws URMException
	 * @throws IOException
	 */
	public static List<String> parseFile(String filename, String type) throws URMException, IOException {
		if (!checkExtension(filename, type)) {
			throw new URMException("The file extension has not been detected");
		}
		
		Path path = FileSystems.getDefault().getPath(filename);
		Scanner scanner = new Scanner(path);
		List<String> list = new LinkedList<>();
		
		scanner.useDelimiter("\n");
		
		while (scanner.hasNext()) {
			String s = scanner.next();

			if (s.equals("") || s.trim().length() < 1 || s.isEmpty()) {
				continue;
			}

			list.add(s);
		}
		
		scanner.close();
		
		return list;
	}
}
