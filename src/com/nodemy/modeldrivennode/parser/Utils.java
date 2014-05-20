package com.nodemy.modeldrivennode.parser;

import java.util.ArrayList;
import java.util.List;

public class Utils {

	/**
	 * Don't allow these words to represent resources.
	 */
	private static List<String> reservedWords = new ArrayList<String>();
	
	static {
		reservedWords.add("errors");
		reservedWords.add("ObjectFactory");
		reservedWords.add(null);
		reservedWords.add("");
	}
	/**
	 * check if a class name is a reserved word
	 * @param word
	 * @return
	 */
	public static boolean isReservedWord(String word) {
		return reservedWords.contains(word);
	}
}
