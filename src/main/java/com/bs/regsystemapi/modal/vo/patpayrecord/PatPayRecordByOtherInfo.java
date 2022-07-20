package com.bs.regsystemapi.modal.vo.patpayrecord;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class PatPayRecordByOtherInfo implements Serializable {

    private Long id;

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
     * 科室
     */
    private String secondDepartment;


}
