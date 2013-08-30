package com.nodemy.modeldrivennode.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.Template;

/**
 * Build main node.js application - app.js, config.js and package.json.
 * They only need knowledge of schemas and versions.
 * 
 * @author broward
 * 
 */
public class NodeJsTemplates extends ModelDrivenNode {

	public NodeJsTemplates() {
		run("app.vm", "app.js", ROOT);
		run("package.vm", "package.json", ROOT);
		run("config.vm", "config.js", ROOT);
	}

	private void run(String templateFile, String nodeFile, String path) {
		context.put("application", APPLICATION);
		context.put("version", VERSION);
		context.put("urlRoot", URL_ROOT);
		context.put("schema", SCHEMA);

		Template t = ve.getTemplate(NODEJS + templateFile);
		Writer writer;
		try {
			writer = new FileWriter(path + nodeFile);
			t.merge(context, writer);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
