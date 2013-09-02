package com.nodemy.modeldrivennode.parser;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.apache.velocity.Template;

/**
 * Build mongoose models and related test data.
 * 
 * @author broward
 * 
 */
public class ModelsJsTemplates extends ModelDrivenNode {

	public ModelsJsTemplates() {
		run("models.vm", "models.js", MODEL_OUTPUT);
		run("testdata.vm", "testdata1.js", MODEL_OUTPUT);
		run("testdata.vm", "testdata2.js", MODEL_OUTPUT);
	}
	
	private void run(String templateFile, String nodeFile, String path) {
		context.put("list", ResourceList.INSTANCE.getResources());
		context.put("schema", APPINSTANCE);
		context.put("randomId", (int) (Math.random() * 100)); 

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
