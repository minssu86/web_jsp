package com.minsu.dto;

import java.time.LocalDateTime;

public class CommentRequestDto {
	private int cmtSeq;
	private int brdSeq;
	private int cmtParentSeq;
	private String content;
	
	public int getCmtSeq() {
		return cmtSeq;
	}
	public void setCmtSeq(int cmtSeq) {
		this.cmtSeq = cmtSeq;
	}
	public int getBrdSeq() {
		return brdSeq;
	}
	public void setBrdSeq(int brdSeq) {
		this.brdSeq = brdSeq;
	}
	public int getCmtParentSeq() {
		return cmtParentSeq;
	}
	public void setCmtParentSeq(int cmtParentSeq) {
		this.cmtParentSeq = cmtParentSeq;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
