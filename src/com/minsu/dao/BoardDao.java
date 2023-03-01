package com.minsu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.minsu.dto.BoardRequestDto;
import com.minsu.dto.BoardResponseDto;
import com.minsu.util.ConnectSql;

public class BoardDao {

    private final ConnectSql connectSql;

    public BoardDao() {
        this.connectSql = new ConnectSql();
    }

    public boolean save(BoardRequestDto boardRequestDto, int userSeq) {
        String sql = "insert into board(brd_title, brd_content, created_at, user_seq) "
                + "values(?,?,now(),?)";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, boardRequestDto.getBrdTitle());
            stmt.setString(2, boardRequestDto.getBrdContent());
            stmt.setInt(3, userSeq);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<BoardResponseDto> findAll(int page, int size) {
        List<BoardResponseDto> boardResponseDtos = new ArrayList<BoardResponseDto>();
        page = size * page;
        String sql = "SELECT b.brd_seq, b.brd_title, b.created_at, b.modified_at, b.brd_view_count, b.brd_like_count, m.user_nickname "
                + "FROM board b "
                + "INNER JOIN member m "
                + "ON b.user_seq = m.user_seq "
                + "ORDER BY b.brd_seq DESC LIMIT ? OFFSET ?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, size);
            stmt.setInt(2, page);
            try (
            		ResultSet rs = stmt.executeQuery();
            ) {
                while (rs.next()) {
                    BoardResponseDto boardResponseDto = new BoardResponseDto(
                            rs.getInt("brd_seq"),
                            rs.getString("brd_title"),
                            rs.getString("user_nickname"),
                            rs.getString("created_at"),
                            rs.getString("modified_at"),
                            rs.getInt("brd_view_count"),
                            rs.getInt("brd_like_count")
                    );
                    boardResponseDtos.add(boardResponseDto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardResponseDtos;
    }

    public List<BoardResponseDto> findAll(int page, int size, String keyword) {
    	System.out.println("오나??");
        List<BoardResponseDto> boardResponseDtos = new ArrayList<BoardResponseDto>();
        String sql = "SELECT b.brd_seq, b.brd_title, b.created_at, b.modified_at, b.brd_view_count, b.brd_like_count, m.user_nickname "
                + "FROM board b "
                + "INNER JOIN member m "
                + "ON b.user_seq = m.user_seq "
                + "WHERE brd_title LIKE ? or brd_content LIKE ? "
                + "ORDER BY b.brd_seq DESC LIMIT ? OFFSET ?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, "%"+keyword+"%");
            stmt.setString(2, "%"+keyword+"%");
            stmt.setInt(3, size);
            stmt.setInt(4, page);
            try (
                    ResultSet rs = stmt.executeQuery();
            ) {
                while (rs.next()) {
                    BoardResponseDto boardResponseDto = new BoardResponseDto(
                            rs.getInt("brd_seq"),
                            rs.getString("brd_title"),
                            rs.getString("user_nickname"),
                            rs.getString("created_at"),
                            rs.getString("modified_at"),
                            rs.getInt("brd_view_count"),
                            rs.getInt("brd_like_count")
                    );
                    boardResponseDtos.add(boardResponseDto);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardResponseDtos;
    }


    public BoardResponseDto findById(int brdSeq, int userSeq) {
        String sql = "SELECT b.brd_seq, b.brd_title, b.brd_content,"
                + " m.user_nickname, b.created_at, b.modified_at, "
                + "b.brd_view_count, b.brd_like_count, b.brd_cmt_count , m.user_seq, "
                + "(SELECT l.brd_seq FROM board_like l "
                + "WHERE l.brd_seq = b.brd_seq AND l.user_seq = ? LIMIT 1 ) as 'is_liked' " +
                "FROM board b " +
                "LEFT JOIN member m " +
                "ON b.user_seq = m.user_seq " +
                "WHERE b.brd_seq = ?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, userSeq);
            stmt.setInt(2, brdSeq);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return new BoardResponseDto(
                            rs.getInt("brd_seq"),
                            rs.getString("brd_title"),
                            rs.getString("brd_content"),
                            rs.getString("user_nickname"),
                            rs.getString("created_at"),
                            rs.getString("modified_at"),
                            rs.getInt("brd_view_count"),
                            rs.getInt("brd_like_count"),
                            rs.getInt("brd_cmt_count"),
                            rs.getInt("is_liked") != 0,
                            rs.getInt("user_seq") == userSeq
                    );
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(BoardRequestDto boardRequestDto, int userSeq) {
        String sql = "UPDATE board" +
                " SET modified_at = now(),  brd_title=? , brd_content=? " +
                " WHERE brd_seq=? and user_seq=? ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, boardRequestDto.getBrdTitle());
            stmt.setString(2, boardRequestDto.getBrdContent());
            stmt.setInt(3, boardRequestDto.getBrdSeq());
            stmt.setInt(4, userSeq);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int brdSeq, int userSeq) {
        String sql = "DELETE FROM board" +
                " WHERE brd_seq=? and user_seq=?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
        	System.out.println(brdSeq + ":" + userSeq);
            stmt.setInt(1, brdSeq);
            stmt.setInt(2, userSeq);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateLikeCount(int brdSeq, int count) {
        String sql = "UPDATE board" +
                " SET brd_like_count = (brd_like_count+?) " +
                "WHERE brd_seq=?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, count);
            stmt.setInt(2, brdSeq);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateCommentCount(int brdSeq, int count) {
        String sql = "UPDATE board" +
                " SET brd_cmt_count = (brd_cmt_count+?)" +
                " WHERE brd_seq=?";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setInt(1, count);
            stmt.setInt(2, brdSeq);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public int countBoard() {
        String sql = "select count(brd_seq) from board ";
        try (
                Connection conn = connectSql.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
        		ResultSet rs = stmt.executeQuery();
        ) {
        	if(rs.next())return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return 0;
	}
}
