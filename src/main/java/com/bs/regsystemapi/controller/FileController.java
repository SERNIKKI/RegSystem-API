package com.bs.regsystemapi.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.exception.ManageException;
import com.bs.regsystemapi.modal.dto.file.DownloadForm;
import com.bs.regsystemapi.modal.vo.FileInfo;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.service.impl.FileService;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/4 17:09
 */

@RestController
@RequestMapping("/file")
@CrossOrigin
@Slf4j
@Api(value = "文件上传与下载接口")
public class FileController {
    //    @Value("${file.upload.url}")
    private static final String uploadFilePath =
            System.getProperty("user.dir").replaceAll("\\\\","/")
                    + "/src/main/resources/file";

    @Autowired
    private FileService fileService;
    @Autowired
    private LogService logService;

    @PostMapping("/upload")
    @ApiOperation(value = "文件上传，支持多文件上传")
    @ResponseBody
    public ResponseResult httpUpload(@RequestParam("file") MultipartFile[] files,
                                     HttpServletRequest request){
        List<FileInfo> fileInfos = fileService.upload(files, request);
        if(fileInfos.size() == 0) {
            return ResponseResult.error("上传失败，请重试");
        }
        return ResponseResult.ok().put(fileInfos);
    }

    @ApiOperation(value = "获取上传进度")
    @GetMapping("/percent/{type}")
    public ResponseResult getUploadPercent(@PathVariable("type") String type) {
        SaSession session = StpUtil.getSession();
        int percent = session.get("upload_percent") == null ? 0:  (Integer)session.get("upload_percent");
        return ResponseResult.ok().put(percent);
    }

    @ApiOperation(value = "重置进度")
    @GetMapping("/percent/reset/{type}")
    public ResponseResult resetPercent(@PathVariable("type") String type) {
        StpUtil.getSession().set("upload_percent", 0);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "下载文件")
    @PostMapping(value = "/download")
    @ResponseBody
    public ResponseResult fileDownLoad(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String fileName1 = request.getParameter("fileName");
        if (Strings.isBlank(fileName1)) {
            throw new ManageException("Download File Name Is Not Null");
        }
        String folderName = request.getParameter("folderName");
        if (Strings.isBlank(folderName)) {
            throw new ManageException("Download Folder Name Is Not Null");
        }
        String fileName = "file/";
        if(!StringUtils.isEmpty(folderName)) {
            fileName += folderName + "/";
        }
        fileName += fileName1;
        response.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName1,"utf-8"));
        fileService.downloadFile(response.getOutputStream(),fileName);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "删除文件")
    @PostMapping(value = "/delete")
    public ResponseResult deleteFile(@RequestBody List<DownloadForm> forms) {
        List<String> keys = new ArrayList<>();
        for(DownloadForm form : forms) {
            if (StringUtils.isEmpty(form.getFileName())) {
                return ResponseResult.error("文件名字不能为空！");
            }
            String fileName = "file";
            if (!StringUtils.isEmpty(form.getFolderName())) {
                fileName += "/" + form.getFolderName();
            }
            fileName += "/" + form.getFileName();
            keys.add(fileName);
        }
        fileService.deleteFile(keys);
        return ResponseResult.ok();
    }

}
