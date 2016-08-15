package com.nepian.npcore.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;

import com.nepian.npcore.util.exception.NotFoundKeyException;
import com.nepian.npcore.util.exception.SaveYamlConfigurationException;

public abstract class ConfigBase {
	protected File configFile;
	protected Map<String, Object> configs;

	public ConfigBase(File configFile) {
		this.configFile = configFile;
		this.configs = Util.newMap();
		this.setConfigs();
		this.read();
	}
	
	/* Method -------------------------------------------------------------- */
	
	/**
	 * コンフィグを更新する
	 * @param key
	 * @param value
	 * @throws NotFoundKeyException
	 * @throws SaveYamlConfigurationException
	 */
	public void update(String key, Object value)
			throws NotFoundKeyException, SaveYamlConfigurationException {
		
		if (configs.containsKey(key)) {
			write(key, value);
			put(key, value);
		} else {
			throw new NotFoundKeyException(key);
		}
	}

	/**
	 * コンフィグを追加する
	 * @param key
	 * @param value
	 * @return
	 */
	public Object put(String key, Object value) {
		return configs.put(key, value);
	}

	/**
	 * コンフィグを取得する
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return configs.get(key);
	}
	
	/**
	 * コンフィグを設定する
	 * @param key
	 * @param value
	 */
	protected void setConfig(String key, Object value) {
		this.put(key, value);
	}
	
	protected abstract void setConfigs();
	
	/* Private Method ------------------------------------------------------ */

	private void read() {
		YamlConfiguration data = FileUtil.getYml(configFile);
		
		for (String key : configs.keySet()) {
			if (!data.contains(key)) {
				try {
					write(key, get(key));
				} catch (SaveYamlConfigurationException e) {
					e.printStackTrace();
				}
			} else {
				put(key, data.get(key));
			}
		}
	}
	
	private void write(String key, Object value)
			throws SaveYamlConfigurationException {
		
		YamlConfiguration data = FileUtil.getYml(configFile);
		
		data.set(key, value);
		
		try {
			data.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
			throw new SaveYamlConfigurationException(configFile);
		}
	}
}
