package com.nepian.npcore.command.sub;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.nepian.npcore.NPCore;
import com.nepian.npcore.UserdataController;
import com.nepian.npcore.util.command.CommandSenderType;
import com.nepian.npcore.util.command.SubCommand;
import com.nepian.npcore.util.command.SubCommandType;

public class SetnameCommand extends SubCommand {
	private NPCore npcore;
	
	public SetnameCommand(NPCore npcore) {
		super("setname", "sn");
		super.addCommandSenderType(CommandSenderType.PLAYER);
		this.npcore = npcore;
	}

	@Override
	public void execute(CommandSender sender, String label, String[] args)
			throws CommandException {

		Player player = (Player) sender;
		String name = args[0];
		UserdataController uc = npcore.getUserdataController();
		
		player.setPlayerListName(name);
		player.setDisplayName(name);
		uc.updateName(player, name);
	}

	@Override
	public String getPossibleArguments() {
		return "<名前>";
	}

	@Override
	public int getMinimumArguments() {
		return 1;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public SubCommandType getType() {
		return SubCommandType.GENERIC;
	}

}
