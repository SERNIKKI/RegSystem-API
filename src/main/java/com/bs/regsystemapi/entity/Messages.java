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
 * @date 2022-03-01 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="messages")
public class Messages implements Serializable {

	private static final long serialVersionUID =  8148320243932745257L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 消息no
	 */
	@TableField(value = "message_no")
	private String messageNo;

	/**
	 * 发送人no
	 */
   	@TableField(value = "actor_no" )
	private String actorNo;

	/**
	 * 接收人no
	 */
   	@TableField(value = "receiver_no" )
	private String receiverNo;

	/**
	 * 消息类型
	 */
   	@TableField(value = "type" )
	private String type;

	/**
	 * 消息内容
	 */
   	@TableField(value = "content" )
	private String content;

	/**
	 * 发送时间
	 */
   	@TableField(value = "send_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sendTime;

	/**
	 * 发送状态1：成功，0：失败
	 */
   	@TableField(value = "send_status" )
	private Long sendStatus;

	/**
	 * 是否已读，1：已读，0：未读
	 */
   	@TableField(value = "is_read" )
	private Long isRead;


	/**
	 * 文件地址
	 */
   	@TableField(value = "file_url" )
	private String fileUrl;

	/**
	 * 时长
	 */
   	@TableField(value = "duration" )
	private String duration;

	/**
	 * 文件名称
	 */
   	@TableField(value = "file_name" )
	private String fileName;

	/**
	 * 文件大小
	 */
   	@TableField(value = "file_size" )
	private String fileSize;

	/**
	 * 文件扩展名
	 */
   	@TableField(value = "file_ext" )
	private String fileExt;

	/**
	 * 是否已撤回，0：未撤回，1：已撤回
	 */
   	@TableField(value = "is_back" )
	private Long isBack;

	/**
	 * 图片宽度
	 */
	@TableField(value = "width")
	private String width;

	/**
	 * 图片高度
	 */
	@TableField(value = "height")
	private String height;

	/**
	 * 文件发送状态
	 */
	@TableField(value = "file_status")
	private String fileStatus;

	/**
	 * 文件开始上传时间
	 */
	@TableField(value = "file_upload_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date fileUploadTime;
}
