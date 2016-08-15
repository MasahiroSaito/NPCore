package com.nepian.npcore.command.sub;

import java.util.UUID;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.nepian.npcore.NPCore;
import com.nepian.npcore.UserdataController;
import com.nepian.npcore.util.Messenger;
import com.nepian.npcore.util.command.CommandSenderType;
import com.nepian.npcore.util.command.SubCommand;
import com.nepian.npcore.util.command.SubCommandType;

public class ListCommand extends SubCommand {
	private NPCore npcore;

	public ListCommand(NPCore npcore) {
		super("list", "l");
		setPermission("npcore.command.list");
		addCommandSenderType(CommandSenderType.CONSOLE);
		this.npcore = npcore;
	}
	
	@Override
	public void execute(CommandSender sender, String label, String[] args) throws CommandException {
		Messenger mes = npcore.getMessenger();
		UserdataController uc = npcore.getUserdataController();
		
		for (UUID uuid : uc.getUUIDs()) {
			mes.send(sender, uc.getName(uuid) + ": " + uuid.toString());
		}
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
