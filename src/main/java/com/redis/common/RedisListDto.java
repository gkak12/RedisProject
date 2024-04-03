package com.redis.common;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RedisListDto {

	private String key;
	private List<Object> list;
}
