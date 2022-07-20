package com.bs.regsystemapi.modal.vo.Duty;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/4/20 16:19
 */
@Data
public class DoctorDuty implements Serializable {

    /**
     * 早上开始
     */
    @JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date morningBegin;

    /**
     * 早上结束
     */
    @JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date morningEnd;

    /**
     * 下午开始
     */
    @JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date afternoonBegin;

    /**
     * 下午结束
     */
    @JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date afternoonEnd;

    /**
     * 晚上开始
     */
    @JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date nightBegin;

    /**
     * 晚上结束
     */
    @JsonFormat(pattern="HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date nightEnd;

    private String userRealName;

    private List<DutyList> dutyLists;

    public DoctorDuty(Date morningBegin, Date morningEnd, Date afternoonBegin, Date afternoonEnd, Date nightBegin, Date nightEnd, String userRealName) {
        this.morningBegin = morningBegin;
        this.morningEnd = morningEnd;
        this.afternoonBegin = afternoonBegin;
        this.afternoonEnd = afternoonEnd;
        this.nightBegin = nightBegin;
        this.nightEnd = nightEnd;
        this.userRealName = userRealName;
    }
}
