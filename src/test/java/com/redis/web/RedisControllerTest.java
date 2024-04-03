package com.redis.web;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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

import com.redis.common.RedisAttr;
import com.redis.service.RedisService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers=RedisController.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RedisControllerTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisControllerTest.class);

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean(name="RedisServiceImp")
	private RedisService redisService;
	
	@Test
	@Order(1)
	public void redis_문자열_저장_테스트() {
		String url = "/setString";
		String redisString = "{\"key\": \"testString\", \"val\":\"redis test string value\"}";
		
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.post(url)
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(redisString))
					.andExpect(result -> {
						MockHttpServletResponse response = result.getResponse();
						String res = response.getContentAsString();
						
						LOGGER.debug(res);
						assertTrue(res.contains(RedisAttr.SUCCESS.normalCase()));
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(2)
	public void redis_문자열_조회_테스트() {
		StringBuilder sb = new StringBuilder();
		sb.append("/getString?key=").append("testString");
		
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.get(sb.toString())
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(result -> {
						MockHttpServletResponse response = result.getResponse();
						String res = response.getContentAsString();
						
						LOGGER.debug(res);
						assertTrue(res.contains(RedisAttr.SUCCESS.normalCase()));
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(3)
	public void redis_리스트_저장_테스트() {
		String url = "/setList";
		String redisList = "{\"key\": \"testList\", \"list\":[\"item\", \"item2\", \"item3\", \"item4\", \"item5\"]}";
		
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.post(url)
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(redisList))
					.andExpect(result -> {
						MockHttpServletResponse response = result.getResponse();
						String res = response.getContentAsString();
						
						LOGGER.debug(res);
						assertTrue(res.contains(RedisAttr.SUCCESS.normalCase()));
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(4)
	public void redis_리스트_조회_테스트() {
		StringBuilder sb = new StringBuilder();
		sb.append("/getList?key=").append("testList");
		
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.get(sb.toString())
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andExpect(result -> {
						MockHttpServletResponse response = result.getResponse();
						String res = response.getContentAsString();
						
						LOGGER.debug(res);
						assertTrue(res.contains(RedisAttr.SUCCESS.normalCase()));
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Order(5)
	public void redis_해시_저장_테스트() {
		String url = "/setHash";
		String redisHash = "{\"key\": \"testHash\", \"hashKey\": \"hash_test_key\", \"hashVal\": \"hast test value\"}";
		
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.post(url)
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(redisHash))
					.andExpect(result -> {
						MockHttpServletResponse response = result.getResponse();
						String res = response.getContentAsString();
						
						LOGGER.debug(res);
						assertTrue(res.contains(RedisAttr.SUCCESS.normalCase()));
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	@Order(6)
	public void redis_해시_조회_테스트() {
		String url = "/getHash";
		String redisHash = "{\"key\": \"testHash\", \"hashKey\": \"hash_test_key\"}";
		
		try {
			mockMvc.perform(MockMvcRequestBuilders
							.get(url)
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(redisHash))
					.andExpect(result -> {
						MockHttpServletResponse response = result.getResponse();
						String res = response.getContentAsString();
						
						LOGGER.debug(res);
						assertTrue(res.contains(RedisAttr.SUCCESS.normalCase()));
					});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
