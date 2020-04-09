package com.xiaomo.client.config;//package com.deepexi.domain.marketing.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisPassword;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
///**
// * 缓存配置
// *
// * @author linpbin
// * @version 1.0
// * @date 2020-03-12 16:50
// */
//@Configuration
//@EnableCaching
//public class CacheConfig extends CachingConfigurerSupport {
//    private Logger logger = LoggerFactory.getLogger(CacheConfig.class);
//
//    @Autowired
//    private Environment environment;
//
//
////    @Bean
////    public JedisConnectionFactory redisConnectionFactory() {
////        return getConnectionFactory("spring.redis.sentinel.share");
////    }
//
////    private JedisConnectionFactory getConnectionFactory(String prefix) {
////        RedisSentinelConfiguration redisSentinelConfiguration = getRedisSentinelConfiguration(prefix);
////
////        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
////        jedisPoolConfig.setMaxIdle(environment.getProperty(prefix + ".max-idle", Integer.class));
////        jedisPoolConfig.setMaxTotal(environment.getProperty(prefix + ".max-total", Integer.class));
////        jedisPoolConfig.setMaxWaitMillis(environment.getProperty(prefix + ".max-wait-millis", Integer.class));
////        jedisPoolConfig.setBlockWhenExhausted(environment.getProperty(prefix + ".block-when-exhausted", Boolean.class));
////        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jedisPoolingClientConfigurationBuilder = JedisClientConfiguration.builder().usePooling().poolConfig(jedisPoolConfig);
////        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolingClientConfigurationBuilder.build());
////        jedisConnectionFactory.afterPropertiesSet();
////
////        logger.info("redis sentinel started, master:{}, nodes:{}, db:{}", redisSentinelConfiguration.getMaster().getName(),
////                redisSentinelConfiguration.getSentinels(),
////                redisSentinelConfiguration.getDatabase());
////        return jedisConnectionFactory;
////    }
//
//    /**
//     * redis 哨兵配置
//     *
//     * @param prefix
//     * @return
//     */
////    private RedisSentinelConfiguration getRedisSentinelConfiguration(String prefix) {
////        Map<String, Object> source = Maps.newHashMap();
////        // 主节点的名称
////        source.put("spring.redis.sentinel.master", environment.getProperty(prefix + ".master"));
////        // 哨兵节点
////        source.put("spring.redis.sentinel.nodes", environment.getProperty(prefix + ".nodes"));
////        RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration(new MapPropertySource("RedisSentinelConfiguration", source));
////
////        // redis db
////        redisSentinelConfiguration.setDatabase(Integer.valueOf(environment.getProperty(prefix + ".database")));
////        // redis password
////        String password = environment.getProperty(prefix + ".password");
////        if (StringUtils.isNotBlank(password)) {
////            redisSentinelConfiguration.setPassword(RedisPassword.of(password));
////        }
////        return redisSentinelConfiguration;
////    }
//
//    /**
//     * 交付时去掉这个配置，改成上面的哨兵模式
//     *
//     * @return
//     */
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        String host = environment.getProperty("spring.redis.host");
//        String port = environment.getProperty("spring.redis.port");
//        String password = environment.getProperty("spring.redis.password");
//        RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration(host, Integer.valueOf(port));
//        RedisPassword redisPassword = RedisPassword.of(password);
//        standaloneConfig.setPassword(redisPassword);
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(standaloneConfig);
//        return jedisConnectionFactory;
//    }
//
//    @Bean
//    RedisTemplate<Object, Object> redisTemplate() {
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringRedisSerializer);
//        redisTemplate.setHashKeySerializer(stringRedisSerializer);
//
//        @SuppressWarnings({"rawtypes", "unchecked"})
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        return redisTemplate;
//    }
//
//
//    @Override
//    @Bean
//    public CacheManager cacheManager() {
//        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager
//                .RedisCacheManagerBuilder
//                .fromConnectionFactory(redisConnectionFactory());
//        RedisCacheManager cacheManager = builder.build();
//        return cacheManager;
//    }
//
//
//}
