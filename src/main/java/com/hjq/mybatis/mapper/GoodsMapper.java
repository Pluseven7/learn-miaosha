package com.hjq.mybatis.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjq.mybatis.entity.Goods;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface GoodsMapper extends BaseMapper<Goods> {

}