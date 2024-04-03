package com.redis.service.imp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.redis.common.RedisHashDto;
import com.redis.common.RedisListDto;
import com.redis.common.RedisStringDto;
import com.redis.service.RedisService;

@Service("RedisServiceImp")
public class RedisServiceImp implements RedisService{

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Override
	public void setString(RedisStringDto redisStringDto) throws Exception {
		redisTemplate.opsForValue().set(redisStringDto.getKey(), redisStringDto.getVal(), 3600, TimeUnit.SECONDS);
	}

	@Override
	public String getString(String key) throws Exception {
		return redisTemplate.opsForValue().get(key).toString();
	}

	@Override
	public void setList(RedisListDto redisListDto) throws Exception {
		redisTemplate.opsForList().rightPushAll(redisListDto.getKey(), redisListDto.getList());
		redisTemplate.expire(redisListDto.getKey(), 3600, TimeUnit.SECONDS);
	}

	@Override
	public List<Object> getList(String key) throws Exception {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	@Override
	public void setHash(RedisHashDto redisHashDto) throws Exception {
		redisTemplate.opsForHash().put(redisHashDto.getKey(), redisHashDto.getHashKey(), redisHashDto.getHashVal());
		redisTemplate.expire(redisHashDto.getKey(), 3600, TimeUnit.SECONDS);
	}

	@Override
	public String getHash(RedisHashDto redisHashDto) throws Exception {
		return redisTemplate.opsForHash().get(redisHashDto.getKey(), redisHashDto.getHashKey()).toString();
	}
}
