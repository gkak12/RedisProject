package com.redis.service;

import java.util.List;

import com.redis.common.RedisHashDto;
import com.redis.common.RedisListDto;
import com.redis.common.RedisStringDto;

public interface RedisService {
	
	/**
	 * 문자열 저장
	 * @param redisStringDto
	 * @throws Exception
	 */
	public void setString(RedisStringDto redisStringDto) throws Exception;
	
	/**
	 * 문자열 조회
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public String getString(String key) throws Exception;
	
	/**
	 * 리스트 저장
	 * @param redisListDto
	 * @throws Exception
	 */
	public void setList(RedisListDto redisListDto) throws Exception;
	
	/**
	 * 리스트 조회
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<Object> getList(String key) throws Exception;
	
	/**
	 * 해시 저장
	 * @param redisHashDto
	 * @throws Exception
	 */
	public void setHash(RedisHashDto redisHashDto) throws Exception;
	
	/**
	 * 해시 조회
	 * @param redisHashDto
	 * @return
	 * @throws Exception
	 */
	public String getHash(RedisHashDto redisHashDto) throws Exception;
}
