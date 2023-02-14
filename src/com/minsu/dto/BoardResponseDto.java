package com.minsu.dto;

public class BoardResponseDto {
	private int brdSeq;
	private String brdTitle;
	private String brdContent;
	private String date;
	private String userNickname;
	private int viewCount;
	private int likeCount;
	private int commentCount;
	private boolean isLiked;
	private boolean isMine;
	
	public BoardResponseDto(int brdSeq, String brdTitle, String userNickname, 
			String createdAt, String modifiedAt, int brdViewCount, int brdLikeCount) {
		this.brdSeq = brdSeq;
		this.brdTitle = brdTitle;
		this.userNickname = userNickname;
		this.date = modifiedAt!=null? modifiedAt : createdAt;
		this.viewCount = brdViewCount;
		this.likeCount = brdLikeCount;
	}

	public BoardResponseDto(int brdSeq, String brdTitle, String brdContent, String userNickname, String createdAt, String modifiedAt, int brdViewCount, int brdLikeCount, int commentCount, boolean isLiked, boolean isMine){
		this.brdSeq = brdSeq;
		this.brdTitle = brdTitle;
		this.brdContent = brdContent;
		this.userNickname = userNickname;
		this.date = modifiedAt!=null? modifiedAt : createdAt;
		this.viewCount = brdViewCount;
		this.likeCount = brdLikeCount;
		this.commentCount = commentCount;
		this.isLiked = isLiked;
		this.isMine = isMine;
	}
	
	public int getBrdSeq() {
		return brdSeq;
	}

	public void setBrdSeq(int brdSeq) {
		this.brdSeq = brdSeq;
	}

	public String getBrdTitle() {
		return brdTitle;
	}

	public void setBrdTitle(String brdTitle) {
		this.brdTitle = brdTitle;
	}

	public String getBrdContent() {
		return brdContent;
	}

	public void setBrdContent(String brdContent) {
		this.brdContent = brdContent;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public boolean isLiked() {
		return isLiked;
	}

	public void setLiked(boolean isLiked) {
		this.isLiked = isLiked;
	}

	public boolean isMine() {
		return isMine;
	}

	public void setMine(boolean isMine) {
		this.isMine = isMine;
	}

	
}


