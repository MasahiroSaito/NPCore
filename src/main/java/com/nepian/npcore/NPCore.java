package com.nepian.npcore;

import org.bukkit.plugin.java.JavaPlugin;

import com.nepian.npcore.command.NPCoreCommand;
import com.nepian.npcore.listener.PlayerJoinListener;
import com.nepian.npcore.util.CommandUtil;
import com.nepian.npcore.util.ListenerUtil;
import com.nepian.npcore.util.Messenger;
import com.nepian.npcore.util.sqlite.SQLite;

public class NPCore extends JavaPlugin {
	private static NPCore npcore;

	private UserdataController userdataController;
	private Config config;
	private Messenger messenger;
	private SQLite sqlite;

	@Override
	public void onEnable() {
		npcore = this;
		config = new Config(npcore);
		messenger = new Messenger(npcore, config.isDebugMessage());
		sqlite = new SQLite(this, "userdatas.db");
		userdataController = new UserdataController(sqlite);

		loadCommand();
		loadListener();
	}
	
	@Override
	public void onDisable() {
		sqlite.close();
	}
	
	/* Private Method ------------------------------------------------------ */
	
	private void loadCommand() {
		CommandUtil.registerCommand(npcore, "npcore", new NPCoreCommand(npcore));
	}
	
	private void loadListener() {
		ListenerUtil.register(npcore, new PlayerJoinListener(npcore));
	}

	/* Getter -------------------------------------------------------------- */

	public static NPCore getNPCore() {
		return npcore;
	}

	public UserdataController getUserdataController() {
		return this.userdataController;
	}
	
	public Config getConf() {
		return this.config;
	}
	
	public Messenger getMessenger() {
		return this.messenger;
	}
	
	public SQLite getSQLite() {
		return this.sqlite;
	}
}
