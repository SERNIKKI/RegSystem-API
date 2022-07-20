package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PatPersonDao;
import com.bs.regsystemapi.entity.PatPerson;
import com.bs.regsystemapi.service.PatPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Date 2022/4/24 22:55
 */
@Service
public class PatPersonServiceImpl extends ServiceImpl<PatPersonDao, PatPerson> implements PatPersonService {
    @Autowired
    private PatPersonService patPersonService;

    @Override
    public void updatePerson(PatPerson patPerson) {
        this.baseMapper.updateById(patPerson);
    }

    @Override
    public void deletePerson(String personNo) {
        UpdateWrapper<PatPerson> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("person_no", personNo)
                .set("is_delete", "0");
        patPersonService.update(updateWrapper);
    }
}
