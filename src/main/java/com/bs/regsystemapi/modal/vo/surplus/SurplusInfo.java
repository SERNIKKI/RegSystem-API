package com.bs.regsystemapi.modal.vo.surplus;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
public class SurplusInfo implements Serializable {

    private Long id;

    private String surplusNo;

    private String doctorNo;

    /**
     * 周日
     */
    private Long sunMorning;

    private Long sunAfternoon;

    private Long sunNight;

    private Long monMorning;

    private Long monAfternoon;

    private Long monNight;

    private Long tueMorning;

    private Long tueAfternoon;

    private Long tueNight;

    private Long wedMorning;

    private Long wedAfternoon;

    private Long wedNight;

    private Long thurMorning;

    private Long thurAfternoon;

    private Long thurNight;

    private Long friMorning;

    private Long firAfternoon;

    private Long firNight;

    private Long satMorning;

    private Long satAfternoon;

    private Long satNight;

    private String updateName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String userRealName;

    private String secondDepartment;

    private String userSpecialties;
}
