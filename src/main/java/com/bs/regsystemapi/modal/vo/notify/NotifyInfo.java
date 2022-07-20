package com.bs.regsystemapi.modal.vo.notify;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/29 15:22
 */
@Data
public class NotifyInfo implements Serializable {

    private String notifyNo;

    /**
     * 通知类型
     */
    private String notifyType;

    /**
     * 标题
     */
    private String notifyTitle;

    /**
     * 通知内容
     */
    private String notifyContent;

    /**
     * 已读数
     */
    private String readNum;

    /**
     * 送达数
     */
    private String sendNum;

    /**
     * 总数
     */
    private String total;

    /**
     * 发送时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    /**
     * 创建人
     */
    private String createName;

    private List<NotSend> notSends;

}

