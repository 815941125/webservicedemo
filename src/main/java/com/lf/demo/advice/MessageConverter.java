package com.lf.demo.advice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.sun.corba.se.spi.ior.ObjectId;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.util.Date;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/24
 * @desc 返回数据处理器
 * @see
 * @since 1.0
 */
public class MessageConverter extends AbstractHttpMessageConverter<Object> {

    public MessageConverter() {
        super(MediaType.ALL);
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    protected Object readInternal(Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        FileCopyUtils
                .copy(JSON.toJSONString(o, filter, SerializerFeature.DisableCircularReferenceDetect)
                        .getBytes(), httpOutputMessage.getBody());
    }

    ValueFilter filter = (obj, s, v) -> {
        if (v instanceof ObjectId) {
            return v.toString();
        }
        if (v instanceof Date) {
            return ((Date) v).getTime();
        }
        return v;
    };

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return this.supports(clazz) && this.canWrite(mediaType);
    }
}
