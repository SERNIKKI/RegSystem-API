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
 * @date 2022-03-28 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="bulletin")
public class Bulletin  implements Serializable {

	private static final long serialVersionUID =  4086178253614708755L;

	/**
	 * 公告
	 */
   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 公告no
	 */
   	@TableField(value = "bulletin_no" )
	private String bulletinNo;

	/**
	 * 标题
	 */
   	@TableField(value = "bulletin_title" )
	private String bulletinTitle;

	/**
	 * 内容
	 */
   	@TableField(value = "bulletin_contact" )
	private String bulletinContact;

	/**
	 * 创建人
	 */
   	@TableField(value = "bulletin_name" )
	private String bulletinName;

	/**
	 * 开始时间
	 */
   	@TableField(value = "begin_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;

	/**
	 * 结束时间
	 */
   	@TableField(value = "end_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	/**
	 * 是否有效
	 */
   	@TableField(value = "is_valid" )
	private String isValid;

	/**
	 * 公告图片
	 */
   	@TableField(value = "bulletin_image" )
	private String bulletinImage;

	/**
	 * 是否被删除
	 */
   	@TableField(value = "is_delete" )
	private String isDelete;

	/**
	 * 更改人
	 */
   	@TableField(value = "update_name" )
	private String updateName;

	/**
	 * 更改时间
	 */
   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 发布部门
	 */
	@TableField(value = "bulletin_department")
   	private String bulletinDepartment;

}
