package com.redis.config;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class RedisConfigTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void redis_접속_테스트() {
		String key = "testKey";
		String val = "testVal";
		
		redisTemplate.opsForValue().set(key, val, 60, TimeUnit.SECONDS);
		
		String redisVal = redisTemplate.opsForValue().get(key).toString();
		assertEquals(val, redisVal);
	}
}
