package com.bs.regsystemapi.modal.vo.Duty;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/27 20:38
 */
@Data
public class DutyInfo implements Serializable {

    private Long id;

    private String dutyNo;

    private String doctorNo;

    /**
     * 周日
     */
    private String sunMorning;

    /**
     * 周日
     */
    private String sunAfternoon;

    /**
     * 周日
     */
    private String sunNight;

    /**
     * 周一
     */
    private String monMorning;

    /**
     * 周一
     */
    private String monAfternoon;

    /**
     * 周一
     */
    private String monNight;

    /**
     * 周二
     */
    private String tueMorning;

    /**
     * 周二
     */
    private String tueAfternoon;

    /**
     * 周二
     */
    private String tueNight;

    /**
     * 周三
     */
    private String wedMorning;

    /**
     * 周三
     */
    private String wedAfternoon;

    /**
     * 周三
     */
    private String wedNight;

    /**
     * 周四
     */
    private String thurMorning;

    /**
     * 周四
     */
    private String thurAfternoon;

    /**
     * 周四
     */
    private String thurNight;

    /**
     * 周五
     */
    private String friMorning;

    /**
     * 周五
     */
    private String firAfternoon;

    /**
     * 周五
     */
    private String firNight;

    /**
     * 周六
     */
    private String satMorning;

    /**
     * 周六
     */
    private String satAfternoon;

    /**
     * 周六
     */
    private String satNight;

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

    /**
     * 排班人
     */
    private String updateName;

    /**
     * 排班时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String userRealName;
}
