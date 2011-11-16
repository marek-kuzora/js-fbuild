package org.fierry.build.resources;

import java.nio.file.Path;

import org.fierry.build.utils.Template;

public class Script extends Resource {

	public Script(Path path) {
		super(path);
	}
	
	public void deploy(StringBuilder builder) {

		Template.get("modules/script")
				.replace("name", name)
				.replaceLine("content", content)
				.appendTo(builder);
	}
}