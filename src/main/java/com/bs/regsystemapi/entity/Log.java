package com.bs.regsystemapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/23 21:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value ="log")
public class Log implements Serializable {

    private static final long serialVersionUID =  7668332885486238085L;

    @TableField(value = "id" )
    @TableId
    private Long id;

    @TableField(value = "log_no")
    private String logNo;

    @TableField(value = "action")
    private String action;

    @TableField(value = "create_name")
    private String createName;

    @TableField(value = "create_time" )
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @TableField(value = "status")
    private String status;

    @TableField(value = "effect_table")
    private String effectTable;

    @TableField(value = "effect_nos")
    private String effectNos;

    @TableField(value = "user_type")
    private String userType;
}
