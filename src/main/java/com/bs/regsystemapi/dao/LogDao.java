package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Log;
import com.bs.regsystemapi.modal.dto.log.QueryLogForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/23 21:40
 */
@Mapper
public interface LogDao extends BaseMapper<Log> {
    List<Log> getLogList(QueryLogForm form);
}
