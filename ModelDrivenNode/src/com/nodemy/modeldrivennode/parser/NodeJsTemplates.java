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
		run("app.vm", "app.js", SERVER_ROOT);
		run("config.vm", "config.js", SERVER_ROOT);
		run("package.vm", "package.json", SERVER_ROOT);
		run("models.vm", SCHEMA + "models.js", MODEL_OUTPUT);
		run("testdata.vm", SCHEMA + "testdata.js", MODEL_OUTPUT);
	}
	
	private void run(String templateFile, String nodeFile, String path) {
		context.put("list", ResourceList.INSTANCE.getResources());

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
