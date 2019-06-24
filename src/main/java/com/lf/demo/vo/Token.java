package com.lf.demo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author linfei
 * @version 1.0
 * @date 2019/6/24
 * @desc
 * @see
 * @since 1.0
 */
@Data
public class Token implements Serializable {
    private Long id;
    private String type;
    private String name;
    private Date ts;
}
