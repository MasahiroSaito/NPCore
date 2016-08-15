package com.nepian.npcore.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class Messenger {
	protected final JavaPlugin plugin;
	protected final String prefix;
	protected boolean debugOn;

	public Messenger(JavaPlugin plugin, boolean debugOn) {
		this.plugin = plugin;
		this.prefix = "&d" + plugin.getName() + ":&r ";
		this.debugOn = debugOn;
	}

	/* Methods ------------------------------------------------------------- */
	
	/**
	 * 指定した送信者にプラグイン名無しで色つきのメッセージを送信する
	 * @param sender 対象の送信者
	 * @param obj 送信するメッセージ
	 */
	public void sendNoPre(CommandSender sender, Object obj) {
		sender.sendMessage(
				ChatColor.translateAlternateColorCodes('&', obj.toString()));
	}

	/**
	 * 指定したSenderに色つきのメッセージを送信する
	 * @param sender
	 * @param obj
	 */
	public void send(CommandSender sender, Object obj) {
		sender.sendMessage(
				ChatColor.translateAlternateColorCodes('&', prefix + obj));
	}

	/**
	 * 指定したSenderに失敗メッセージを送信する
	 * @param sender
	 * @param obj
	 */
	public void sendFailed(CommandSender sender, Object obj) {
		send(sender, "&4FAILED:&r " + obj);
	}

	/**
	 * 指定したSenderに成功メッセージを送信する
	 * @param sender
	 * @param obj
	 */
	public void sendSuccess(CommandSender sender, Object obj) {
		send(sender, "&9SUCCESS:&r " + obj);
	}

	/**
	 * サーバーのコンソールにメッセージを送信する
	 * @param obj
	 */
	public void log(Object obj) {
		send(Bukkit.getServer().getConsoleSender(), obj);
	}

	/**
	 * サーバーのコンソールにエラーメッセージを送信する
	 * @param obj
	 */
	public void error(Object obj) {
		log("&4ERROR:&r " + obj);
	}

	/**
	 * サーバーのコンソールにデバッグメッセージを送信する
	 * @param obj
	 */
	public void debug(Object obj) {
		if (debugOn) {
			log("&eDEBUG:&r " + obj);
		}
	}

	/**
	 * サーバーのコンソールに成功メッセージを送信する
	 * @param obj
	 */
	public void success(Object obj) {
		debug("&9SUCCESS:&r " + obj);
	}

	/**
	 * サーバーのコンソールに失敗メッセージを送信する
	 * @param obj
	 */
	public void failed(Object obj) {
		debug("&4FAILED:&r " + obj);
	}

	/* Setter -------------------------------------------------------------- */

	public void setDebugOn(boolean bool) {
		this.debugOn = bool;
	}
}
