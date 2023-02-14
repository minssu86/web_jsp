package com.minsu.dao;

import com.minsu.util.ConnectSql;

import java.sql.*;

public class CommentLikeDao {

    private final ConnectSql connectSql;

    public CommentLikeDao() {
        this.connectSql = new ConnectSql();
    }

    public boolean find(int cmtSeq, int userSeq) {
        String sql = "SELECT cmt_seq FROM comment_like "
                + "WHERE cmt_seq = ? AND user_seq = ? LIMIT 1 ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, cmtSeq);
            stmt.setInt(1, userSeq);
            try (ResultSet rs = stmt.executeQuery();) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean save(int cmtSeq, int userSeq) {
        String sql = "INSERT INTO comment_like(cmt_seq, user_seq) "
                + "VALUES(?, ?) ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, cmtSeq);
            stmt.setInt(2, userSeq);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int cmtSeq, int userSeq) {
        String sql = "DELETE FROM comment_like " +
                "WHERE cmt_seq=? and user_seq=?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, cmtSeq);
            stmt.setInt(2, userSeq);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteAll(String cmtArrStr) {
        String sql = "DELETE FROM comment_like" +
                " WHERE cmt_seq in(?)";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, cmtArrStr);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
