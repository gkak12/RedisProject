package com.redis.common;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RedisControllerAdvice {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisControllerAdvice.class);

	@SuppressWarnings("unchecked")
	@ExceptionHandler(Exception.class)
	public ResponseEntity<JSONObject> handleException(Exception e) {
		LOGGER.debug(e.toString());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(RedisAttr.RESULT.normalCase(), RedisAttr.FAIL.normalCase());
		jsonObject.put(RedisAttr.MSG.normalCase(), e.toString());
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(jsonObject);
    }
	
	@SuppressWarnings("unchecked")
	@ExceptionHandler(RedisConnectionFailureException.class)
	public ResponseEntity<JSONObject> handleRedisConnectionException(RedisConnectionFailureException e){
		LOGGER.debug(e.toString());
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(RedisAttr.RESULT.normalCase(), RedisAttr.FAIL.normalCase());
		jsonObject.put(RedisAttr.MSG.normalCase(), e.toString());
		
		return ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(jsonObject);
	}
}

