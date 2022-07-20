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
@TableName (value ="notify")
public class Notify implements Serializable {

	private static final long serialVersionUID =  1067904866838340617L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

   	@TableField(value = "notify_no" )
	private String notifyNo;

	/**
	 * 通知类型
	 */
   	@TableField(value = "notify_type" )
	private String notifyType;

	/**
	 * 标题
	 */
   	@TableField(value = "notify_title" )
	private String notifyTitle;

	/**
	 * 通知内容
	 */
   	@TableField(value = "notify_content" )
	private String notifyContent;

	/**
	 * 通知对象
	 */
   	@TableField(value = "notify_object" )
	private String notifyObject;

	/**
	 * 工单no
	 */
   	@TableField(value = "system_no" )
	private String systemNo;

	/**
	 * 是否已读
	 */
   	@TableField(value = "is_read" )
	private String isRead;

	/**
	 * 是否送达
	 */
   	@TableField(value = "is_send" )
	private String isSend;

	/**
	 * 送达时间
	 */
   	@TableField(value = "send_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendTime;

	/**
	 * 创建人
	 */
   	@TableField(value = "create_name" )
	private String createName;

	/**
	 * 是否被删除
	 */
   	@TableField(value = "is_delete" )
	private String isDelete;

}
