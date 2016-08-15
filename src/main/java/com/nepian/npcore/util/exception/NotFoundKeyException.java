package com.nepian.npcore.util.exception;

public class NotFoundKeyException extends Exception {
	private static final long serialVersionUID = -1861785286471176082L;

	public NotFoundKeyException(String key) {
		super("キーが存在しません (キー: " + key + ")");
	}
}
