package com.redis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisHashDto {

	private String key;
	private String hashKey;
	private String hashVal;
}
