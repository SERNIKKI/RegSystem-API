package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Area;
import com.bs.regsystemapi.modal.vo.province.ParentProvinceList;

import java.util.List;

/**
 * @author qpj
 * @date 2022/4/24 16:45
 */
public interface AreaService extends IService<Area> {
    List<ParentProvinceList> getProvinceList();
}
