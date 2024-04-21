package com.redis.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.redis.service.RedisService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers=RedisErrorControllerTest.class)
class RedisErrorControllerTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisErrorControllerTest.class);
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean(name="RedisService")
	private RedisService redisService;
	
	@Test
	public void redis_페이지_404_테스트() {
		int expRes = 404;
		String url = "/test";
		
		try {
			mockMvc.perform(MockMvcRequestBuilders
								.get(url)
								.contentType(MediaType.TEXT_HTML))
			.andExpect(result -> {
				MockHttpServletResponse response = result.getResponse();
				
				assertEquals(expRes, response.getStatus());
			});
		} catch (Exception e) {
			LOGGER.debug(e.toString());
		}
	}
}
