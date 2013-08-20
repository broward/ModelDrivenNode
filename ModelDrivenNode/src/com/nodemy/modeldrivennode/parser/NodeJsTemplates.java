package com.nodemy.modeldrivennode.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.Template;

/**
 * Build app.js, main application module, from template merge.
 * 
 * @author broward
 * 
 */
public class NodeJsTemplates extends ModelDrivenNode {

	public NodeJsTemplates() {
		run("app.vm", "app.js", ROOT);
		run("config.vm", "config.js", ROOT);
		run("package.vm", "package.json", ROOT);
		run("models.vm", SCHEMA + "models.js", MODEL_OUTPUT);
	}
	
	private void run(String templateFile, String nodeFile, String path) {
		context.put("list", ResourceList.INSTANCE.getResources());
		context.put("schema", SCHEMA);
		context.put("version", VERSION);
		context.put("randomId", (int) (Math.random() * 100)); 

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
