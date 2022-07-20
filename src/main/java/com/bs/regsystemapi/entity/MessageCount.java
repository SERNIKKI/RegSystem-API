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
 * @date 2022-03-04 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="messagecount")
public class MessageCount implements Serializable {

	private static final long serialVersionUID =  3974976567786230442L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

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
	 * 发送总数
	 */
   	@TableField(value = "total" )
	private Long total;

	/**
	 * 未读消息数
	 */
   	@TableField(value = "not_read" )
	private Long notRead;

	/**
	 * 已读消息数
	 */
   	@TableField(value = "is_read" )
	private Long isRead;

	/**
	 * 发送成功数
	 */
   	@TableField(value = "is_success" )
	private Long isSuccess;

	/**
	 * 发送失败数
	 */
   	@TableField(value = "is_error" )
	private Long isError;

	/**
	 * 最后一次发送信息时间
	 */
   	@TableField(value = "last_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastTime;

	/**
	 * 备注
	 */
   	@TableField(value = "remark" )
	private String remark;

}
