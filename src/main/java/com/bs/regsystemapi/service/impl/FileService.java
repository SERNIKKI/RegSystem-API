package com.bs.regsystemapi.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.model.*;
import com.bs.regsystemapi.configuration.AliyunConfig;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.listener.PutObjectProgressListener;
import com.bs.regsystemapi.modal.vo.FileInfo;
import com.bs.regsystemapi.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/8 10:48
 */
@Service("fileService")
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    // 将要创建的文件夹名字
    private static final String objectName = "file";
    // 存在本地时的临时文件夹
    private static final String uploadFilePath =
            System.getProperty("user.dir").replaceAll("\\\\","/")
            + "/src/main/resources/file";

    @Autowired
    private OSS ossClient;
    @Autowired
    private AliyunConfig aliyunConfig;

    /**
     * 文件上传
     */
    public List<FileInfo> upload(MultipartFile[] files, HttpServletRequest request) {
        List<FileInfo> fileInfos = new ArrayList<>();
        // 获取用户信息
        UserEntity user = new UserEntity();
        String filePath1 = "";
        if(StpUtil.isLogin()) {
            user = (UserEntity) StpUtil.getSession().get("user");
            // 设置文件夹名字
            String folderName = user.getUserNo();
            filePath1 = objectName + "/" + folderName;
        }
        for(MultipartFile file : files) {
            FileInfo fileInfo = new FileInfo();
            String fileName = file.getOriginalFilename();  // 文件名
            String filePath = filePath1 + "/" + fileName;
            // 上传至oss
            try {
                fileInfo.setFileName(fileName);
                fileInfo.setFileNameNotExt(FileUtils.getFileNameNoEx(fileName));
                fileInfo.setFilePath(this.aliyunConfig.getUrlPrefix() + filePath);
                fileInfo.setFileExt(FileUtils.getExtensionName(fileName));
                fileInfo.setFileSize(FileUtils.getPrintSize(file.getSize()));
                File file1 = new File(uploadFilePath + '/' + fileName);
                file.transferTo(file1);
                PutObjectResult putObjectResult = ossClient.putObject(new PutObjectRequest(aliyunConfig.getBucketName(),
                        filePath, file1).<PutObjectRequest>withProgressListener(new PutObjectProgressListener(StpUtil.getSession(), "upload")));
                fileInfo.setDuration(String.valueOf(FileUtils.getDuration(file1, fileName)));
                fileInfo.setFileToken(putObjectResult.getETag());
                fileInfos.add(fileInfo);
                boolean delete = file1.delete();
            } catch (Exception e) {
                logger.error("{1}", e);
            }
        }
        return fileInfos;
    }

    /**
     * 文件下载
     */
    public void downloadFile(OutputStream os, String fileName) throws IOException{
        // 下载文件的同时指定了进度条参数
        OSSObject object = ossClient.getObject(aliyunConfig.getBucketName(), fileName);
        // 读取文件内容
        BufferedInputStream in = new BufferedInputStream(object.getObjectContent());
        BufferedOutputStream out = new BufferedOutputStream(os);
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = in.read(buffer)) != -1) {
            out.write(buffer, 0, length);
        }
        if (out != null) {
            out.flush();
            out.close();
        }
        if (in != null) {
            in.close();
        }
    }

    /**
     * 文件删除
     */
    public void deleteFile(List<String> keys) {
        logger.warn("用户【{}】正在执行删除操作",StpUtil.isLogin() ? StpUtil.getLoginId() : "admin");
        DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(
                aliyunConfig.getBucketName()).withKeys(keys).withEncodingType("url"));
        List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
        try {
            for(String obj : deletedObjects) {
                String deleteObj =  URLDecoder.decode(obj, "UTF-8");
                logger.info("文件 {} 被用户【{}】删除成功",deleteObj,StpUtil.isLogin() ? StpUtil.getLoginId() : "admin");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
