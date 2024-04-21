package com.redis.service.imp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.redis.dto.RedisHashDto;
import com.redis.dto.RedisListDto;
import com.redis.dto.RedisStringDto;
import com.redis.service.RedisService;

@Service("RedisService")
public class RedisServiceImp implements RedisService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImp.class);

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisServiceImp(RedisTemplate<String, Object> redisTemplate) {
    	this.redisTemplate = redisTemplate;
    }
   
    @Override
    public void setString(RedisStringDto redisStringDto) {
    	LOGGER.debug(redisStringDto.toString());
    	redisTemplate.opsForValue().set(redisStringDto.getKey(), redisStringDto.getVal(), 3600, TimeUnit.SECONDS);
    }

    @Override
    public String getString(String key) {
    	LOGGER.debug(key);
    	return redisTemplate.opsForValue().get(key).toString();
    }

	@Override
	public void setList(RedisListDto redisListDto) {
		LOGGER.debug(redisListDto.toString());
		
		redisTemplate.opsForList().rightPushAll(redisListDto.getKey(), redisListDto.getList());
		redisTemplate.expire(redisListDto.getKey(), 3600, TimeUnit.SECONDS);
	}

	@Override
	public List<Object> getList(String key) {
		LOGGER.debug(key);
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	@Override
	public void setHash(RedisHashDto redisHashDto) {
		LOGGER.debug(redisHashDto.toString());
		redisTemplate.opsForHash().put(redisHashDto.getKey(), redisHashDto.getHashKey(), redisHashDto.getHashVal());
		redisTemplate.expire(redisHashDto.getKey(), 3600, TimeUnit.SECONDS);
	}

	@Override
	public String getHash(RedisHashDto redisHashDto) {
		LOGGER.debug(redisHashDto.toString());
		return redisTemplate.opsForHash().get(redisHashDto.getKey(), redisHashDto.getHashKey()).toString();
	}
}
