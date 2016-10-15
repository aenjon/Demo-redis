package com.rhwayfun.redis.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Test for redis ,simple
 */
public class RedisTest extends BaseTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * ���Բ������ȡRedis������
	 * @Title: testPutAndGet 
	 * @Description: TODO
	 * @throws
	 */
	@Test
	public void testPutAndGet() {
		redisTemplate.opsForHash().put("user", "name", "ok  this is the first one");
		Object object = redisTemplate.opsForHash().get("user", "name");
		System.out.println(object);
	}

	/**
	 * ����Redis��Ϊ���������
	 * @Title: testCache 
	 * @Description: TODO
	 * @throws InterruptedException
	 * @throws
	 */
	@Test
	public void testCache() throws InterruptedException {
		// ����һ������
		redisTemplate.opsForHash().put("user", "name", "second ,this is not the second one only");
		// ����ʧЧʱ��Ϊ2��
		redisTemplate.expire("user", 10, TimeUnit.SECONDS);
		Thread.sleep(10000);
		// 1����ȡ
		Object object = redisTemplate.opsForHash().get("user", "name");
		System.out.println("1���" + object);
		Thread.sleep(1000);
		// 2����ȡ
		object = redisTemplate.opsForHash().get("user", "name");
		System.out.println("2���" + object);
	}
}
