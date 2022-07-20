package com.bs.regsystemapi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bs.regsystemapi.entity.Bulletin;
import com.bs.regsystemapi.modal.dto.bulletin.QueryBulletinForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:21
 */
@Mapper
public interface BulletinDao extends BaseMapper<Bulletin> {
    List<Bulletin> getBulletinList(QueryBulletinForm form);
    List<Bulletin> getValidBulletin();
    Bulletin getBulletinInfo(String bulletinNo);
    Integer deleteBulletin(String bulletinNo);
    Integer recoverBulletin(String bulletinNo);
}
