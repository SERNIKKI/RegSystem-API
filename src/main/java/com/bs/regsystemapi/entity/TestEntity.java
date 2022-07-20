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
 * @date 2022-01-28 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="test")
public class TestEntity implements Serializable {

	private static final long serialVersionUID =  1068386420115941983L;

	/**
	 * 自增id
	 */
   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 唯一标识
	 */
   	@TableField(value = "test_no" )
	private String testNo;

	/**
	 * 备注
	 */
   	@TableField(value = "test_remark" )
	private String testRemark;

	/**
	 * 创建人
	 */
   	@TableField(value = "create_name" )
	private String createName;

	/**
	 * 创建人id
	 */
   	@TableField(value = "create_id" )
	private Long createId;

	/**
	 * 创建时间
	 */
   	@TableField(value = "create_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 修改人
	 */
   	@TableField(value = "update_name" )
	private String updateName;

	/**
	 * 修改人id
	 */
   	@TableField(value = "update_id" )
	private Long updateId;

	/**
	 * 修改时间
	 */
   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

}
