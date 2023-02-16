package com.minsu.dto;

public class BoardRequestDto {
	private int brdSeq;
	private String brdTitle;
	private String brdContent;
	private int page;
	private int size;
	
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
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public String toString() {
		return "BoardRequestDto [brdSeq=" + brdSeq + ", brdTitle=" + brdTitle + ", brdContent=" + brdContent + ", page="
				+ page + ", size=" + size + "]";
	}
	
	
	
	
}
