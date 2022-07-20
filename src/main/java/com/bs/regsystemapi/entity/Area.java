package com.bs.regsystemapi.entity;

import java.io.Serializable;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

/**
  * @author  qpj
 * @date 2022-04-24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="area")
public class Area implements Serializable {

	private static final long serialVersionUID =  7514925392675407130L;

   	@TableField(value = "AREA_ID" )
	private String areaId;

   	@TableField(value = "AREA_NAME" )
	private String areaName;

   	@TableField(value = "PARENT_ID" )
	private String parentId;

   	@TableField(value = "IS_DELETE" )
	private String isDelete;

   	@TableField(value = "CREATE_TIME" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

   	@TableField(value = "MODIFY_TIME" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifyTime;

}
