package com.nepian.npcore.util.exception;

import com.nepian.npcore.util.command.SubCommand;

public class NotHasCommandPermissionException extends Exception {
	private static final long serialVersionUID = 7509918737975385362L;
	private SubCommand subCommand;

	public NotHasCommandPermissionException(SubCommand subCommand) {
		super("コマンドを実行する権限がありません");
		this.subCommand = subCommand;
	}
	
	public String getPermission() {
		return subCommand.getPermission();
	}
}
