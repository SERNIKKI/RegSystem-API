package com.bs.regsystemapi.entity;

import java.io.Serializable;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
  * @author  qpj
 * @date 2022-02-25 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="login_info")
@ToString
public class LoginInfo implements Serializable {

	private static final long serialVersionUID =  5846246746223360453L;

	/**
	 * 自增id
	 */
   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 历史记录标识
	 */
   	@TableField(value = "info_no" )
	private String infoNo;

   	@TableField(value = "user_no" )
	private String userNo;

	/**
	 * 登录ip
	 */
   	@TableField(value = "login_ip" )
	private String loginIp;

	/**
	 * 登录地址
	 */
   	@TableField(value = "login_address" )
	private String loginAddress;

	/**
	 * 登陆时间
	 */
   	@TableField(value = "login_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date loginTime;

	/**
	 * 登录状态：1：成功 2：失败
	 */
   	@TableField(value = "login_state" )
	private Long loginState;

	/**
	 * 登陆设备
	 */
   	@TableField(value = "login_equi" )
	private String loginEqui;

	/**
	 * 在线状态
	 */
   	@TableField(value = "login_online" )
	private Long loginOnline;

	/**
	 * 登录信息
	 */
   	@TableField(value = "login_information" )
	private String loginInformation;

	/**
	 * 登陆账号
	 */
	@TableField(value = "login_account")
	private String loginAccount;

	/**
	 * 备注
	 */
	@TableField(value = "login_remark")
   	private String loginRemark;

	/**
	 * 登出时间
	 */
	@TableField(value = "logout_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date logoutTime;
}
