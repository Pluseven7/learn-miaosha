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
public class MiaoshaGoods implements Serializable {

    private static final long serialVersionUID = -5421191738477338134L;

    private Long id;

    private Long goodsId;

    private BigDecimal miaoshaPrice;

    private Integer stockCount;

    private Date startDate;

    private Date endDate;

}