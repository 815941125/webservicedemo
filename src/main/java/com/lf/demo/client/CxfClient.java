package com.lf.demo.client;

import com.lf.demo.demo.CommonService;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/11
 * @desc
 * @see
 * @since 1.0
 */
public class CxfClient {

    @Autowired
    private static RestTemplate restTemplate;

    public static void main(String[] args) {
//        test2();
        cl2();
    }

    public static void test2() {
        try {
            // 接口地址
            String address = "http://localhost:9000/services/CommonService?wsdl";
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(CommonService.class);
            // 创建一个代理接口实现
            CommonService cs = (CommonService) jaxWsProxyFactoryBean.create();
            // 数据准备
            String str = "<Input>\n" +
                    "\n" +
                    "    <RESTHeader>\n" +
                    "\n" +
                    "        <Responsibility/>\n" +
                    "\n" +
                    "        <RespApplication/>\n" +
                    "\n" +
                    "        <SecurityGroup/>\n" +
                    "\n" +
                    "        <NLSLanguage>AMERICAN</NLSLanguage>\n" +
                    "\n" +
                    "        <Org_Id>0</Org_Id>\n" +
                    "\n" +
                    "    </RESTHeader>\n" +
                    "\n" +
                    "    <InputParameters>\n" +
                    "\n" +
                    "        <P_SOURCE_CODE>BI</P_SOURCE_CODE>\n" +
                    "\n" +
                    "        <P_IFACE_CODE>BIEBS002</P_IFACE_CODE>\n" +
                    "\n" +
                    "        <P_REQUEST_DATA>\n" +
                    "\n" +
                    "            <HEADERS>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11F-2</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼2区</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11F-A</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼A区</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11F-B</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼B区</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11ST-14</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼B区ST-14楼梯间</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11ST-2</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼A区ST-2楼梯间</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11ST-3</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼A区ST-3楼梯间</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11ST-4</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼B区ST-4楼梯间</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-12101</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼12楼1区会议室卫生间</LocationDesc>\n" +
                    "\n" +
                    "                <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "            </HEADER>\n" +
                    "\n" +
                    "        </HEADERS>\n" +
                    "\n" +
                    "                   </P_REQUEST_DATA>\n" +
                    "\n" +
                    "         </InputParameters>\n" +
                    "\n" +
                    "</input>";
            // 调用代理接口的方法调用并返回结果
            Boolean result = cs.getLocationsInfo(str);

            System.out.println("客户端请求结果："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String test1() {
        // 创建动态客户端
        String address = " http://ws.webxml.com.cn/WebServices/WeatherWS.asmx?wsdl";
        String str = restTemplate.postForEntity(address,null,String.class).getBody();
        return str;
    }

    /**
     * 方式1.代理类工厂的方式,需要拿到对方的接口
     */
    public static void cl1() {
       /* try {
            // 接口地址
            String address = "http://localhost:8080/services/CommonService?wsdl";
            // 代理工厂
            JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
            // 设置代理地址
            jaxWsProxyFactoryBean.setAddress(address);
            // 设置接口类型
            jaxWsProxyFactoryBean.setServiceClass(CommonService.class);
            // 创建一个代理接口实现
            CommonService cs = (CommonService) jaxWsProxyFactoryBean.create();
            // 数据准备
            String userName = "Leftso";
            // 调用代理接口的方法调用并返回结果
            List<UserDO> result = cs.sayHello(userName);
            for (UserDO user : result) {
                System.out.println("返回结果:" + user.toString());
                System.out.println("id:" + user.getId());
                System.out.println("name:" + user.getName());
                System.out.println("age:" + user.getAge());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 动态调用方式
     */
    public static void cl2() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:9030/services/CodeBlueService?wsdl");
//        Client client = dcf.createClient("http://180.167.213.26:9030/services/CodeBlueService?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects = new Object[0];
        try {
            String str = "<Input>\n" +
                    "\n" +
                    "    <RESTHeader>\n" +
                    "\n" +
                    "        <Responsibility/>\n" +
                    "\n" +
                    "        <RespApplication/>\n" +
                    "\n" +
                    "        <SecurityGroup/>\n" +
                    "\n" +
                    "        <NLSLanguage>AMERICAN</NLSLanguage>\n" +
                    "\n" +
                    "        <Org_Id>0</Org_Id>\n" +
                    "\n" +
                    "    </RESTHeader>\n" +
                    "\n" +
                    "    <InputParameters>\n" +
                    "\n" +
                    "        <P_SOURCE_CODE>BI</P_SOURCE_CODE>\n" +
                    "\n" +
                    "        <P_IFACE_CODE>BIEBS002</P_IFACE_CODE>\n" +
                    "\n" +
                    "        <P_REQUEST_DATA>\n" +
                    "\n" +
                    "            <HEADERS>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11F-2</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼2211区</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11F-A</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼AA111区</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11F-B</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼BB区</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11ST-14</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼B区ST-14楼梯间</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11ST-2</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼A区ST-2楼梯间</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11ST-3</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼A区ST-3楼梯间</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-11ST-4</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼11楼B区ST-4楼梯间</LocationDesc>\n" +
                    "\n" +
                    "                    <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "                </HEADER>\n" +
                    "\n" +
                    "                <HEADER>\n" +
                    "\n" +
                    "                    <LocationID>ZYL-12101</LocationID>\n" +
                    "\n" +
                    "                    <LocationDesc>住院楼12楼1区会议室卫生间</LocationDesc>\n" +
                    "\n" +
                    "                <LocStatus>T</LocStatus>\n" +
                    "\n" +
                    "            </HEADER>\n" +
                    "\n" +
                    "        </HEADERS>\n" +
                    "\n" +
                    "                   </P_REQUEST_DATA>\n" +
                    "\n" +
                    "         </InputParameters>\n" +
                    "\n" +
                    "</input>";
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("getLocations",str);
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
