package com.nepian.npcore;

import org.bukkit.plugin.java.JavaPlugin;

import com.nepian.npcore.util.ConfigBase;
import com.nepian.npcore.util.FileUtil;
import com.nepian.npcore.util.FileUtil.FileType;

public class Config extends ConfigBase {
	public static final String IS_DEBUG_MESSAGE = "IS_DEBUG_MESSAGE";
	
	public Config(JavaPlugin plugin) {
		super(FileUtil.load(plugin.getDataFolder(), "config.yml", FileType.FILE));
	}

	@Override
	protected void setConfigs() {
		setConfig(IS_DEBUG_MESSAGE, false);
	}
	
	public boolean isDebugMessage() {
		return getBool(IS_DEBUG_MESSAGE);
	}
	
	/* Private Method ------------------------------------------------------ */
	
	private boolean getBool(String key) {
		return (boolean) get(key);
	}
}
