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
public class QueuesJsTemplates extends ModelDrivenNode {

	public QueuesJsTemplates() {
		run("kue.vm", "kue.js", QUEUE_OUTPUT);
	}

	private void run(String templateFile, String nodeFile, String path) {
		context.put("schema", APPINSTANCE);
		context.put("version", VERSION);

		Template t = ve.getTemplate(QUEUES + templateFile);
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
