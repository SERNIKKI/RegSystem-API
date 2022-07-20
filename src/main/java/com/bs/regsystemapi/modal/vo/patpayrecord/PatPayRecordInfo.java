package com.bs.regsystemapi.modal.vo.patpayrecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class PatPayRecordInfo implements Serializable {

    /**
     * 用户no
     */
    private String patNo;

    /**
     * 医生no
     */
    private String doctorNo;

    /**
     * 挂号费用
     */
    private Double userCost;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单状态，0待付款1已付款
     */
    private String orderState;

    /**
     * 付款类型
     */
    private String payType;

    /**
     * 支付方式
     */
    private String payMode;

    /**
     * 缴费时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 就诊卡号
     */
    private String personNo;

    /**
     * 就诊人姓名
     */
    private String personName;

    /**
     * 预约号
     */
    private String regNo;

    /**
     * 订单创建时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    /**
     * 订单完成时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;

    /**
     * 是否已删除，1：正常，0：已删除
     */
    private String isDelete;
}
