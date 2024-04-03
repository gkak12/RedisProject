package com.redis.common;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisStringDto {

	private String key;
	private String val;
}
