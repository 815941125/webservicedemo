package com.lf.demo.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;

/**
 * PACKAGE      :  com.jiahui.eappt.util
 * CREATE DATE  :  2018/4/2
 * AUTHOR       :  linfei
 * 文件描述      :
 */
public class HttpUtil {

    /**
     * @param request request对象
     * @return ip
     */
    public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("clientip"); // for UC browser
        if (ip == null) {
            ip = request.getHeader("X-Real-IP");
            if (ip == null) {
                ip = request.getHeader("X-Forwarded-For");
                if (ip == null) {
                    ip = request.getRemoteAddr();
                }
            }
        }
        return ip;
    }

    /**
     * @param request 请求
     * @return ua
     */
    public static String getUA(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua != null ? ua : "";
    }

    /**
     * 获得String类型的参数。首先从request参数中拿，若不存在，则从Cookie中获得。
     *
     * @param request 请求
     * @param key     参数名
     * @return 参数值
     */
    public static String getParameter(HttpServletRequest request, String key) {
        return request.getParameter(key);
    }

    /**
     * 根据request拼接queryString
     *
     * @return queryString
     */
    public static String buildQueryString(HttpServletRequest request) {
        @SuppressWarnings("unchecked") Enumeration<String> es = request.getParameterNames();
        if (!es.hasMoreElements()) {
            return "";
        }
        String parameterName, parameterValue;
        StringBuilder params = new StringBuilder();
        while (es.hasMoreElements()) {
            parameterName = es.nextElement();
            parameterValue = request.getParameter(parameterName);
            params.append(parameterName).append("=").append(parameterValue).append("&");
        }
        return params.deleteCharAt(params.length() - 1).toString();
    }

    /**
     * 微信提交xml参数
     */
    public static String xmlPost(String url1, String xml) {
        try {
            // 创建url资源
            URL url = new URL(url1);
            // 建立http连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);

            conn.setDoInput(true);

            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");
            // 设置维持长连接
            conn.setRequestProperty("Connection", "Keep-Alive");
            // 设置文件字符集:
            conn.setRequestProperty("Charset", "UTF-8");
            //转换为字节数组
            byte[] data = xml.getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("contentType", "text/xml");
            // 开始连接请求
            conn.connect();
            OutputStream out = conn.getOutputStream();
            // 写入请求的字符串
            out.write(data);
            out.flush();
            out.close();

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                String a = null;
                try {
                    byte[] data1 = new byte[in.available()];
                    in.read(data1);
                    // 转成字符串
                    a = new String(data1);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                return a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * HttpClient单例持有
     */
    private static class HttpClientHolder {
        private static final CloseableHttpClient INSTANCE = HttpClients.custom()
                .disableAutomaticRetries()
                .setMaxConnTotal(10240)
                .setMaxConnPerRoute(512)
                .setDefaultRequestConfig(RequestConfig.custom().setConnectTimeout((int) (30 * DateUtil.SECOND_LONG)).setSocketTimeout((int) (30 * DateUtil.SECOND_LONG)).build())// TODO 【要设置下httpclient的默认超时时间】
                .setUserAgent("")
                .build();
    }

    /**
     * @return HttpClient单例
     */
    public static CloseableHttpClient getHttpClient() {
        return HttpClientHolder.INSTANCE;
    }

    /**
     * 安静的关闭，即使抛出异常
     *
     * @param response 响应
     */
    public static void closeQuietly(CloseableHttpResponse response) {
        if (response == null) {
            return;
        }
        try {
            response.close();
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }
}