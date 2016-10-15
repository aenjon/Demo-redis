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
	 * 测试插入与获取Redis的数据
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
	 * 测试Redis作为缓存的例子
	 * @Title: testCache 
	 * @Description: TODO
	 * @throws InterruptedException
	 * @throws
	 */
	@Test
	public void testCache() throws InterruptedException {
		// 插入一条数据
		redisTemplate.opsForHash().put("user", "name", "second ,this is not the second one only");
		// 设置失效时间为2秒
		redisTemplate.expire("user", 10, TimeUnit.SECONDS);
		Thread.sleep(10000);
		// 1秒后获取
		Object object = redisTemplate.opsForHash().get("user", "name");
		System.out.println("1秒后：" + object);
		Thread.sleep(1000);
		// 2秒后获取
		object = redisTemplate.opsForHash().get("user", "name");
		System.out.println("2秒后：" + object);
	}
}
