package com.minsu.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "insert into board(brd_title, brd_content, created_at, user_seq) "
				+ "values('"+ boardRequestDto.getBrdTitle() +"', '"
							+ boardRequestDto.getBrdContent() + "', now(), "+ userSeq +")";
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

	public List<BoardResponseDto> findAll(int page, int size) {
		List<BoardResponseDto> boardResponseDtos = new ArrayList<BoardResponseDto>();
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.brd_seq, b.brd_title, b.created_at, b.modified_at, b.brd_view_count, b.brd_like_count, m.user_nickname "
				+ "FROM board b "
				+ "INNER JOIN member m "
				+ "ON b.user_seq = m.user_seq "
				+ "ORDER BY b.brd_seq DESC LIMIT "+ size +" OFFSET " + page;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
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
		return boardResponseDtos;
	}

	public List<BoardResponseDto> findAll(int page, int size, String keyword) {
		List<BoardResponseDto> boardResponseDtos = new ArrayList<BoardResponseDto>();
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.brd_seq, b.brd_title, b.created_at, b.modified_at, b.brd_view_count, b.brd_like_count, m.user_nickname "
				+ "FROM board b "
				+ "INNER JOIN member m "
				+ "ON b.user_seq = m.user_seq "
				+ "WHERE brd_title LIKE '%"+keyword+"%' or brd_content LIKE '%"+keyword+"%' "
				+ "ORDER BY b.brd_seq DESC LIMIT "+ size +" OFFSET " + page;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
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
		return boardResponseDtos;
	}

	
	public BoardResponseDto findById(int brdSeq, int userSeq) {
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT b.brd_seq, b.brd_title, b.brd_content,"
				+ " m.user_nickname, b.created_at, b.modified_at, "
				+ "b.brd_view_count, b.brd_like_count, b.brd_cmt_count , m.user_seq, "
				+ "(SELECT l.brd_seq FROM board_like l "
				+ "WHERE l.brd_seq = b.brd_seq AND l.user_seq = "+userSeq+" LIMIT 1 ) as 'is_liked' " +
				"FROM board b " +
				"INNER JOIN  member m " +
				"ON b.user_seq = m.user_seq " +
				"WHERE b.brd_seq = " + brdSeq;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
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
						rs.getInt("is_liked")!=0,
						rs.getInt("user_seq")==userSeq
				);
			} else {
				return null;
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

	public boolean update(int brdSeq, int userSeq, BoardRequestDto boardRequestDto) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "UPDATE board" +
				" SET modified_at = now(),  brd_title='"+boardRequestDto.getBrdTitle()+"', brd_content='"+boardRequestDto.getBrdContent() +
				"' WHERE brd_seq="+brdSeq+" and user_seq="+userSeq;
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
		String sql = "DELETE FROM board" +
				" WHERE brd_seq="+brdSeq+" and user_seq="+userSeq;
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

	public boolean updateLikeCount(int brdSeq, int i) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "UPDATE board" +
				" SET brd_like_count = brd_like_count + "+i+
				" WHERE brd_seq="+brdSeq ;
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


	public boolean updateCommentCount(int brdSeq, int i) {

		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "UPDATE board" +
				" SET brd_cmt_count = brd_cmt_count + "+i+
				" WHERE brd_seq="+brdSeq ;
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
