package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PrescriptionHerbsDao;
import com.bs.regsystemapi.entity.PrescriptionHerbs;
import com.bs.regsystemapi.service.PrescriptionHerbsService;
import org.springframework.stereotype.Service;

/**
 * @author qpj
 * @date 2022/5/6 11:28
 */
@Service("prescriptionHerbsService")
public class PrescriptionHerbsServiceImpl extends ServiceImpl<PrescriptionHerbsDao, PrescriptionHerbs> implements PrescriptionHerbsService {

}
