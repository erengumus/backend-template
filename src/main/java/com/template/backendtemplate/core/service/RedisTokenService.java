package com.template.backendtemplate.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisTokenService {

    private final StringRedisTemplate redisTemplate;

    public void saveRefreshToken(String token, String email, long expiration) {
        redisTemplate.opsForValue().set(token, email, expiration, TimeUnit.MILLISECONDS);
    }

    public String getEmailFromRefreshToken(String token) {
        return redisTemplate.opsForValue().get(token);
    }

    public void deleteToken(String token) {
        redisTemplate.delete(token);
    }
}
