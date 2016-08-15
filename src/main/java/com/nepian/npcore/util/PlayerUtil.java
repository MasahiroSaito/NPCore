package com.nepian.npcore.util;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

public class PlayerUtil {

	/**
	 * プレイヤーのUUIDから名前を取得する
	 * @param uuid
	 * @return
	 */
	public static String getName(UUID uuid) {
		return getOfflinePlayer(uuid).getName();
	}

	/**
	 * プレイヤーのUUIDからOfflinePlayerを取得する
	 * @param uuid
	 * @return
	 */
	public static OfflinePlayer getOfflinePlayer(UUID uuid) {
		return Bukkit.getServer().getOfflinePlayer(uuid);
	}

	/**
	 * プレイヤーの名前からOfflinePlayerを取得する
	 * @param name
	 * @return
	 */
	public static OfflinePlayer getOfflinePlayer(String name) {
		for (OfflinePlayer player : Bukkit.getServer().getOfflinePlayers()) {
			if (player.getName().equals(name)) {
				return player;
			}
		}
		return null;
	}
}
