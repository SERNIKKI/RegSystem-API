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
@TableName (value ="department")
public class Department implements Serializable {

	private static final long serialVersionUID =  3456819670757811544L;

	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 一级科室
	 */
	@TableField(value = "department" )
	private String department;

	/**
	 * 二级科室
	 */
	@TableField(value = "second_department" )
	private String secondDepartment;

	/**
	 * 一级科室负责人
	 */
	@TableField(value = "first_person" )
	private String firstPerson;

	/**
	 * 一级科室电话
	 */
	@TableField(value = "first_tel" )
	private String firstTel;

	/**
	 * 一级科室地址
	 */
	@TableField(value = "first_address" )
	private String firstAddress;

	/**
	 * 二级科室id
	 */
	@TableField(value = "second_id" )
	private String secondId;

	/**
	 * 修改人
	 */
	@TableField(value = "update_name" )
	private String updateName;

	/**
	 * 修改时间
	 */
	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 二级科室负责人
	 */
	@TableField(value = "second_person" )
	private String secondPerson;

	/**
	 * 二级科室电话
	 */
	@TableField(value = "second_tel" )
	private String secondTel;

	/**
	 * 二级科室地址
	 */
	@TableField(value = "second_address" )
	private String secondAddress;

	/**
	 * 一级负责人no
	 */
	@TableField(value = "first_no" )
	private String firstNo;

	/**
	 * 二级负责人no
	 */
	@TableField(value = "second_no" )
	private String secondNo;

	@TableField(value = "is_delete")
	private String isDelete;

}
