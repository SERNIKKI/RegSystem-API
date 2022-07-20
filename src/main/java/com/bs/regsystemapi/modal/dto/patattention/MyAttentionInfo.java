package com.bs.regsystemapi.modal.dto.patattention;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date 2022/4/30 21:31
 */
@Data
public class MyAttentionInfo implements Serializable {


    /**
     * 用户号
     */
    private String patNo;

    /**
     * 医生no
     */
    private String userNo;

    /**
     * no
     */
    private String attentionNo;

    /**
     * 关注时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 关注状态，1：正在关注，0：已取消关注
     */
    private String status;

    private String userRealName;

    private String userPosition;

    private String userDepartment;




}
