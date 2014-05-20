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
public class RoutesJsTemplates extends ModelDrivenNode {

	public RoutesJsTemplates() {
		context.put("list", ResourceList.INSTANCE.getResources());
		context.put("schema", APPINSTANCE);
		context.put("version", VERSION);
		context.put("entryPoints", ObjectGrapher.INSTANCE.getEntrypoints());

		String fileName = ROUTE_OUTPUT + "routes.js";
		Template t = ve.getTemplate(NODEJS + "routes.vm");
		Writer writer;
		try {
			writer = new FileWriter(fileName);
			t.merge(context, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
