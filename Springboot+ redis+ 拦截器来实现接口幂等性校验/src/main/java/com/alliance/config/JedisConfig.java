package com.alliance.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {
    private Logger logger = LoggerFactory.getLogger(JedisConfig.class);


    @Value("${spring.redis.host}")
    private String host;

    public String getHost() {
        return host;
    }

    public String getSss() {
        return sss;
    }

    public int getPort() {
        return port;
    }

    public String getPassword() {
        return password;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public long getMaxWait() {
        return maxWait;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public int getTimeout() {
        return timeout;
    }

    @Value("${spring.redis.sss}")
    private String sss;
    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private long maxWait;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Bean
    public JedisPool redisPoolFactory() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(getMaxIdle());
        jedisPoolConfig.setMaxWaitMillis(getMaxWait());
        jedisPoolConfig.setMinIdle(getMinIdle());


        JedisPool jedisPool = new JedisPool(jedisPoolConfig, getHost(), getPort(), getTimeout(), getPassword());

        return jedisPool;
    }

}
