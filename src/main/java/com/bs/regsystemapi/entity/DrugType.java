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
 * @date 2022-03-15 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="drug_type")
public class DrugType implements Serializable {

	private static final long serialVersionUID =  7668332884486238085L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 主分类
	 */
   	@TableField(value = "main_type" )
	private String mainType;

	/**
	 * 次分类
	 */
   	@TableField(value = "sub_type" )
	private String subType;

	/**
	 * 分类id
	 */
   	@TableField(value = "sub_id" )
	private String subId;

	/**
	 * 创建人
	 */
   	@TableField(value = "create_name" )
	private String createName;

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
	 * 修改时间
	 */
   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

}
