package com.nodemy.modeldrivennode.parser;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;

/*
 * Creates xsd wrapper classes for my REST API entry points,
 * i.e. guesstimates entry points of each object graph and its depth.
 */
public enum ObjectGrapher {
	INSTANCE;
	
	private HashMap<String, Integer> objectGraphs = new HashMap<String, Integer> ();

	public void addGraph(String entryPoint) {
		int count = 1;
		String temp = entryPoint;
		while (temp.indexOf("|") > -1) {
			count++;
			int cut = temp.indexOf("|");
			temp = temp.substring(cut+1, temp.length());
		}
		objectGraphs.put(entryPoint, count);
	}

	public void writeXsdWrapper() {
				FileWriter outFile;
				try {
					outFile = new FileWriter("/Temp/objectGraph.xsd");
					PrintWriter out = new PrintWriter(outFile);
					
					@SuppressWarnings("rawtypes")
					Iterator i = objectGraphs.keySet().iterator();
					int index = 0;
					while (i.hasNext()) {
						index++;
						String entry = (String) i.next();
						Integer count = objectGraphs.get(entry);
						if (count > -1) {
							out.println(index + " : " + count + " :  " + entry);
						}
					}
					
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

	}
}
