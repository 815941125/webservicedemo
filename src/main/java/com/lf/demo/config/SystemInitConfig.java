package com.lf.demo.config;

import com.lf.demo.advice.MessageConverter;
import com.lf.demo.service.AccessLimitService;
import com.lf.demo.service.TokenService;
import com.lf.demo.util.BeanManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/24
 * @desc 系统初始化配置
 * @see
 * @since 1.0
 */
@Configuration
public class SystemInitConfig {
    private static final Logger log = LoggerFactory.getLogger(SystemInitConfig.class);

    @Bean
    public AccessLimitService accessLimitService() {
        return new AccessLimitService(50D);
    }

    @Bean
    public TokenService tokenService(@Value("${token.keyToken}") String keyToken) {
        TokenService tokenService = new TokenService(keyToken);
        tokenService.setUseAES(true);
        return tokenService;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new MessageConverter();
    }
    @Bean
    public BeanManager beanManager() {
        return new BeanManager();
    }
}
