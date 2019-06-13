package com.lf.demo.demo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/11
 * @desc
 * @see
 * @since 1.0
 */
@WebService(name = "CommonService",
            targetNamespace = "http://demo.lf.com/")
public interface CommonService {

    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    Boolean getLocationsInfo(@WebParam(name = "xmlStr") String xmlStr);
}
