package com.bs.regsystemapi.modal.vo;

import com.bs.regsystemapi.entity.Actor;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author qpj
 * @date 2022/3/1 14:28
 */
@Data
public class MessageInfo<T, V> implements Serializable {

    /**
     * 发送者
     */
    private Actor<T> actor;

    /**
     * 接收者
     */
    private Actor<V> receiver;

    private String messageNo;

    private String actorNo;

    private String receiverNo;

    private String type;

    private String content;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    private Long sendStatus;

    private Long isRead;

    private String fileUrl;

    private String duration;

    private String fileName;

    private String fileSize;

    private String fileExt;

    private Long isBack;

    private int notRead;

    private String height;

    private String width;

    private String fileStatus;
}
