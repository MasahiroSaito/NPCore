package com.nepian.npcore.util.command;

import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.nepian.npcore.util.Messenger;
import com.nepian.npcore.util.Util;
import com.nepian.npcore.util.exception.InValidCommandArgsException;
import com.nepian.npcore.util.exception.InValidCommandSenderException;
import com.nepian.npcore.util.exception.NotHasCommandPermissionException;

public abstract class MainCommand extends SubCommand implements CommandExecutor {
	protected Messenger messenger;
	private List<SubCommand> subCommands;

	public MainCommand(Messenger messenger, String name, String...names) {
		super(name, names);
		this.subCommands = Util.newList();
		this.messenger = messenger;
	}
	
	/* Method -------------------------------------------------------------- */

	@Override
	public boolean onCommand(CommandSender sender,
			Command command, String label, String[] args) {
		
		try {
			if (this.onSubCommand(sender, label, args)) return true;
			if (this.onMainCommand(sender, label, args)) return true;
		} catch (NotHasCommandPermissionException e) {
			messenger.sendFailed(sender, e.getMessage());
		} catch (CommandException e) {
			messenger.sendFailed(sender, e.getMessage());
		} catch (InValidCommandArgsException e) {
			messenger.sendFailed(sender, e.getMessage());
			messenger.send(sender, e.getSubCommand().getUsage(label));
		} catch (InValidCommandSenderException e) {
			messenger.sendFailed(sender, e.getMessage());
		}

		return true;
	}
	
	protected void registerSubCommand(SubCommand command) {
		subCommands.add(command);
	}
	
	public List<SubCommand> getSubCommands() {
		return subCommands;
	}
	
	/* Private Method ------------------------------------------------------ */
	
	private boolean onMainCommand(CommandSender sender, String label, String[] args)
			throws NotHasCommandPermissionException, CommandException,
			InValidCommandArgsException, InValidCommandSenderException {
		
		if (!this.isValidTrigger(label)) {
			return false;
		}
		
		if (!this.isValidCommandSender(sender)) {
			throw new InValidCommandSenderException(sender);
		}
		
		if (!this.hasPermission(sender)) {
			throw new NotHasCommandPermissionException(this);
		}
		
		if (this.getMinimumArguments() <= args.length) {
			this.execute(sender, label, args);
			return true;
		} else {
			throw new InValidCommandArgsException(this, label, args);
		}
	}
	
	private boolean onSubCommand(CommandSender sender, String label, String[] args)
			throws NotHasCommandPermissionException, CommandException,
			InValidCommandArgsException, InValidCommandSenderException {
		
		if (args.length <= 0) {
			return false;
		}
		
		for (SubCommand subCommand : subCommands) {
			if (!subCommand.isValidTrigger(args[0])) {
				continue;
			}
			
			if (!subCommand.isValidCommandSender(sender)) {
				throw new InValidCommandSenderException(sender);
			}
			
			if (!subCommand.hasPermission(sender)) {
				throw new NotHasCommandPermissionException(subCommand);
			}
			
			if (subCommand.getMinimumArguments() <= args.length - 1) {
				subCommand.execute(sender, label, Arrays.copyOfRange(args, 1, args.length));
				return true;
			} else {
				throw new InValidCommandArgsException(subCommand, label, args);
			}
		}
		
		return false;
	}
}
