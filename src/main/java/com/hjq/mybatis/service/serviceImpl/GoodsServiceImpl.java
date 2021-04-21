package com.hjq.mybatis.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjq.mybatis.entity.Goods;
import com.hjq.mybatis.mapper.GoodsMapper;
import com.hjq.mybatis.service.GoodsService;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService{
}
