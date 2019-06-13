package com.lf.demo.demo;

import com.lf.demo.model.Headers;
import com.lf.demo.model.LocationDO;
import com.lf.demo.model.UserDO;
import com.lf.demo.util.XMLUtil;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/11
 * @desc
 * @see
 * @since 1.0
 */
@WebService(serviceName = "CommonService", // 与接口中指定的name一致
        targetNamespace = "http://demo.lf.com/", // 与接口中的命名空间一致,一般是接口的包名倒
        endpointInterface = "com.lf.demo.demo.CommonService"
)
@Component
public class CommonServiceImpl implements CommonService {
    @Override
    public Boolean getLocationsInfo(String xmlStr) {
        if (null == xmlStr) {
            return Boolean.FALSE;
        }
        //删除数据库
        System.out.println("删除");
        //添加数据库
        System.out.println("添加");
        int startIndex = xmlStr.indexOf("<P_REQUEST_DATA>") + "<P_REQUEST_DATA>".length();
        int endIndex = xmlStr.indexOf("</P_REQUEST_DATA>");
        String substring = xmlStr.substring(startIndex, endIndex);
        Headers userTest = (Headers) XMLUtil.convertXmlStrToObject(Headers.class, substring);
        List<LocationDO> header = userTest.getHEADER();
        for (LocationDO locationDO : header) {
            System.out.println("结果："+locationDO.getLocationID()+","+locationDO.getLocationDesc()+","+locationDO.getLocStatus());
        }
        //记录日志操作
        System.out.println("记录日志");
        return Boolean.TRUE;
    }


   /* @Override
    public List<UserDO> sayHello(String name) {
        List<UserDO> list = new ArrayList<UserDO>();
        UserDO userDO = new UserDO();
        userDO.setId(1);
        userDO.setName("小明");
        userDO.setAge(18);

        UserDO userDO1 = new UserDO();
        userDO1.setId(2);
        userDO1.setName("小红");
        userDO1.setAge(19);

        UserDO userDO2 = new UserDO();
        userDO2.setId(3);
        userDO2.setName(name);
        userDO2.setAge(10);

        list.add(userDO);
        list.add(userDO1);
        list.add(userDO2);
        return list;
    }*/

}
