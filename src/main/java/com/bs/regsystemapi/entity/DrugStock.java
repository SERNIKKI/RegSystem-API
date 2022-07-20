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
 * @date 2022-03-19 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName (value ="drug_stock")
public class DrugStock implements Serializable {

	private static final long serialVersionUID =  8730327374108563029L;

   	@TableField(value = "id" )
	@TableId
	private Long id;

	/**
	 * 药品no
	 */
   	@TableField(value = "drug_no" )
	private String drugNo;

	/**
	 * 药物类型
	 */
   	@TableField(value = "drug_type" )
	private String drugType;

	/**
	 * 一月卖出数
	 */
   	@TableField(value = "count_Jan" )
	private Long countJan;

	/**
	 * 二月
	 */
   	@TableField(value = "count_Feb" )
	private Long countFeb;

	/**
	 * 三月
	 */
   	@TableField(value = "count_Mar" )
	private Long countMar;

	/**
	 * 四月
	 */
   	@TableField(value = "count_Apr" )
	private Long countApr;

	/**
	 * 五月
	 */
   	@TableField(value = "count_May" )
	private Long countMay;

	/**
	 * 六月
	 */
   	@TableField(value = "count_Jun" )
	private Long countJun;

	/**
	 * 七月
	 */
   	@TableField(value = "count_Jul" )
	private Long countJul;

	/**
	 * 八月
	 */
   	@TableField(value = "count_Aug" )
	private Long countAug;

	/**
	 * 九月
	 */
   	@TableField(value = "count_Sep" )
	private Long countSep;

	/**
	 * 十月
	 */
   	@TableField(value = "count_Oct" )
	private Long countOct;

	/**
	 * 十一月
	 */
   	@TableField(value = "count_Nov" )
	private Long countNov;

	/**
	 * 十二月
	 */
   	@TableField(value = "count_Dec" )
	private Long countDec;

	/**
	 * 总数
	 */
   	@TableField(value = "count_total" )
	private Long countTotal;

	/**
	 * 售出数
	 */
   	@TableField(value = "count_sold" )
	private Long countSold;

	/**
	 * 上一次库存更改时间
	 */
   	@TableField(value = "update_time" )
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 修改人
	 */
   	@TableField(value = "update_name" )
	private String updateName;

}
