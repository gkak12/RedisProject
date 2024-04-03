package com.redis.common;

public enum RedisAttr {

	STRING("STRING", "string", "String"),
	LIST("LIST", "list", "List"),
	RESULT("RESULT", "result", "Result"),
	SUCCESS("SUCCESS", "success", "Success"),
	FAIL("FAIL", "fail", "Fail"),
	MSG("MSG", "msg", "Msg");
	
	private final String upperCase;
	private final String lowerCase;
	private final String normalCase;
	
	RedisAttr(String upperCase, String lowerCase, String normalCase) {
		this.upperCase = upperCase;
		this.lowerCase = lowerCase;
		this.normalCase = normalCase;
	}
	
	public String upperCase() {
		return this.upperCase;
	}
	
	public String lowerCase() {
		return this.lowerCase;
	}
	
	public String normalCase() {
		return this.normalCase;
	}
}
