package com.nepian.npcore.util.sqlite;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.plugin.java.JavaPlugin;

import com.nepian.npcore.util.FileUtil;
import com.nepian.npcore.util.FileUtil.FileType;

/**
 * データベース SQLite を利用するためのクラス
 */
public class SQLite {
	private File file;
	private Connection connection;

	/**
	 * コンストラクタ
	 * @param file SQLiteを使用するファイル
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public SQLite(File file) {
		this.file = file;
		this.connection = connect();
	}
	
	/**
	 * コンストラクタ
	 * @param plugin SQLiteを使用するプラグイン
	 * @param fileName SQLiteを使用するファイルの名前
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * プラグイン(plugin)のフォルダを作成し、引数(fileName)でファイルを作成
	 */
	public SQLite(JavaPlugin plugin, String fileName) {
		this(FileUtil.load(plugin.getDataFolder(), fileName, FileType.FILE));
	}

	/**
	 * データベースと接続する
	 * @return (Connection)-> コネクション
	 */
	private Connection connect() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + file);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return c;
	}

	/**
	 * データベースから切断する
	 * @throws SQLException 
	 */
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * データベースに使用しているファイルを取得する
	 * @return (File)-> データベースに使用しているファイル
	 */
	public File getFile() {
		return file;
	}
	
	/**
	 * データベースとのコネクションを取得する
	 * @return (Connection)-> データベースとのコネクション
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * データベースとのPreparedStatementを取得する
	 * @param token 実行するSQL文
	 * @return (PreparedStatement)-> データベースとのPreparedStatement
	 * @throws SQLException
	 */
	public PreparedStatement getPreparedStatement(String token) throws SQLException {
		return connection.prepareStatement(token);
	}
}
