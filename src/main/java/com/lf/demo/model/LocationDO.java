package com.lf.demo.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/11
 * @desc
 * @see
 * @since 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "HEADER")
@XmlType(propOrder = {
        "LocationID",
        "LocationDesc",
        "LocStatus",
})
public class LocationDO implements Serializable {
    private static final long serialVersionUID = 2L;

    private String LocationID;
    private String LocationDesc;
    private String LocStatus;

    @Override
    public String toString() {
        return "LocationDO{" +
                "LocationID='" + LocationID + '\'' +
                ", LocationDesc='" + LocationDesc + '\'' +
                ", LocStatus='" + LocStatus + '\'' +
                '}';
    }

    public LocationDO(String locationID, String locationDesc, String locStatus) {
        LocationID = locationID;
        LocationDesc = locationDesc;
        LocStatus = locStatus;
    }

    public LocationDO() {
    }


    public String getLocStatus() {
        return LocStatus;
    }

    public void setLocStatus(String locStatus) {
        LocStatus = locStatus;
    }

    public String getLocationDesc() {
        return LocationDesc;
    }

    public void setLocationDesc(String locationDesc) {
        LocationDesc = locationDesc;
    }

    public String getLocationID() {
        return LocationID;
    }

    public void setLocationID(String locationID) {
        LocationID = locationID;
    }
}
