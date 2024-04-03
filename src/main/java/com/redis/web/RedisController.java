package com.redis.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.common.RedisAttr;
import com.redis.common.RedisHashDto;
import com.redis.common.RedisListDto;
import com.redis.common.RedisStringDto;
import com.redis.service.RedisService;

@RestController
public class RedisController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);
	
	@Resource(name = "RedisServiceImp")
	private RedisService redisService;
	
	@PostMapping(value = "/setString")
	public Map<RedisAttr, String> setString(@RequestBody RedisStringDto redisStringDto){
		LOGGER.debug(redisStringDto.toString());
		Map<RedisAttr, String> res = new HashMap<RedisAttr, String>();
		
		try {
			redisService.setString(redisStringDto);
			res.put(RedisAttr.RESULT, RedisAttr.SUCCESS.normalCase());
		} catch (Exception e) {
			LOGGER.debug(e.toString());
			res.put(RedisAttr.MSG, e.toString());
			res.put(RedisAttr.RESULT, RedisAttr.FAIL.normalCase());
		}
		
		return res;
	}
	
	@GetMapping(value = "/getString")
	public Map<RedisAttr, String> getString(@RequestParam(value = "key", required = true) String key){
		LOGGER.debug(key);
		Map<RedisAttr, String> res = new HashMap<RedisAttr, String>();
		
		try {
			String val = redisService.getString(key);
			res.put(RedisAttr.STRING, val);
			res.put(RedisAttr.RESULT, RedisAttr.SUCCESS.normalCase());
		} catch (Exception e) {
			LOGGER.debug(e.toString());
			res.put(RedisAttr.MSG, e.toString());
			res.put(RedisAttr.RESULT, RedisAttr.FAIL.normalCase());
		}
		
		return res;
	}
	
	@PostMapping(value = "/setList")
	public Map<RedisAttr, String> setList(@RequestBody RedisListDto redisListDto){
		LOGGER.debug(redisListDto.toString());
		Map<RedisAttr, String> res = new HashMap<RedisAttr, String>();
		
		try {
			redisService.setList(redisListDto);
			res.put(RedisAttr.RESULT, RedisAttr.SUCCESS.normalCase());
		} catch (Exception e) {
			LOGGER.debug(e.toString());
			res.put(RedisAttr.MSG, e.toString());
			res.put(RedisAttr.RESULT, RedisAttr.FAIL.normalCase());
		}
		
		return res;
	}
	
	@GetMapping(value = "/getList")
	public Map<RedisAttr, Object> getList(@RequestParam(value="key", required = true) String key){
		LOGGER.debug(key);
		Map<RedisAttr, Object> res = new HashMap<RedisAttr, Object>();
		
		try {
			List<Object> list = redisService.getList(key);
			res.put(RedisAttr.LIST, list);
			res.put(RedisAttr.RESULT, RedisAttr.SUCCESS.normalCase());
		} catch (Exception e) {
			LOGGER.debug(e.toString());
			res.put(RedisAttr.MSG, e.toString());
			res.put(RedisAttr.RESULT, RedisAttr.FAIL.normalCase());
		}
		
		return res;
	}
	
	@PostMapping(value = "/setHash")
	public Map<RedisAttr, String> setHash(@RequestBody RedisHashDto redisHashDto){
		LOGGER.debug(redisHashDto.toString());
		Map<RedisAttr, String> res = new HashMap<RedisAttr, String>();
		
		try {
			redisService.setHash(redisHashDto);
			res.put(RedisAttr.RESULT, RedisAttr.SUCCESS.normalCase());
		} catch (Exception e) {
			LOGGER.debug(e.toString());
			res.put(RedisAttr.MSG, e.toString());
			res.put(RedisAttr.RESULT, RedisAttr.FAIL.normalCase());
		}
		
		return res;
	}
	
	@GetMapping(value = "/getHash")
	public Map<RedisAttr, String> getHash(@RequestBody RedisHashDto redisHashDto){
		LOGGER.debug(redisHashDto.toString());
		Map<RedisAttr, String> res = new HashMap<RedisAttr, String>();
		
		try {
			String val = redisService.getHash(redisHashDto);
			res.put(RedisAttr.STRING, val);
			res.put(RedisAttr.RESULT, RedisAttr.SUCCESS.normalCase());
		} catch (Exception e) {
			LOGGER.debug(e.toString());
			res.put(RedisAttr.MSG, e.toString());
			res.put(RedisAttr.RESULT, RedisAttr.FAIL.normalCase());
		}
		
		return res;
	}
}
