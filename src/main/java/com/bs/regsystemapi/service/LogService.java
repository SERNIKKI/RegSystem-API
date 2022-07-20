package com.bs.regsystemapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bs.regsystemapi.entity.Log;
import com.bs.regsystemapi.modal.dto.log.QueryLogForm;
import com.bs.regsystemapi.utils.ManagePageResult;

/**
 * @author qpj
 * @date 2022/3/23 21:43
 */
public interface LogService extends IService<Log> {
    void addLog(String action, String effectTable, String effectNos, String status);
    void addLog(String action, String effectTable, String effectNos);
    void addLog(String action);
    ManagePageResult getLogList(QueryLogForm form);
}
