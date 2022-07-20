package com.bs.regsystemapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author wgg
 * @date 2022-04-20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "patient")
public class PatientEntity implements Serializable {

    private static final long serialVersionUID = 7817800771348398791L;

    @ApiModelProperty(name = "id", value = "主键", required = false)
    @TableField(value = "id")
    @TableId
    private Long id;

    @ApiModelProperty(name = "patNo", value = "用户号，存openid", required = true)
    @NotBlank(message = "用户号不可为空!")
    @TableField(value = "pat_no")
    private String patNo;


    /**
     * 用户昵称
     */
    @ApiModelProperty(name = "patNickName", value = "用户昵称", required = false)
    @TableField(value = "pat_nickName")
    private String patNickName;

    /**
     * 图片地址
     */
    @ApiModelProperty(name = "patImgsrc", value = "图片地址", required = false)
    @TableField(value = "pat_imgsrc")
    private String patImgsrc;

    /**
     * 患者性别
     */
    @ApiModelProperty(name = "patGender", value = "患者性别", required = false)
    @TableField(value = "pat_gender")
    private String patGender;

    /**
     * 患者真实姓名
     */
    @ApiModelProperty(name = "patRealName", value = "患者真实姓名", required = true)
    @NotBlank(message = "患者真实姓名不可为空!")
    @TableField(value = "pat_realName")
    private String patRealName;

    /**
     * 患者联系方式
     */
    @ApiModelProperty(name = "patPhone", value = "患者联系方式", required = true)
    @NotBlank(message = "患者联系方式不可为空!")
    @TableField(value = "pat_phone")
    private String patPhone;

    /**
     * 患者地址
     */
    @ApiModelProperty(name = "patAddress", value = "患者地址", required = false)
    @TableField(value = "pat_address")
    private String patAddress;

    /**
     * 患者邮箱
     */
    @ApiModelProperty(name = "patMail", value = "患者邮箱", required = false)
    @TableField(value = "pat_mail")
    private String patMail;

    /**
     * 患者密码
     */
    @ApiModelProperty(name = "patPassword", value = "患者密码", required = true)
    @NotBlank(message = "患者密码不可为空!")
    @TableField(value = "pat_password")
    private String patPassword;

    /**
     * 患者账号
     */
    @ApiModelProperty(name = "patAccount", value = "患者账号", required = true)
    @NotBlank(message = "患者账号不可为空!")
    @TableField(value = "pat_account")
    private String patAccount;

    /**
     * 是否已删除，1：正常，0：已删除
     */
    @ApiModelProperty(name = "isDelete", value = "是否已删除，1：正常，0：已删除", required = false)
    @TableField(value = "is_delete")
    private String isDelete;


}
