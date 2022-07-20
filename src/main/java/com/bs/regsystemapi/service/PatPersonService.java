package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.PatPerson;

/**
 * @Date 2022/4/24 22:54
 */
public interface PatPersonService extends IService<PatPerson> {

    void updatePerson(PatPerson patPerson);

    void deletePerson(String personNo);
}
