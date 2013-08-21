package com.nodemy.modeldrivennode.parser;

import java.io.File;
import java.util.Iterator;

import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.writer.FileCodeWriter;
import com.sun.tools.xjc.Plugin;
import com.sun.tools.xjc.api.ErrorListener;
import com.sun.tools.xjc.api.S2JJAXBModel;
import com.sun.tools.xjc.api.SchemaCompiler;
import com.sun.tools.xjc.api.XJC;

/**
 * Converts our xsd elements into resources which are usable with velocity
 * templates.
 * 
 * @author broward
 * 
 */
public enum ResourceBuilder {
	INSTANCE;

	private SchemaCompiler sc = XJC.createSchemaCompiler();

	/**
	 * Add schemas for processing
	 * 
	 * @param fileName
	 */
	public void addSchema(String fileName) {
		sc.resetSchema();
		sc.setErrorListener(new ErrorListener() {
			public void error(SAXParseException exception) {
				//exception.printStackTrace();
				System.out.println(exception.getMessage());
			}

			@Override
			public void fatalError(SAXParseException arg0) {
				//arg0.printStackTrace();
				System.out.println(arg0.getMessage());
			}

			@Override
			public void info(SAXParseException arg0) {
				//arg0.printStackTrace();
				System.out.println(arg0.getMessage());
			}

			@Override
			public void warning(SAXParseException arg0) {
				//arg0.printStackTrace();
				System.out.println(arg0.getMessage());
			}
		});

		sc.parseSchema(new InputSource(fileName));
	}

	public void go() {
		try {
			// sc.forcePackageName("generated");
			S2JJAXBModel model = sc.bind();

			System.out.println("");
			System.out.println("binding the schema");
			JCodeModel jcm = model.generateCode(new Plugin[0], null);

			System.out.println("generated the schema!");

			Iterator<JPackage> packages = jcm.packages();
			while (packages.hasNext()) {
				JPackage pkg = packages.next();
				Iterator<JDefinedClass> classes = pkg.classes();
				while (classes.hasNext()) {

					// convert classes into resources
					JDefinedClass cls = classes.next();
					JClass[] innerClasses = cls.listClasses();
					for (JClass child : innerClasses) {
						Resource resource = new Resource(child.name());
						ResourceList.INSTANCE.add(resource);
					}
					Resource resource = new Resource(cls.name(), cls.fields());
					ResourceList.INSTANCE.add(resource);
				}
			}
			// write java classes out for now for debugging
			//File file = new File("/temp");
			//jcm.build(new FileCodeWriter(file));

			// re-sort list by dependency order,
			// children come before parents
			ResourceList.INSTANCE.sortByDependency();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
