package com.minsu.dao;

import com.minsu.util.ConnectSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommentLikeDao {

    private final ConnectSql connectSql;

    public CommentLikeDao() {
        this.connectSql = new ConnectSql();
    }

    public boolean find(int cmtSeq, int userSeq) {

        Connection conn = connectSql.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        String sql = "SELECT cmt_seq FROM comment_like "
                + "WHERE cmt_seq = "+cmtSeq+" AND user_seq = "+userSeq+" LIMIT 1 ";
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

    public boolean save(int cmtSeq, int userSeq) {
        int resultCount = 0;
        Connection conn = connectSql.getConnection();
        Statement stmt = null;
        String sql = "INSERT INTO comment_like(cmt_seq, user_seq) "
                + "VALUES("+cmtSeq+", "+userSeq+") ";
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

    public boolean delete(int cmtSeq, int userSeq) {
        int resultCount = 0;
        Connection conn = connectSql.getConnection();
        Statement stmt = null;
        String sql = "DELETE FROM comment_like " +
                "WHERE cmt_seq="+cmtSeq+" and user_seq="+userSeq;
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

    public void deleteAll(String cmtArrStr) {
        Connection conn = connectSql.getConnection();
        Statement stmt = null;
        String sql = "DELETE FROM comment_like" +
                " WHERE cmt_seq in("+cmtArrStr+")";
        System.out.println(sql);
        try {
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
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
    }
}
