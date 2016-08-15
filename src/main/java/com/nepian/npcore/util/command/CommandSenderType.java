package com.nepian.npcore.util.command;

import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.entity.Player;

public enum CommandSenderType {
	
	REMOTE_CONSOLE() {
		public boolean isValid(CommandSender sender) {
			if (sender instanceof RemoteConsoleCommandSender) return true;
			else return false;
		}
	},
	PLAYER() {
		public boolean isValid(CommandSender sender) {
			if (sender instanceof Player) return true;
			else return false;
		}
	},
	CONSOLE() {
		public boolean isValid(CommandSender sender) {
			if (sender instanceof ConsoleCommandSender) return true;
			else return false;
		}
	};

	public abstract boolean isValid(CommandSender sender);
}
