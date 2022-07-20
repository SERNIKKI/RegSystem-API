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
@TableName (value ="preview")
public class Preview implements Serializable {

	private static final long serialVersionUID =  8696897775111791043L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

   	@TableField(value = "preview_no" )
	private String previewNo;

	/**
	 * 预览图显示文字
	 */
   	@TableField(value = "preview_title" )
	private String previewTitle;

	/**
	 * 预览图链接
	 */
   	@TableField(value = "preview_link" )
	private String previewLink;

	/**
	 * 预览图图片
	 */
   	@TableField(value = "preview_image" )
	private String previewImage;

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

	/**
	 * 是否已删除
	 */
   	@TableField(value = "is_delete" )
	private String isDelete;

	/**
	 * 显示设备（pc/app）
	 */
   	@TableField(value = "show_model" )
	private String showModel;

	/**
	 * 是否有效（0：未开始，1：进行中，2：已过期）
	 */
   	@TableField(value = "is_valid" )
	private String isValid;

}
