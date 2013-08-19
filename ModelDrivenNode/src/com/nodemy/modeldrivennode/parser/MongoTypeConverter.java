package com.nodemy.modeldrivennode.parser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Convert xsd primitive data types to mongodb data types.
 * 
 * @author broward
 * 
 */
public class MongoTypeConverter {
	public static Map<String, String> types = new HashMap<String, String>();

	// mongo data types
	public static String DOUBLE = "Double";
	public static String STRING = "String";
	public static String OBJECT = "Object";
	public static String ARRAY = "Array";
	public static String NUMBER = "Number";
	public static String BOOLEAN = "Boolean";
	public static String DATE = "Date";

	static {
		// mapping java types to mongo types
		types.put(String.class.getSimpleName(), STRING);
		types.put(Integer.class.getSimpleName(), NUMBER);
		types.put(BigInteger.class.getSimpleName(), NUMBER);
		types.put(int.class.getSimpleName(), NUMBER);
		types.put(char.class.getSimpleName(), STRING);
		types.put(XMLGregorianCalendar.class.getSimpleName(), DATE);
		types.put(Object.class.getSimpleName(), STRING);
		types.put(BigDecimal.class.getSimpleName(), NUMBER);
		types.put(Byte.class.getSimpleName(), NUMBER);
		types.put(byte.class.getSimpleName(), STRING);
		types.put(Duration.class.getSimpleName(), NUMBER);
		
		types.put(short.class.getSimpleName(), NUMBER);
		types.put(Short.class.getSimpleName(), NUMBER);
		
		types.put(long.class.getSimpleName(), NUMBER);
		types.put(Long.class.getSimpleName(), NUMBER);
		
		types.put(float.class.getSimpleName(), NUMBER);
		types.put(Float.class.getSimpleName(), NUMBER);
		
		types.put(double.class.getSimpleName(), NUMBER);
		types.put(Double.class.getSimpleName(), NUMBER);
		
		types.put(boolean.class.getSimpleName(), BOOLEAN);
		types.put(Boolean.class.getSimpleName(), BOOLEAN);		
	}

	/**
	 * is this a natural java type or complex type?
	 * @param type
	 * @return
	 */
	public static boolean isPrimitive(String type) {
		return types.get(type) != null;
	}
	
	/**
	 * Convert java data type to mongo data type
	 */
	public static String convertXsdToMongo(String type) {
			
		String mongoType = types.get(type);
		
		// if it's not a primitive type,
		// assume it's complex type and return mongo record syntax.
		if (mongoType == null) {
			mongoType = "[" + type + "Schema]";
		} 
			
		return mongoType;
	}
}
