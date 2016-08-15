package com.nepian.npcore;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

import com.nepian.npcore.util.Util;
import com.nepian.npcore.util.sqlite.SQLite;

public class UserdataController {
	private final String TABLE = "userdata";
	private final String UUID = "uuid";
	private final String NAME = "name";
	
	private final String select = "select * from " + TABLE + " where " + UUID + " = ?";
	private final String delete = "delete from " + TABLE + " where " + UUID + " = ?";
	private final String insert = "insert into " + TABLE + " values (?,?)";
	private final String updateName = "update " + TABLE + " set " + NAME + " = ? where " + UUID + " = ?";
	private final String selectUUIDs = "select " + UUID + " from " + TABLE;
	private final String selectName = "select " + NAME + " from " + TABLE + " where " + UUID + " = ?";
	
	private SQLite sqlite;
	
	public UserdataController(SQLite sqlite) {
		this.sqlite = sqlite;
		this.createTable();
	}
	
	/**
	 * プレイヤーのデータを登録する
	 * @param player 対象のプレイヤー
	 */
	public void register(OfflinePlayer player) {
		PreparedStatement ps;
		try {
			ps = sqlite.getPreparedStatement(insert);
			ps.setString(1, player.getUniqueId().toString());
			ps.setString(2, player.getName());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * プレイヤーのデータの名前を更新する
	 * @param player 対象のプレイヤー
	 */
	public void updateName(OfflinePlayer player, String name) {
		PreparedStatement ps;
		try {
			ps = sqlite.getPreparedStatement(updateName);
			ps.setString(1, name);
			ps.setString(2, player.getUniqueId().toString());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * プレイヤーのデータが登録されているか確認する
	 * @param player 確認するプレイヤー
	 * @return (登録済)-> true (非登録)-> false
	 */
	public boolean has(OfflinePlayer player) {
		try {
			PreparedStatement ps = sqlite.getPreparedStatement(select);
			ps.setString(1, player.getUniqueId().toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * プレイヤーのデータを削除する
	 * @param player データを削除するプレイヤー
	 */
	public void delete(OfflinePlayer player) {
		if (has(player)) {
			PreparedStatement ps;
			try {
				ps = sqlite.getPreparedStatement(delete);
				ps.setString(1, player.getUniqueId().toString());
				ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * テーブルを作成する
	 */
	private void createTable() {
		String token = "create table if not exists " + TABLE + " ( "
				+ UUID + ", "
				+ NAME + " )";
		try {
			sqlite.getPreparedStatement(token).execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 登録されているUUIDの一覧を取得する
	 * @return (List<UUID>)-> UUIDの一覧のリスト
	 */
	public List<UUID> getUUIDs() {
		PreparedStatement ps;
		ResultSet rs;
		try {
			ps = sqlite.getPreparedStatement(selectUUIDs);
			rs = ps.executeQuery();
			List<UUID> uuids = Util.newList();
			while(rs.next()) {
				uuids.add(java.util.UUID.fromString(rs.getString(1)));
			}
			return uuids;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * プレイヤーの名前を取得する
	 * @param uuid 対象プレイヤーのUUID
	 * @return (String)-> プレイヤーの名前
	 */
	public String getName(UUID uuid) {
		try {
			PreparedStatement ps = sqlite.getPreparedStatement(selectName);
			ps.setString(1, uuid.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
