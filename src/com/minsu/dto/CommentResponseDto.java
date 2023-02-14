package com.minsu.dto;

public class CommentResponseDto {
	private int cmtSeq;
	private String cmtContent;
	private String date;
	private int cmtParentSeq;
	private int cmtLikeCount;
	private boolean cmtIsDeleted;
	private boolean cmtIsModify;
	private int brdSeq;
	private String userNickname;
	private boolean isLiked;
	private boolean isMine;
	
	public CommentResponseDto(int cmtSeq, String cmtContent, String createdAt, String modifiedAt, 
								int cmtParentSeq, int cmtLikeCount, boolean cmtIsDeleted, 
								int brdSeq, String userNickname, boolean isLiked, boolean isMine) {
		this.cmtSeq = cmtSeq;
		this.cmtContent = cmtContent;
		this.date = modifiedAt==null?createdAt:modifiedAt;
		this.cmtParentSeq = cmtParentSeq;
		this.cmtLikeCount = cmtLikeCount;
		this.cmtIsDeleted = cmtIsDeleted;
		this.cmtIsModify = modifiedAt!=null;
		this.brdSeq = brdSeq;
		this.userNickname = userNickname;
		this.isLiked = isLiked;
		this.isMine = isMine;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isCmtIsModify() {
		return cmtIsModify;
	}

	public void setCmtIsModify(boolean cmtIsModify) {
		this.cmtIsModify = cmtIsModify;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public int getCmtSeq() {
		return cmtSeq;
	}
	public void setCmtSeq(int cmtSeq) {
		this.cmtSeq = cmtSeq;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public int getCmtParentSeq() {
		return cmtParentSeq;
	}
	public void setCmtParentSeq(int cmtParentSeq) {
		this.cmtParentSeq = cmtParentSeq;
	}
	public int getCmtLikeCount() {
		return cmtLikeCount;
	}
	public void setCmtLikeCount(int cmtLikeCount) {
		this.cmtLikeCount = cmtLikeCount;
	}
	public boolean isCmtIsDeleted() {
		return cmtIsDeleted;
	}
	public void setCmtIsDeleted(boolean cmtIsDeleted) {
		this.cmtIsDeleted = cmtIsDeleted;
	}
	public int getBrdSeq() {
		return brdSeq;
	}
	public void setBrdSeq(int brdSeq) {
		this.brdSeq = brdSeq;
	}
}
