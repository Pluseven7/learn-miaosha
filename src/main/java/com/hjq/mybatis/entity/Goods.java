package com.hjq.mybatis.entity;


import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods implements Serializable {

    private static final long serialVersionUID = 4183238707777357311L;

    private Long id;

    private String goodsName;

    private String goodsTitle;

    private String goodsImg;

    private BigDecimal goodsPrice;

    private Integer goodsStock;

    private String goodsDetail;

}