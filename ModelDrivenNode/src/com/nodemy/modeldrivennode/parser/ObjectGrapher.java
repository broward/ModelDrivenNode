package com.nodemy.modeldrivennode.parser;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * Creates xsd wrapper classes for my REST API entry points,
 * i.e. guesstimates entry points of each object graph and its depth.
 */
public enum ObjectGrapher {
	INSTANCE;

	private HashMap<String, Integer> objectGraphs = new HashMap<String, Integer>();
	private Set<String> entrypoints = new HashSet<String> ();

	public void addGraph(String entryPoint) {
		
		// save object graphs
		int count = 1;
		String temp = entryPoint;
		while (temp.indexOf("|") > -1) {
			count++;
			int cut = temp.indexOf("|");
			temp = temp.substring(cut + 1, temp.length());
		}
		objectGraphs.put(entryPoint, count);
		
		// save entry points if graph is at least two levels deep
		temp = entryPoint;
		if (temp.indexOf("|") > -1) {
			if (temp.substring(temp.indexOf("|") + 1, temp.length()).indexOf("|") > -1) {
				entrypoints.add(temp.substring(0, temp.indexOf("|")).trim());
			}
		}
	}

	public void writeObjectGraph() {
		FileWriter outFile;
		try {
			outFile = new FileWriter("/Temp/Graph" + ModelDrivenNode.SCHEMA
					+ ".txt");
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

		writeEntryPoints();
		
		// clear data
		objectGraphs = new HashMap<String, Integer>();
		entrypoints = new HashSet<String>();
	}

	public void writeEntryPoints() {
		FileWriter outFile;
		try {
			outFile = new FileWriter("/Temp/EntryPoints" + ModelDrivenNode.SCHEMA
					+ ".txt");
			PrintWriter out = new PrintWriter(outFile);

			@SuppressWarnings("rawtypes")
			Iterator i = entrypoints.iterator();
			int index = 0;
			while (i.hasNext()) {
				index++;
				String entry = (String) i.next();
					out.println(index + " :  " + entry);
			}

			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
