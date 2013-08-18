package com.nodemy.modeldrivennode.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.Template;

/**
 * Build routes.js, our REST API, from template merge.
 * 
 * @author broward
 * 
 */
public class RoutesJsTemplate extends ModelDrivenNode {

	public RoutesJsTemplate() {
		context.put("list", ResourceList.INSTANCE.getResources());
		context.put("schema", SCHEMA);
		String fileName = ROUTE_OUTPUT + SCHEMA + "routes.js";
		Template t = ve.getTemplate(TEMPLATES + "routes.vm");
		Writer writer;
		try {
			writer = new FileWriter(fileName);
			t.merge(context, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		context.put("list", ResourceList.INSTANCE.getResources());
		context.put("schema", SCHEMA);
		fileName = ROUTE_OUTPUT + "jade.js";
		t = ve.getTemplate(TEMPLATES + "jade.vm");
		try {
			writer = new FileWriter(fileName);
			t.merge(context, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
