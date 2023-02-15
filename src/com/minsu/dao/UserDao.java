package com.minsu.dao;

import com.minsu.dto.UserRequestDto;
import com.minsu.dto.UserResponseDto;
import com.minsu.util.ConnectSql;

import java.sql.*;

public class UserDao {

    private final ConnectSql connectSql;

    public UserDao() {
        this.connectSql = new ConnectSql();
    }

    public boolean findByEmail(String userEmail) {
        String sql = "SELECT user_id FROM member "
                + "WHERE user_email = ? LIMIT 1 ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userEmail);
            try (ResultSet rs = stmt.executeQuery();) {
                return !rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean findByUserNickname(String userNickname) {
        String sql = "SELECT user_seq FROM member "
                + "WHERE user_nickname = ? LIMIT 1 ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userNickname);
            try (ResultSet rs = stmt.executeQuery();) {
                return !rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean findByUserId(String userId) {
        String sql = "SELECT user_seq FROM member "
                + "WHERE user_id = ? LIMIT 1 ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery();) {
                return !rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean save(UserRequestDto userRequestDto) {
        String sql = "insert into member(user_id, user_password, user_name, user_nickname, user_email, created_at) "
                + "values(?,?,?,?,?, now())";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userRequestDto.getUserId());
            stmt.setString(2, userRequestDto.getUserPassword());
            stmt.setString(3, userRequestDto.getUserName());
            stmt.setString(4, userRequestDto.getUserNickname());
            stmt.setString(5, userRequestDto.getUserEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public UserResponseDto findByUserIdAndPassword(String userId, String userPassword) {
    	UserResponseDto userResponseDto = new UserResponseDto();
        String sql = "SELECT user_seq, user_nickname FROM member "
                + "WHERE user_id = ? AND user_password = ? LIMIT 1 ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userId);
            stmt.setString(2, userPassword);
            try (ResultSet rs = stmt.executeQuery();) {
            	if(rs.next()){
            		userResponseDto.setUserSeq(rs.getInt("user_seq"));
            		userResponseDto.setUserNickname(rs.getString("user_nickname"));
                    return userResponseDto;
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserResponseDto findByUserNameAndUserEmail(String userName, String userEmail) {
        UserResponseDto userResponseDto = new UserResponseDto();
        String sql = "SELECT user_id, created_at, user_name, user_email FROM member "
                + "WHERE user_name = ? AND user_email = ? LIMIT 1 ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);

        ) {
            stmt.setString(1, userName);
            stmt.setString(2, userEmail);
            try (
                    ResultSet rs = stmt.executeQuery();
            ) {
                if (rs.next()) {
                    userResponseDto.setUserId(rs.getString("user_id"));
                    userResponseDto.setCreatedAt(rs.getString("created_at"));
                    userResponseDto.setUserName(rs.getString("user_name"));
                    userResponseDto.setUserEmail(rs.getString("user_email"));
                    return userResponseDto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserResponseDto findByUserIdAndUserEmail(String userId, String userEmail) {
        UserResponseDto userResponseDto = new UserResponseDto();
        String sql = "SELECT user_id, user_email FROM member "
                + "WHERE user_id = ? AND user_email = ? LIMIT 1 ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, userId);
            stmt.setString(2, userEmail);
            try (
                    ResultSet rs = stmt.executeQuery();
            ) {
                if (rs.next()) {
                    userResponseDto.setUserId(rs.getString("user_id"));
                    userResponseDto.setUserEmail(rs.getString("user_email"));
                    return userResponseDto;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updatePassword(String userId, String userPassword) {
        String sql = "UPDATE member SET user_password=? WHERE user_id=?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1,userPassword);
            stmt.setString(2,userId);
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
