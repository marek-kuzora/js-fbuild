package org.fierry.build.project;

import java.util.ArrayList;
import java.util.Collection;

public class Args {
	private static final String BUILD_SWITCH = "-b";

	private String command;
	private Collection<String> projects;
	
	public Args(String[] args) {
		this.command = args.length > 0 ? args[0] : BUILD_SWITCH;
		
		this.projects = new ArrayList<String>();
		for(int i = 1; i < args.length; i++) { projects.add(args[i]); }
	}
	
	public Boolean isContinous() {
		return isBuild();
	}
	
	public Boolean isBuild() {
		return command.equals("-b") || command.equals("-build");
	}
	
	public Boolean isCompile() {
		return command.equals("-c") || command.equals("-compile");
	}
	
	public Boolean isCreate() {
		return command.equals("-n") || command.equals("-new");
	}
	
	public Collection<String> getProjects() {
		return projects;
	}
}