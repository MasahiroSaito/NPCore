package com.nepian.npcore.util;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerUtil {

	/**
	 * プラグインにリスナーを追加する
	 * @param plugin
	 * @param listener
	 */
	public static void register(JavaPlugin plugin, Listener listener) {
		plugin.getServer().getPluginManager().registerEvents(listener, plugin);
	}
}
