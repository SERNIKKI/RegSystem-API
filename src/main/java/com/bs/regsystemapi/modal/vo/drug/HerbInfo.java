package com.bs.regsystemapi.modal.vo.drug;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/18 18:00
 */
@Data
public class HerbInfo implements Serializable {

    private Long id;

    /**
     * 药材名称(通用名称)
     */
    private String herbName;

    /**
     * 汉语拼音
     */
    private String herbPinyin;

    /**
     * 作用类别
     */
    private String herbAction;

    /**
     * 古籍出处
     */
    private String herbOrigin;

    /**
     * 基元
     */
    private String herbFundamental;

    /**
     * 主要产地
     */
    private String herbPlace;

    /**
     * 采集
     */
    private String herbCollect;

    /**
     * 炮制
     */
    private String herbConcoct;

    /**
     * 贮藏
     */
    private String herbStorage;

    /**
     * 药性
     */
    private String herbProp;

    /**
     * 归经
     */
    private String herbReturn;

    /**
     * 应用
     */
    private String herbApply;

    /**
     * 功效
     */
    private String herbEfficacy;

    /**
     * 用法用量
     */
    private String herbUsage;

    /**
     * 古籍摘要
     */
    private String herbAbstract;

    /**
     * 化学成分
     */
    private String herbIngredient;

    /**
     * 创建日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 创建人
     */
    private String createName;

    /**
     * 更新日期
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateName;

    /**
     * 药材图片
     */
    private String herbImage;

    private String herbNo;

    private String herbAdverse;

    private String herbRemark;

    private String mainType;

    private String subType;

    private String herbIdent;
}
