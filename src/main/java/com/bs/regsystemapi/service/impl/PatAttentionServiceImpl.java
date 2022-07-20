package com.bs.regsystemapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bs.regsystemapi.dao.PatAttentionDao;
import com.bs.regsystemapi.entity.PatAttention;
import com.bs.regsystemapi.service.PatAttentionService;
import org.springframework.stereotype.Service;

/**
 * @Date 2022/4/26 22:13
 */
@Service
public class PatAttentionServiceImpl extends ServiceImpl<PatAttentionDao, PatAttention> implements PatAttentionService {
}
