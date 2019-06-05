package com.cyh.water.utils;

import com.cyh.water.common.Properties;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisDB {
	/*jedis连接池对象*/
	private static JedisPool jedisPool;

	/**
	 * 初始化连接池
	 * */
	public synchronized static void init(){
		try{
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(Properties.redisMaxTotal);
			config.setMaxIdle(Properties.redisMaxIdle);
			config.setMinIdle(Properties.redisMinIdle);//最小空闲连接数
			config.setMaxWaitMillis(Properties.redisMaxWaitMillis);
			config.setTestOnBorrow(Properties.redisTestOnBorrow);
			jedisPool = new JedisPool(config, Properties.redisHostName, Properties.redisPort, Properties.redisTimeout, Properties.redisPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
   * 获取Jedis实例
   * @return
   */
	public synchronized static Jedis getJedis(){
		try{
			if(jedisPool == null){
				init();
			}
			try {
				Jedis Jedis = jedisPool.getResource();
				return Jedis;
			}catch (Exception e){
				e.printStackTrace();
				init();
			}
			return jedisPool.getResource();
		}catch(Exception e){
			e.printStackTrace();
			init();
		}
		return jedisPool.getResource();
	}

	/**
	 *释放jedis资源
	 *@paramjedis
	 */
	public synchronized static void returnResource(Jedis jedis){
		if(jedis!=null){
			jedis.close();
		}
	}

	/**
	 * Jedis对象出异常的时候，回收Jedis对象资源
	 *
	 * @param jedis
	 */
	public synchronized static void returnBrokenResource(Jedis jedis) {
		if (jedis != null) {
			jedis.close();
		}

	}
}