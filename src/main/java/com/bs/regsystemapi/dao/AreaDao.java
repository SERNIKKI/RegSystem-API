package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Area;
import com.bs.regsystemapi.modal.vo.province.ChildProvinceList;
import com.bs.regsystemapi.modal.vo.province.ParentProvinceList;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/4/24 16:40
 */
@Mapper
public interface AreaDao extends BaseMapper<Area> {

    List<ParentProvinceList> getParentList();

    List<ChildProvinceList> getChildList(String parentId);
}
