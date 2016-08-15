package com.nepian.npcore.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.nepian.npcore.NPCore;
import com.nepian.npcore.UserdataController;
import com.nepian.npcore.util.Messenger;

public class PlayerJoinListener implements Listener {
	private static NPCore npcore;
	private static UserdataController uc;
	private static Messenger mes;

	public PlayerJoinListener(NPCore npcore) {
		PlayerJoinListener.npcore = npcore;
		uc = npcore.getUserdataController();
		mes = npcore.getMessenger();
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public static void onPlayerJoin(final PlayerJoinEvent event) {
		Bukkit.getScheduler().runTaskAsynchronously(npcore, new Runnable() {
			@Override
			public void run() {
				Player player = event.getPlayer();
				if (!uc.has(player)) {
					mes.debug("プレイヤーは非登録なのでデータを追加します");
					uc.register(player);
				} else {
					String name = uc.getName(player.getUniqueId());
					player.setPlayerListName(name);
					player.setDisplayName(name);
				}
			}
		});
	}
}
