package com.nepian.npcore.util.exception;

import com.nepian.npcore.util.command.SubCommand;

public class InValidCommandArgsException extends Exception {
	private static final long serialVersionUID = 4200495451115021016L;
	private SubCommand subCommand;
	private String label;
	private String[] args;

	public InValidCommandArgsException(SubCommand subCommand, String label, String[] args) {
		super("コマンドの引数が間違っています");
		this.subCommand = subCommand;
		this.label = label;
		this.args = args;
	}
	
	public SubCommand getSubCommand() {
		return subCommand;
	}
	
	public String getLabel() {
		return label;
	}
	
	public String[] getArgs() {
		return args;
	}
}
