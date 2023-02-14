package com.minsu.dto;

public class ResponseDto<T> {
	private ResponseStatus status;
	private T data;
	
	public ResponseDto(){
		
	}
	public ResponseDto(ResponseStatus status, T data){
		this.status = status;
		this.data = data;
	}
	public ResponseDto(ResponseStatus status){
		this.status = status;
		this.data = null;
	}
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
