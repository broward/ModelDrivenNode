package com.nodemy.modeldrivennode;

import java.util.Date;

import com.nodemy.modeldrivennode.parser.MongooseTypeConverter;

/**
 * Generate test data for our schemas.
 * @author broward
 *
 */
public enum TestDataGenerator {
	INSTANCE;
	
	private StringBuilder data = new StringBuilder();

	private TestDataGenerator() {
		data.append("Lorem ipsum dolor sit amet, consectetuer adipiscing elit,");
		data.append("sed diam nonummy nibh euismod tincidunt ut laoreet dolore ");
		data.append("magna aliquam erat volutpat. Ut wisi enim ad minim veniam, ");
		data.append("quis nostrud exerci tation ullamcorper suscipit lobortis nisl ");
		data.append("ut aliquip ex ea commodo consequat. Duis autem vel eum iriure ");
		data.append("dolor in hendrerit in vulputate velit esse molestie consequat, ");
		data.append("vel illum dolore eu feugiat nulla facilisis at vero eros et ");
		data.append("accumsan et iusto odio dignissim qui blandit praesent luptatum ");
		data.append("zzril delenit augue duis dolore te feugait nulla facilisi. ");
		data.append("Nam liber tempor cum soluta nobis eleifend option congue nihil ");
		data.append("imperdiet doming id quod mazim placerat facer possim assum. ");
		data.append("Typi non habent claritatem insitam; est usus legentis in iis qui ");
		data.append("facit eorum claritatem. Investigationes demonstraverunt lectores ");
		data.append("legere me lius quod ii legunt saepius. Claritas est etiam processus ");
		data.append("dynamicus, qui sequitur mutationem consuetudium lectorum. ");
		data.append("Mirum est notare quam littera gothica, quam nunc putamus parum claram, ");
		data.append("anteposuerit litterarum formas humanitatis per seacula quarta decima et ");
		data.append("quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, ");
		data.append("fiant sollemnes in futurum.");
	}
	
	/**
	 * What is our data type?
	 * @param attributeType
	 * @return
	 */
	public String generate(String attributeType) {
		if (attributeType == null) {
			return null;
		} else if (attributeType.equals(MongooseTypeConverter.STRING)) {
			return generateStringData();
		} else if (attributeType.equals(MongooseTypeConverter.NUMBER)) {
			return generateNumberData();
		} else if (attributeType.equals(MongooseTypeConverter.BOOLEAN)) {
			return generateBooleanData();
		} else if (attributeType.equals(MongooseTypeConverter.DATE)) {
			return generateDateData();
		} else {
			// Build mongo-specific syntax to reference a complextype
			// record which was already created earlier in models.js
			int cut = attributeType.indexOf("Schema");
			return attributeType.substring(1, cut) + "Record";
		}
	}

	/** 
	 * Generate data for a String.
	 * @return
	 */
	private String generateStringData() {
		int start = (int) (Math.random() * 1000);
		int stop = (int) (Math.random() * 50) + start + 1;
		return "'" + data.toString().substring(start, stop) + "'";
	}
	
	/**
	 * Generate data for a Number!
	 * @return
	 */
	private String generateNumberData() {
		int temp = (int) (Math.random() * 1000000);
		return temp + "";
	}
	
	/**
	 * Generate data for a boolean.
	 * @return
	 */
	private String generateBooleanData() {
		boolean value = Math.random() < 0.5;
		if (value) {
			return "1";
		} else {
			return "0";
		}
	}
	
	/**
	 * Generate data for a Date!
	 * @return
	 */
	private String generateDateData() {
		double random = (int) (Math.random() * 100000000);
		Date now = new Date();
		random = now.getTime() - (random*100);
		return "moment(" + random + ")";
	}
}
