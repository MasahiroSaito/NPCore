package com.nepian.npcore.util.command;

import java.util.List;

import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;

import com.nepian.npcore.util.Util;

public abstract class SubCommand {
	private String name;
	private String permission;
	private String[] aliases;
	private List<CommandSenderType> senderTypes;
	
	public SubCommand(String name, String... aliases) {
		this.name = name;
		this.aliases = aliases;
		this.senderTypes = Util.newList();
	}

	public SubCommand(String name) {
		this(name,new String[0]);
	}

	/**
	 * コマンドを実行する
	 * @param sender -> コマンド実行者
	 * @param label -> 使用したコマンド名
	 * @param args -> コマンドの引数
	 * @throws CommandException
	 */
	public abstract void execute(CommandSender sender,
			String label, String[] args) throws CommandException;

	/**
	 * コマンドの送信者が権限を所持しているか判定する
	 * @param sender -> コマンドの送信者
	 * @return (非所持)-> false (所持)-> true
	 */
	public final boolean hasPermission(CommandSender sender) {
		if (permission == null) return true;
		return sender.hasPermission(permission);
	}

	/**
	 * 指定した文字列がコマンドとして適正か判断する
	 * @param name -> コマンド名
	 * @return (適正)-> true (不適正)-> false
	 */
	public final boolean isValidTrigger(String name) {
		if (this.name.equalsIgnoreCase(name)) {
			return true;
		}

		if (aliases != null) {
			for (String alias : aliases) {
				if (alias.equalsIgnoreCase(name)) {
					return true;
				}
			}
		}

		return false;
	}
	
	/**
	 * コマンドの送信者が適正か判断する
	 * @param sender -> コマンドの送信者
	 * @return (適正)-> true (不適正)-> false
	 */
	public final boolean isValidCommandSender(CommandSender sender) {
		if (senderTypes.isEmpty()) return true;
		for (CommandSenderType type : senderTypes) {
			if (type.isValid(sender)) return true;
		}
		return false;
	}

	/**
	 * コマンドで可能な引数の文字列を取得する
	 * @return
	 */
	public abstract String getPossibleArguments();

	/**
	 * コマンドで最低限必要な引数の長さを取得する
	 * @return
	 */
	public abstract int getMinimumArguments();

	/**
	 * コマンドのチュートリアルメッセージを取得する
	 * @return
	 */
	public abstract String getDescription();

	/**
	 * コマンドのタイプを取得する
	 * @return
	 */
	public abstract SubCommandType getType();

	public String getName() {
		return name;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	public void addCommandSenderType(CommandSenderType type) {
		senderTypes.add(type);
	}
	
	public String getUsage(String label) {
		StringBuilder usage = new StringBuilder("使い方: /");
		usage.append(label).append(" ");
		usage.append(getName()).append(" ");
		usage.append(getPossibleArguments());
		return usage.toString();
	}
	
	public List<CommandSenderType> getCommandSenderTypes() {
		return senderTypes;
	}
}
