package com.minsu.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.minsu.dto.CommentRequestDto;
import com.minsu.dto.CommentResponseDto;
import com.minsu.util.ConnectSql;

public class CommentDao {

	private final ConnectSql connectSql;
	
	public CommentDao() {
		this.connectSql = new ConnectSql();
	}

	public boolean save(int userSeq, CommentRequestDto commentRequestDto) {
		String cmtParentSeq = "null";
		if(commentRequestDto.getCmtParentSeq()!=0)cmtParentSeq = commentRequestDto.getCmtParentSeq()+ "";
		String sql = "insert into comment(cmt_content, created_at, cmt_parent_seq, cmt_is_deleted, brd_seq, user_seq) "
				+ "values(?, now(), "
							+ cmtParentSeq + ", " 
						+ "false,?, ?)";
		try (
				Connection conn = connectSql.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, commentRequestDto.getContent());
			stmt.setInt(2, 0);
			stmt.setInt(3, commentRequestDto.getBrdSeq());
			stmt.setInt(4, userSeq);
			return stmt.executeUpdate()>0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public List<CommentResponseDto> findAll(int page, int size, int brdSeq, int userSeq) {
		List<CommentResponseDto> commentResponseDtos = new ArrayList<CommentResponseDto>();
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT c.cmt_seq, c.cmt_content, c.created_at, c.modified_at, c.cmt_parent_seq, c.cmt_like_count,"
				+ "c.cmt_is_deleted, c.brd_seq, m.user_seq, m.user_nickname, "
								+ "(SELECT l.cmt_seq FROM comment_like l "
								+ "WHERE l.cmt_seq = c.cmt_seq AND l.user_seq = "+userSeq+" LIMIT 1 ) as is_liked "
				+ "FROM comment c "
				+ "INNER JOIN member m "
				+ "ON c.user_seq = m.user_seq "
				+ "WHERE c.brd_seq = " + brdSeq + " "
				+ "ORDER BY c.cmt_seq ASC LIMIT "+ size +" OFFSET " + page;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				CommentResponseDto commentResponseDto = new CommentResponseDto(
							rs.getInt("cmt_seq"), 
							rs.getString("cmt_content"),
							rs.getString("created_at"), 
							rs.getString("modified_at"), 
							rs.getInt("cmt_parent_seq"), 
							rs.getInt("cmt_like_count"), 
							rs.getBoolean("cmt_is_deleted"),
							rs.getInt("brd_seq"),
							rs.getString("user_nickname"),
							rs.getBoolean("is_liked"),
							rs.getInt("user_seq")==userSeq
						);
				commentResponseDtos.add(commentResponseDto);
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
		return commentResponseDtos;
		
	}

	public boolean update(int userSeq, CommentRequestDto commentRequestDto) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "UPDATE comment" +
				" SET cmt_content='"+commentRequestDto.getContent()+"', modified_at= now() "+
				" WHERE cmt_seq="+commentRequestDto.getCmtSeq()+" and user_seq="+userSeq;
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

	public boolean delete(int cmdSeq, int userSeq) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "UPDATE comment" +
				" SET cmt_is_deleted= true" +
				" WHERE cmt_seq="+cmdSeq+" and user_seq="+userSeq + " and cmt_is_deleted = false ";
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

	public boolean updateLikeCount(int cmtSeq, int i) {
		int resultCount = 0;
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "UPDATE comment" +
				" SET cmt_like_count = cmt_like_count + "+i+
				" WHERE cmt_seq ="+cmtSeq ;
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

	public void deleteAll(int brdSeq) {
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		String sql = "DELETE FROM comment" +
				" WHERE brd_seq="+brdSeq;
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

	public String findAllCmt(int brdSeq) {
		String result = "";
		Connection conn = connectSql.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT cmt_seq FROM comment WHERE brd_seq = "+brdSeq;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				result += rs.getString("cmt_seq") + ",";
			}
			return result.substring(0,result.length()-1);
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
}
