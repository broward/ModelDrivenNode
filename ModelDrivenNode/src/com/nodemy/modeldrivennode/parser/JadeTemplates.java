package com.nodemy.modeldrivennode.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.Template;

/**
 * Build jade views wth appropriate schema/versioning info
 * 
 * @author broward
 * 
 */
public class JadeTemplates extends ModelDrivenNode {

	public JadeTemplates() {
		run("api.vm", "api.jade", JADE_OUTPUT);
		run("collections.vm", "collections.jade", JADE_OUTPUT);
		run("doc.vm", "doc.jade", JADE_OUTPUT);
		run("index.vm", "index.jade", JADE_OUTPUT);
		run("layout.vm", "layout.jade", JADE_OUTPUT);
		run("models.vm", "models.jade", JADE_OUTPUT);
	}

	private void run(String templateFile, String nodeFile, String path) {
		context.put("schema", APPINSTANCE);
		context.put("version", VERSION);

		Template t = ve.getTemplate(VIEWS + templateFile);
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
