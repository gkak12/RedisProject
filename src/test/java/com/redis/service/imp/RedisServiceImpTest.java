package com.redis.service.imp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.redis.dto.RedisStringDto;

@ExtendWith(MockitoExtension.class)
class RedisServiceImpTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpTest.class);
	
//	@InjectMocks
//    private RedisServiceImp redisService;
//
//    @Mock
//    private RedisTemplate<String, Object> redisTemplate;
//    
//    @BeforeEach
//    void setUp() {
//        // Mock 객체 초기화
//        MockitoAnnotations.initMocks(this);
//
//        // redisTemplate.opsForValue()가 ValueOperations 객체를 반환하도록 설정
//        ValueOperations<String, Object> valueOperationsMock = Mockito.mock(ValueOperations.class);
//        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperationsMock);
//    }
	
	@InjectMocks
    private RedisServiceImp redisService;

    @Mock
    private RedisTemplate<String, Object> redisTemplate;
    
    @BeforeEach
    void setUp() {
        // Mock 객체 초기화
        MockitoAnnotations.initMocks(this);

        // redisTemplate.opsForValue()가 ValueOperations 객체를 반환하도록 설정
        ValueOperations<String, Object> valueOperationsMock = Mockito.mock(ValueOperations.class);
        Mockito.when(redisTemplate.opsForValue()).thenReturn(valueOperationsMock);
    }

    @Test
    void testSetString() throws Exception{
        // Given
        RedisStringDto redisStringDto = RedisStringDto.builder().key("testKey").val("testValue").build();

        // When
		redisService.setString(redisStringDto);

        // Then
        verify(redisTemplate).opsForValue().set("testKey", "testValue", 3600, TimeUnit.SECONDS);
    }

    @Test
    void testGetString() throws Exception {
        // Given
        String key = "testKey";
        when(redisTemplate.opsForValue().get(key)).thenReturn("testValue");

        // When
        String result = redisService.getString(key);

        // Then
        assertEquals("testValue", result);
    }
	
//	@Test
//	public void 테스트() {
//		MyClass myClassMock = mock(MyClass.class);
//		
//		when(myClassMock.method("hello")).thenReturn("world");
//		
//		String res = myClassMock.method("hello");
//		
//		assertEquals("world", res);
//		verify(myClassMock).method("hello");
//	}
}
