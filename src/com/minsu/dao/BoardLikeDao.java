package com.minsu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.minsu.util.ConnectSql;

public class BoardLikeDao {

	private final ConnectSql connectSql;
	
	public BoardLikeDao() {
		this.connectSql = new ConnectSql();
	}

	public boolean find(int brdSeq, int userSeq) {

		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT brd_seq FROM board_like "
				+ "WHERE brd_seq = "+brdSeq+" AND user_seq = "+userSeq+" LIMIT 1 ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean save(int brdSeq, int userSeq) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "INSERT INTO board_like(brd_seq, user_seq) "
				+ "VALUES("+brdSeq+", "+userSeq+") ";
		try {
			stmt = conn.createStatement();
			resultCount = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
					if(stmt!=null)stmt.close();
					if(conn!=null)conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return resultCount>0;
	}

	public boolean delete(int brdSeq, int userSeq) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM board_like " +
				"WHERE brd_seq="+brdSeq+" and user_seq="+userSeq;
		try {
			stmt = conn.createStatement();
			resultCount = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(stmt!=null)stmt.close();
				if(conn!=null)conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultCount>0;
	}

	
	
}
