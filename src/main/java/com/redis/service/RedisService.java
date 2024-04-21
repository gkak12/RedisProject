package com.redis.service;

import java.util.List;

import com.redis.dto.RedisHashDto;
import com.redis.dto.RedisListDto;
import com.redis.dto.RedisStringDto;

public interface RedisService {
	
	/**
	 * 문자열 저장
	 * @param redisStringDto
	 */
	public void setString(RedisStringDto redisStringDto);
	
	/**
	 * 문자열 조회
	 * @param key
	 * @return
	 */
	public String getString(String key);
	
	/**
	 * 리스트 저장
	 * @param redisListDto
	 */
	public void setList(RedisListDto redisListDto);
	
	/**
	 * 리스트 조회
	 * @param key
	 * @return
	 */
	public List<Object> getList(String key);
	
	/**
	 * 해시 저장
	 * @param redisHashDto
	 */
	public void setHash(RedisHashDto redisHashDto);
	
	/**
	 * 해시 조회
	 * @param redisHashDto
	 * @return
	 */
	public String getHash(RedisHashDto redisHashDto);
}
