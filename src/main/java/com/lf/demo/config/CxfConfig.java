package com.lf.demo.config;

import com.lf.demo.demo.CommonService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/11
 * @desc
 * @see
 * @since 1.0
 */
@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;

    @Autowired
    CommonService commonService;

    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, commonService);
        endpoint.publish("/CommonService");
        return endpoint;
    }

}
