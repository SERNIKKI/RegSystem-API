package com.bs.regsystemapi.modal.vo.drug;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/22 15:16
 */
@Data
public class StockInfo implements Serializable {

    private Long id;

    /**
     * 药品no
     */
    private String drugNo;

    private String drugName;

    /**
     * 药物类型
     */
    private String drugType;

    /**
     * 一月卖出数
     */
    private Long countJan;

    /**
     * 二月
     */
    private Long countFeb;

    /**
     * 三月
     */
    private Long countMar;

    /**
     * 四月
     */
    private Long countApr;

    /**
     * 五月
     */
    private Long countMay;

    /**
     * 六月
     */
    private Long countJun;

    /**
     * 七月
     */
    private Long countJul;

    /**
     * 八月
     */
    private Long countAug;

    /**
     * 九月
     */
    private Long countSep;

    /**
     * 十月
     */
    private Long countOct;

    /**
     * 十一月
     */
    private Long countNov;

    /**
     * 十二月
     */
    private Long countDec;

    /**
     * 总数
     */
    private Long countTotal;

    /**
     * 售出数
     */
    private Long countSold;

    /**
     * 上一次库存更改时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateName;

    private String drugUnit;

}
