package com.lf.demo.client;

import com.lf.demo.model.Headers;
import com.lf.demo.model.LocationDO;
import com.lf.demo.model.User;
import com.lf.demo.util.XMLUtil;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import java.util.Date;
import java.util.List;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/11
 * @desc
 * @see
 * @since 1.0
 */
public class ClientDemo {

    public static void main(String[] args) {

       /* Headers headers = new Headers();
        List<LocationDO> list = new ArrayList<LocationDO>();
        list.add(new LocationDO("ZYL-11F-2","住院楼11楼2区", "T"));
        list.add(new LocationDO("ZYL-11F-A","住院楼11楼A区", "T"));
        list.add(new LocationDO("ZYL-11F-B","住院楼11楼B区", "T"));
        headers.setHEADER(list);
        System.out.println("---将对象转换成string类型的xml Start---");
        // 将对象转换成string类型的xml
        String str = XMLUtil.convertToXml(headers);
        // 输出
        System.out.println(str);
        System.out.println("---将对象转换成string类型的xml End---");
        System.out.println();
        System.out.println("---将String类型的xml转换成对象 Start---");*/
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
        int startIndex = str.indexOf("<P_REQUEST_DATA>") + "<P_REQUEST_DATA>".length();
        int endIndex = str.indexOf("</P_REQUEST_DATA>");
        String substring = str.substring(startIndex, endIndex);
        Headers userTest = (Headers) XMLUtil.convertXmlStrToObject(Headers.class, substring);
        List<LocationDO> header = userTest.getHEADER();
        for (LocationDO locationDO : header) {
            System.out.println("结果："+locationDO.getLocationID()+","+locationDO.getLocationDesc()+","+locationDO.getLocStatus());
        }

        System.out.println("---将String类型的xml转换成对象 End---");
    }


    public void test1(){
        // 创建需要转换的对象
        User user = new User(1, "Steven", "@sun123", new Date(), 1000.0);
        System.out.println("---将对象转换成string类型的xml Start---");
        // 将对象转换成string类型的xml
        String str = XMLUtil.convertToXml(user);
        // 输出
        System.out.println(str);
        System.out.println("---将对象转换成string类型的xml End---");
        System.out.println();
        System.out.println("---将String类型的xml转换成对象 Start---");
        User userTest = (User) XMLUtil.convertXmlStrToObject(User.class, str);
        System.out.println(userTest);
        System.out.println("---将String类型的xml转换成对象 End---");
    }

    public void test() {
        // 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:9000/services/CommonService?wsdl");

        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,PASS_WORD));
        Object[] objects = new Object[0];
        try {

            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sayHello", "wyj");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }
}
