package com.lf.demo.service;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/24
 * @desc 访问限制
 * @see
 * @since 1.0
 */
public class AccessLimitService {

    RateLimiter rateLimiter = null;

    public AccessLimitService(double limit) {
        rateLimiter = RateLimiter.create(limit);
    }

    /**
     * 尝试获取令牌
     * @return
     */
    public boolean tryAcquire() {
        return rateLimiter.tryAcquire();
    }
}
