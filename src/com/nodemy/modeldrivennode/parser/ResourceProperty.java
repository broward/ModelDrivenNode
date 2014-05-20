package com.nodemy.modeldrivennode.parser;

import com.nodemy.modeldrivennode.TestDataGenerator;

/**
 * Resource properties
 * @author broward
 *
 */
public class ResourceProperty {
	private String name;
	private String type;
	private String mongoType;
	private Object value;
	
	/**
	 * Make this an immutable object
	 * @param name
	 * @param mongoType
	 * @param value
	 */
	public ResourceProperty(String name, String type, Object value) {
		this.name = name;
		this.type = type;
		this.mongoType = MongooseTypeConverter.convertXsdToMongo(type);
		this.value = TestDataGenerator.INSTANCE.generate(mongoType);
	}
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public String getMongoType() {
		return mongoType;
	}
	public Object getValue() {
		return value;
	}
}
