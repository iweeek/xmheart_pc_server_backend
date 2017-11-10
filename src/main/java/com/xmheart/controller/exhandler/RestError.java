package com.xmheart.controller.exhandler;

public class RestError {

    /**
     * 对应响应头中的HTTP状态码
     */
    private int status;
    /**
     * 提供的特定 REST API 的异常编码
     */
    private int code;
    /**
     * 可读的异常消息，可直接展示给应用程序端的用户
     */
    private String message;
    /**
     * 提供给开发者的异常信息
     */
    private String[] developerMessages;
    
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getDeveloperMessages() {
		return developerMessages;
	}
	public void setDeveloperMessages(String[] developerMessages) {
		this.developerMessages = developerMessages;
	}
	
}
