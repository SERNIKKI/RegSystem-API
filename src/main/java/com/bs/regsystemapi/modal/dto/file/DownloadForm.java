package com.bs.regsystemapi.modal.dto.file;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qpj
 * @date 2022/3/7 22:18
 */
@Data
public class DownloadForm implements Serializable {
    private String fileName;

    private String folderName;
}
