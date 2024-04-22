package com.redis.web;

import java.util.List;

import javax.annotation.Resource;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.common.RedisAttr;
import com.redis.dto.RedisHashDto;
import com.redis.dto.RedisListDto;
import com.redis.dto.RedisStringDto;
import com.redis.service.RedisService;

@RestController
public class RedisController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);
	
	@Resource(name = "RedisService")
	private RedisService redisService;
	
	@PostMapping(value = "/setString")
	public ResponseEntity<Void> setString(@RequestBody RedisStringDto redisStringDto) {
		LOGGER.debug(redisStringDto.toString());
		
		redisService.setString(redisStringDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getString")
	public ResponseEntity<String> getString(@RequestParam(value = "key", required = true) String key){
		LOGGER.debug(key);
		
		String val = redisService.getString(key);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(RedisAttr.RESULT.normalCase(), RedisAttr.SUCCESS.normalCase());
		jsonObject.put(RedisAttr.STRING.normalCase(), val);
		
		return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
	}
	
	@PostMapping(value = "/setList")
	public ResponseEntity<Void> setList(@RequestBody RedisListDto redisListDto){
		LOGGER.debug(redisListDto.toString());
		
		redisService.setList(redisListDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getList")
	public ResponseEntity<String> getList(@RequestParam(value="key", required = true) String key){
		LOGGER.debug(key);
		
		List<Object> list = redisService.getList(key);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(RedisAttr.RESULT.normalCase(), RedisAttr.SUCCESS.normalCase());
		jsonObject.put(RedisAttr.LIST.normalCase(), list);
		
		return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
	}
	
	@PostMapping(value = "/setHash")
	public ResponseEntity<Void> setHash(@RequestBody RedisHashDto redisHashDto){
		LOGGER.debug(redisHashDto.toString());
		
		redisService.setHash(redisHashDto);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping(value = "/getHash")
	public ResponseEntity<String> getHash(@RequestBody RedisHashDto redisHashDto){
		LOGGER.debug(redisHashDto.toString());
		
		String val = redisService.getHash(redisHashDto);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(RedisAttr.RESULT.normalCase(), RedisAttr.SUCCESS.normalCase());
		jsonObject.put(RedisAttr.STRING.normalCase(), val);
		
		return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
	}
}
