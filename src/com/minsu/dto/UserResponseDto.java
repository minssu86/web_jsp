package com.minsu.dto;

public class UserResponseDto {
	
	private int userSeq;
	private String userId;
	private String userPassword;
	private String userName;
	private String userNickname;
	private String userEmail;
	private String createdAt;
	private String userProfile;
	
	
	public int getUserSeq() {
		return userSeq;
	}
	public void setUserSeq(int userSeq) {
		this.userSeq = userSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}
	@Override
	public String toString() {
		return "{\"userSeq\":" + userSeq + ", \"userId\":\"" + userId + "\", \"userPassword\":\"" + userPassword
				+ "\", \"userName\":\"" + userName + "\", \"userNickname\":\"" + userNickname + "\", \"userEmail\":\"" + userEmail
				+ "\", \"createdAt\":\"" + createdAt + "\", \"userProfile\":\"" + userProfile + "\"}";
	}
	
	
	
}
