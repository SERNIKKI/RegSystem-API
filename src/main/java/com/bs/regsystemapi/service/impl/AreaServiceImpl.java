package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.AreaDao;
import com.bs.regsystemapi.entity.Area;
import com.bs.regsystemapi.modal.vo.province.ChildProvinceList;
import com.bs.regsystemapi.modal.vo.province.ParentProvinceList;
import com.bs.regsystemapi.service.AreaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qpj
 * @date 2022/4/24 16:45
 */
@Service("areaService")
public class AreaServiceImpl extends ServiceImpl<AreaDao, Area> implements AreaService {
    @Override
    public List<ParentProvinceList> getProvinceList() {
        List<ParentProvinceList> parentList = this.baseMapper.getParentList();
        for(ParentProvinceList parentProvinceList : parentList) {
            List<ChildProvinceList> childList = this.baseMapper.getChildList(parentProvinceList.getValue());
            parentProvinceList.setChildren(childList);
        }
        return parentList;
    }
}
