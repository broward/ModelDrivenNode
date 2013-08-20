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
public class SocketJsTemplates extends ModelDrivenNode {

	public SocketJsTemplates() {
		context.put("list", ResourceList.INSTANCE.getResources());
		context.put("schema", SCHEMA);

		String fileName = SOCKET_OUTPUT + SCHEMA + "sockets.js";
		Template t = ve.getTemplate(SOCKETS + "sockets.vm");
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
