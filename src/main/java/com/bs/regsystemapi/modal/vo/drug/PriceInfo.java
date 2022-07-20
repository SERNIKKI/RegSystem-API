package com.bs.regsystemapi.modal.vo.drug;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/21 16:38
 */
@Data
public class PriceInfo implements Serializable {

    private Long id;

    /**
     * 药物no
     */
    private String drugNo;

    private String drugName;

    /**
     * 药物类型，1：西药，2：中成药，3：中药材
     */
    private String drugType;

    /**
     * 单价（非医保）
     */
    private String drugPrice;

    /**
     * 单位
     */
    private String drugUnit;

    /**
     * 单价（有医保）
     */
    private String drugPriceInsurance;

    /**
     * 历史最高价（非医保）
     */
    private String drugMax;

    /**
     * 历史最高价（有医保）
     */
    private String drugMaxInsurance;

    /**
     * 历史最低价（非医保）
     */
    private String drugMin;

    /**
     * 历史最低价（有医保）
     */
    private String drugMinInsurance;

    /**
     * 核准人
     */
    private String createName;

    /**
     * 核准日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修订人
     */
    private String updateName;

    /**
     * 修订日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String isInsurance;
}
