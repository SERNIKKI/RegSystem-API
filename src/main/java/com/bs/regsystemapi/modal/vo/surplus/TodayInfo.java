package com.bs.regsystemapi.modal.vo.surplus;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/4/20 14:00
 */
@Data
public class TodayInfo implements Serializable {

    private Long morning;

    private Long afternoon;

    private Long night;

}
