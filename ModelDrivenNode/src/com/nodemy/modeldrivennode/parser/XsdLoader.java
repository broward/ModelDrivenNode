package com.nodemy.modeldrivennode.parser;

import java.io.File;

/**
 * Import xsd schemas, and extract out only complexTypes, as they represent our
 * db tables.
 * 
 * @author broward
 * 
 */
public class XsdLoader {

	public XsdLoader(String filePath) {

		try {
			File fileEntry = new File(filePath);
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				addSchema(fileEntry);
			}
		} catch (Exception e) {
			System.out.println("Exception : " + e);
		}
	}

	/**
	 * recurse through my given directory and find all xsd schemas.
	 * 
	 * @param folder
	 */
	private void listFilesForFolder(File folder) {
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry);
			} else {
				addSchema(fileEntry);
			}
		}
	}

	private void addSchema(File fileEntry) {
		
		if (fileEntry.getName().contains(".xsd")) {
			try {
				String filePath = fileEntry.getPath();
				while (filePath.indexOf("\\") > -1) {
					filePath = filePath.replace("\\", "/");
				}

				// nb.buildServer(filePath);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new XsdLoader("schema/OTA");
	}
}
