package com.minsu.dao;

import com.minsu.dto.UserRequestDto;
import com.minsu.dto.UserResponseDto;
import com.minsu.util.ConnectSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {

	private final ConnectSql connectSql;

	public UserDao() {
		this.connectSql = new ConnectSql();
	}

	public boolean findByEmail(String userEmail) {
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_id FROM member "
				+ "WHERE user_email = '"+userEmail+"' LIMIT 1 ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return false;
			} else {
				return true;
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


	public boolean findByUserNickname(String userNickname) {
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_seq FROM member "
				+ "WHERE user_nickname = '"+userNickname+"' LIMIT 1 ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return false;
			} else {
				return true;
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

	public boolean findByUserId(String userId) {
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_seq FROM member "
				+ "WHERE user_id = '"+userId+"' LIMIT 1 ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				return false;
			} else {
				return true;
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

	public boolean save(UserRequestDto userRequestDto) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "insert into member(user_id, user_password, user_name, user_nickname, user_email, created_at) "
				+ "values('"+ userRequestDto.getUserId() +"', '"
				+ userRequestDto.getUserPassword() +"', '"
				+ userRequestDto.getUserName() +"', '"
				+ userRequestDto.getUserNickname() +"', '"
				+ userRequestDto.getUserEmail() + "', now())";
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

	public boolean findByUserIdAndPassword(String userId, String userPassword) {
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_id FROM member "
				+ "WHERE user_id = '"+userId+"' AND user_password = '"+userPassword+"' LIMIT 1 ";
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

	public UserResponseDto findByUserNameAndUserEmail(String userName, String userEmail) {
		Connection conn = connectSql.getConnection();
		UserResponseDto userResponseDto = new UserResponseDto();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_id, created_at, user_name, user_email FROM member "
				+ "WHERE user_name = '"+userName+"' AND user_email = '"+userEmail+"' LIMIT 1 ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				userResponseDto.setUserId(rs.getString("user_id"));
				userResponseDto.setCreatedAt(rs.getString("created_at"));
				userResponseDto.setUserName(rs.getString("user_name"));
				userResponseDto.setUserEmail(rs.getString("user_email"));
				return userResponseDto;
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
		return null;
	}

	public UserResponseDto findByUserIdAndUserEmail(String userId, String userEmail) {
		Connection conn = connectSql.getConnection();
		UserResponseDto userResponseDto = new UserResponseDto();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT user_id, user_email FROM member "
				+ "WHERE user_id = '"+userId+"' AND user_email = '"+userEmail+"' LIMIT 1 ";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				userResponseDto.setUserId(rs.getString("user_id"));
				userResponseDto.setUserEmail(rs.getString("user_email"));
				return userResponseDto;
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
		return null;
	}

	public boolean updatePassword(String userId, String userPassword) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "UPDATE member" +
				" SET user_password='"+userPassword+"'"+
				" WHERE user_id='"+userId+"'";
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
