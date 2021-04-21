package com.hjq.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiaoshaMessage implements Serializable {

    private static final long serialVersionUID = -448031470216659611L;

    private Long id;

    private Long messageid;

    private Date createTime;

    private Integer status;

    private Date overTime;

    private Integer messageType;

    private Integer sendType;

    private String goodName;

    private BigDecimal price;

    private String content;

}