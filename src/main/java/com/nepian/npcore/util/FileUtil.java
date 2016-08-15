package com.nepian.npcore.util;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

public class FileUtil {

	public enum FileType { FILE, FOLDER }

	public static File load(File folder, String fileName, FileType type) {
		File file = new File(folder, fileName);

		if (!file.exists()) {
			try {
				if (file.getParent() != null) {
					file.getParentFile().mkdirs();
				}

				switch (type) {
				case FILE:
					file.createNewFile();
					break;
				case FOLDER:
					file.mkdir();
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return file;
	}

	/**
	 * YamlConfigurationを取得する
	 * @param file
	 * @return
	 */
	public static YamlConfiguration getYml(File file) {
		return YamlConfiguration.loadConfiguration(file);
	}
}
