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
		run("config.vm", "config.js", ROOT);
		run("package.vm", "package.json", ROOT);
	}

	private void run(String templateFile, String nodeFile, String path) {
		context.put("schema", SCHEMA);
		context.put("version", VERSION);

		Template t = ve.getTemplate(TEMPLATES + templateFile);
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
