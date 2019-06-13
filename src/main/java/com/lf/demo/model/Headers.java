package com.lf.demo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/11
 * @desc
 * @see
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HEADERS")
@XmlType(propOrder = {
        "HEADER",
})
public class Headers {

    private List<LocationDO> HEADER;

    @Override
    public String toString() {
        return "Headers{" +
                "HEADER=" + HEADER +
                '}';
    }

    public Headers(List<LocationDO> HEADER) {
        this.HEADER = HEADER;
    }

    public Headers() {
    }

    public List<LocationDO> getHEADER() {
        return HEADER;
    }

    public void setHEADER(List<LocationDO> HEADER) {
        this.HEADER = HEADER;
    }
}
