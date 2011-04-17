package org.fierry.build.filters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.fierry.build.files.MemoryFile;
import org.fierry.build.projects.IProject;

public class JavascriptFileFilter implements IFileFilter {
	public static final String FILE_EXT = ".js";
	
	@Override
	public Boolean accept(Path path, IProject project) {
		if(Files.isDirectory(project.getDirectory().resolve(path))) { return false; }
		
		String name = path.getFileName().toString();
		String ext = name.substring(name.lastIndexOf('.'));

		return ext.equals(FILE_EXT);
	}

	@Override
	public void fileCreated(Path path, IProject project) {
		fileUpdated(path, project);
	}

	@Override
	public void fileUpdated(Path path, IProject project) {
		Path abs = project.getDirectory().resolve(path);
		try {
			String name = path.getFileName().toString();
			String content = new String(Files.readAllBytes(abs));
			
			MemoryFile file = new MemoryFile(name, content);
			project.getPackage(path.getParent()).setFile(file);
		}
		catch(IOException e) { throw new RuntimeException(e); }
	}

	@Override
	public void fileDeleted(Path path, IProject project) {
		String name = path.getFileName().toString();
		project.getPackage(path.getParent()).deleteFile(name);
	}

}
