package com.minsu.dao;

import java.sql.*;

import com.minsu.util.ConnectSql;

public class BoardLikeDao {

    private final ConnectSql connectSql;

    public BoardLikeDao() {
        this.connectSql = new ConnectSql();
    }

    public boolean find(int brdSeq, int userSeq) {
        String sql = "SELECT brd_seq FROM board_like "
                + "WHERE brd_seq = ? AND user_seq = ? LIMIT 1 ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, brdSeq);
            stmt.setInt(2, userSeq);
            try (
                    ResultSet rs = stmt.executeQuery();
            ) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean save(int brdSeq, int userSeq) {
        int resultCount = 0;
        String sql = "INSERT INTO board_like(brd_seq, user_seq) "
                + "VALUES(" + brdSeq + ", " + userSeq + ") ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, brdSeq);
            stmt.setInt(2, userSeq);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int brdSeq, int userSeq) {
        String sql = "DELETE FROM board_like " +
                "WHERE brd_seq=? and user_seq=?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, brdSeq);
            stmt.setInt(2, userSeq);
            return stmt.executeUpdate()>0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
