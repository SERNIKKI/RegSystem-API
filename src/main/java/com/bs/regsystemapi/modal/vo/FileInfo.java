package com.bs.regsystemapi.modal.vo;

import lombok.Data;

/**
 * @author qpj
 * @date 2022/3/4 17:23
 */
@Data
public class FileInfo {
    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件全称
     */
    private String fileName;

    /**
     * 不含扩展名的文件名称
     */
    private String fileNameNotExt;
    /**
     * 文件token,文件的md5代码
     */
    private String fileToken;
    /**
     * 文件大小
     */
    private String fileSize;
    /**
     * 扩展名
     */
    private String fileExt;
    /**
     * 时长(音频类才会有)，单位为s
     */
    private String duration;
}
