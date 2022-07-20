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
 * @date 2022-02-09 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="user")
public class UserEntity implements Serializable {

	private static final long serialVersionUID =  3120108594964013309L;

	@TableField(value = "id" )
	@TableId
	private Long id;

	@TableField(value = "user_no" )
	private String userNo;

	/**
	 * 用户名
	 */
	@TableField(value = "user_name" )
	private String userName;

	/**
	 * 密码
	 */
	@TableField(value = "user_password" )
	private String userPassword;

	/**
	 * 创建时间
	 */
	@TableField(value = "create_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 电话号码
	 */
	@TableField(value = "user_tel" )
	private String userTel;

	/**
	 * 用户省份
	 */
	@TableField(value = "user_province" )
	private String userProvince;

	/**
	 * 住址
	 */
	@TableField(value = "user_address" )
	private String userAddress;

	/**
	 * 用户类型
	 */
	@TableField(value = "user_type" )
	private String userType;

	/**
	 * 用户权限
	 */
	@TableField(value = "user_permission" )
	private String userPermission;

	/**
	 * 所属科室
	 */
	@TableField(value = "user_department" )
	private String userDepartment;

	/**
	 * 性别
	 */
	@TableField(value = "user_sex" )
	private String userSex;

	/**
	 * 医生职位
	 */
	@TableField(value = "user_position" )
	private String userPosition;

	/**
	 * 出诊地点
	 */
	@TableField(value = "user_location" )
	private String userLocation;

	/**
	 * 医生擅长，以、间隔
	 */
	@TableField(value = "user_specialties" )
	private String userSpecialties;

	/**
	 * 头像地址
	 */
	@TableField(value = "user_avatar" )
	private String userAvatar;

	/**
	 * 邮箱
	 */
	@TableField(value = "user_email")
	private String userEmail;

	/**
	 * 登录信息no
	 */
	@TableField(exist = false)
	private String loginNo;

	/**
	 * 真实姓名
	 */
	@TableField(value = "user_real_name")
	private String userRealName;

	@TableField(value = "is_delete")
	private String isDelete;

	@TableField(value = "user_work_avatar")
	private String userWorkAvatar;

	@TableField(value = "reserve_price")
	private String reservePrice;
}
