package com.nodemy.modeldrivennode.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sun.codemodel.JFieldVar;

public class Resource {
	private String name;
	private List<ResourceProperty> properties = new ArrayList<ResourceProperty>();
	private Mdn mdn = new Mdn();
	/**
	 * Make this an immutable object.
	 * 
	 * @param name
	 * @param map
	 */
	public Resource(String name, Map<String, JFieldVar> map) {
		this.name = name;

		// extract out attributes we need
		// Resolve xsd exception cases here for now
		Iterator<String> i = map.keySet().iterator();
		while (i.hasNext()) {
			String key = (String) i.next();
			JFieldVar jFieldVar = map.get(key);
			String type = jFieldVar.type().name();

			// convert parameterized lists to their base type
			if (type.contains("List<JAXBElement<? extends ")) {
				int start = 28;

				// check for class argument
				int stop = type.indexOf(">");
				type = type.substring(start, stop);

				// this is a reference to resource
				// make sure we create a phantom resource for it
					if (!MongooseTypeConverter.isPrimitive(type)) {
						Resource resource = new Resource(type);
						ResourceList.INSTANCE.add(resource);
					}
					
			} else if (type.contains("List<JAXBElement<")) {
				int start = 17;
				int stop = type.indexOf(">");
				type = type.substring(start, stop);

				// this is a reference to resource
				// make sure we create a phantom resource for it
					if (!MongooseTypeConverter.isPrimitive(type)) {
						Resource resource = new Resource(type);
						ResourceList.INSTANCE.add(resource);
					}
					
			} else if (type.contains("List<")) {
				int start = type.indexOf("<") + 1;

				// check for class argument
				int stop = type.indexOf("<?");
				if (stop < 0) {
					stop = type.indexOf(">");
				}
				type = type.substring(start, stop);

				// this is a reference to resource
				// make sure we create a phantom resource for it
					if (!MongooseTypeConverter.isPrimitive(type)) {
						Resource resource = new Resource(type);
						ResourceList.INSTANCE.add(resource);
					}
					
			} else if (type.contains("Map<")) {
				int start = type.indexOf(",") + 1;
				int stop = type.indexOf(">");
				type = type.substring(start, stop);

				// this is a reference to resource
				// make sure we create a phantom resource for it
					if (!MongooseTypeConverter.isPrimitive(type)) {
						Resource resource = new Resource(type);
						ResourceList.INSTANCE.add(resource);
					}
					
			} else if (type.contains("[]")) {
				int start = 0;
				int stop = type.indexOf("[");
				type = type.substring(start, stop);

				// this is a reference to resource
				// make sure we create a phantom resource for it
				if (!MongooseTypeConverter.isPrimitive(type)) {
					Resource resource = new Resource(type);
					ResourceList.INSTANCE.add(resource);
				}
			}
			if (!Utils.isReservedWord(key)) {
				properties.add(new ResourceProperty(key, type, ""));
			}
		}
	}

	/**
	 * Create constructor to account for inner classes
	 * 
	 * @return
	 */
	public Resource(String name) {
		this.name = name;
		if (!Utils.isReservedWord(name)) {
			properties.add(new ResourceProperty("id", "String", ""));
		}
	}

	public String getName() {
		return name;
	}

	public List<ResourceProperty> getProperties() {
		return properties;
	}

	public Mdn getMdn() {
		return mdn;
	}

}
