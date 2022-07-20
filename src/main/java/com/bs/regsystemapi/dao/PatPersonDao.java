package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.PatPerson;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Date 2022/4/24 22:55
 */
@Mapper
public interface PatPersonDao extends BaseMapper<PatPerson> {
}
