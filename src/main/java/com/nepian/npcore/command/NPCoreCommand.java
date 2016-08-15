package com.nepian.npcore.command;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.nepian.npcore.NPCore;
import com.nepian.npcore.command.sub.ListCommand;
import com.nepian.npcore.command.sub.SetnameCommand;
import com.nepian.npcore.util.command.MainCommand;
import com.nepian.npcore.util.command.SubCommandType;

public class NPCoreCommand extends MainCommand {
	
	public NPCoreCommand(NPCore npcore) {
		super(npcore.getMessenger(), "npcore");
		registerSubCommands(npcore);
	}
	
	protected void registerSubCommands(NPCore npcore) {
		registerSubCommand(new ListCommand(npcore));
		registerSubCommand(new SetnameCommand(npcore));
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		
		messenger.sendSuccess(sender, "NPCoreCommand を実行しました");
	}

	@Override
	public String getPossibleArguments() {
		return "";
	}

	@Override
	public int getMinimumArguments() {
		return 0;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.HIDDEN;
	}

}
