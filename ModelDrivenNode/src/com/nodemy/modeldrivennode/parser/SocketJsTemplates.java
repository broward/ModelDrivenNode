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
public class SocketJsTemplates extends ModelDrivenNode {

	public SocketJsTemplates() {
		run("socketserver.vm", "socketserver.js", SOCKET_OUTPUT);
	}
	
	private void run(String templateFile, String nodeFile, String path) {
		context.put("list", ResourceList.INSTANCE.getResources());
		context.put("schema", APPINSTANCE);

		Template t = ve.getTemplate(SOCKETS + templateFile);
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
