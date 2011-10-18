package org.fierry.build.utils;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resource {
	private static final String EXT = ".template";
	private static final String DIR = "/org/fierry/build/templates" + File.separator;

	private static Map<String, String> cache = new HashMap<String, String>();
	private String data;
	
	public static Resource get(String resource) {
		return new Resource(readResource(resource));
	}
	
	private static String readResource(String resource) {
		if(!cache.containsKey(resource)) {
			InputStream in = Resource.class.getResourceAsStream(DIR + resource + EXT);
			Scanner scanner = new Scanner(in).useDelimiter("\\A");
			
			cache.put(resource, scanner.next());
			scanner.close();
		}
		return cache.get(resource);
	}
	
	private Resource(String data) {
		this.data = data;
	}
	
	public Resource replace(String name, String value) {
		data = data.replace("${" + name + "}", value);
		return this;
	}
	
	public Resource replaceLine(String name, String value) {
		Pattern p = Pattern.compile("(?<=(^|\n)) *\\$\\{" + name + "\\} *(?=(\n|$))");
		Matcher m = p.matcher(data);
		
		while(m.find()) {
			int spaces = m.group().indexOf('$');
			data = data.replace(m.group(), Lines.indent2(value, spaces));
			m.reset(data);
		}
		assert data.indexOf("${" + name + "}") == -1 : "Illegal sth";
		return this;
	}
	
	public void appendTo(StringBuilder builder) {
		builder.append(data);
	}
	
	public String toString() {
		return data;
	}
	
	public byte[] getBytes() {
		return data.getBytes();
	}
}