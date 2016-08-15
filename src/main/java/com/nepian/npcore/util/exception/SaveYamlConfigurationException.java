package com.nepian.npcore.util.exception;

import java.io.File;

public class SaveYamlConfigurationException extends Exception {
	private static final long serialVersionUID = 4864545153775088903L;

	public SaveYamlConfigurationException(File file) {
		super("ファイルの書き込みに失敗しました (ファイル名: " + file.getName() + ")");
	}
}
