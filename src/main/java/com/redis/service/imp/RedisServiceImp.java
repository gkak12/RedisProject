package com.redis.service.imp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.redis.common.RedisHashDto;
import com.redis.common.RedisListDto;
import com.redis.common.RedisStringDto;
import com.redis.service.RedisService;

@Service("RedisService")
public class RedisServiceImp implements RedisService{

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImp.class);
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void setString(RedisStringDto redisStringDto) throws Exception {
		LOGGER.debug(redisStringDto.toString());
		redisTemplate.opsForValue().set(redisStringDto.getKey(), redisStringDto.getVal(), 3600, TimeUnit.SECONDS);
	}

	@Override
	public String getString(String key) throws Exception {
		LOGGER.debug(key);
		return redisTemplate.opsForValue().get(key).toString();
	}

	@Override
	public void setList(RedisListDto redisListDto) throws Exception {
		LOGGER.debug(redisListDto.toString());
		redisTemplate.opsForList().rightPushAll(redisListDto.getKey(), redisListDto.getList());
		redisTemplate.expire(redisListDto.getKey(), 3600, TimeUnit.SECONDS);
	}

	@Override
	public List<Object> getList(String key) throws Exception {
		LOGGER.debug(key);
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	@Override
	public void setHash(RedisHashDto redisHashDto) throws Exception {
		LOGGER.debug(redisHashDto.toString());
		redisTemplate.opsForHash().put(redisHashDto.getKey(), redisHashDto.getHashKey(), redisHashDto.getHashVal());
		redisTemplate.expire(redisHashDto.getKey(), 3600, TimeUnit.SECONDS);
	}

	@Override
	public String getHash(RedisHashDto redisHashDto) throws Exception {
		LOGGER.debug(redisHashDto.toString());
		return redisTemplate.opsForHash().get(redisHashDto.getKey(), redisHashDto.getHashKey()).toString();
	}
}
