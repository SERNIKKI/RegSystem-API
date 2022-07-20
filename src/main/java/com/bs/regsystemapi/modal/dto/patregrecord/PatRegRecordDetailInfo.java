package com.bs.regsystemapi.modal.dto.patregrecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2022/4/30 22:43
 */
@Data
public class PatRegRecordDetailInfo implements Serializable {


    /**
     * 就诊卡号
     */
    private String personNo;

    /**
     * 预约号
     */
    private String regNo;

    /**
     * 医生no
     */
    private String doctorNo;

    /**
     * 就诊时间
     */
    private String visitTime;

    /**
     * 订单状态，0待就诊,1已完成,2已取消,3未到
     */
    private String regState;

    /**
     * 疾病描述
     */
    private String illnessDetail;

    /**
     * 预约状态，0预约失败，1预约成功
     */
    private String isSuccess;

    /**
     * 就诊日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date visitData;

    /**
     * 患者备注
     */
    private String personRemark;

    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creatTime;

    /**
     * 是否已删除，1：正常，0：已删除
     */
    private String isDelete;

    /**
     * 医生姓名
     */
    private String userRealName;

    /**
     * 医生科室
     */
    private String userDepartment;

    /**
     * 医生职称
     */
    private String userPosition;

    /**
     * 预约费用
     */
    private String reservePrice;

    /**
     * 就诊人姓名
     */
    private String personName;

    /**
     * 订单状态
     */
    private String orderState;

    /**
     * 订单编号
     */
    private String orderNo;

}
