package com.bs.regsystemapi.utils;

import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaInfo;
import ws.schild.jave.MultimediaObject;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author qpj
 * @date 2022/3/7 17:16
 * @des 文件工具类
 */
public class FileUtils {
    private static final String[] videoExt = {"mp4","m4v","mov","qt","avi","flv","wmv","asf","mpeg","mpg","vob","mkv","rm","rmvb","vob","ts","dat"};
    private static final String[] audioExt = {"wav","mp3","wma","ra","mid","ogg","ape","flac","aac","vqf"};

    /**
     * 获取文件扩展名
     *
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot >-1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 获取文件大小
     * @param size
     * @return
     */
    public static String getPrintSize(long size) {
        // 如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        double value = (double) size;
        if (value < 1024) {
            return String.valueOf(value) + "B";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        // 如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        // 因为还没有到达要使用另一个单位的时候
        // 接下去以此类推
        if (value < 1024) {
            return String.valueOf(value) + "KB";
        } else {
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        }
        if (value < 1024) {
            return String.valueOf(value) + "MB";
        } else {
            // 否则如果要以GB为单位的，先除于1024再作同样的处理
            value = new BigDecimal(value / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
            return String.valueOf(value) + "GB";
        }
    }

    /**
     * 判断文件大小
     * @param len 文件长度
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
//        long len = file.length();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }

    /**
     * 获取视频、音配类文件的时长
     */
    public static long getDuration(File file, String fileName) throws EncoderException, IOException {
        // 获取扩展名
        String ext = getExtensionName(fileName);
        long videoTime = 0;
        // 为视频
        if(isVideo(ext) || isAudio(ext)) {
            MultimediaObject instance = new MultimediaObject(file);
            MultimediaInfo result = instance.getInfo();
            videoTime = result.getDuration() / 1000;
            return videoTime;
        }
        return videoTime;
    }

    /**
     * 是否为视频
     */
    public static boolean isVideo(String ext) {
        for(String s: videoExt) {
            if(s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否为音频
     */
    public static boolean isAudio(String ext) {
        for (String s : audioExt) {
            if(s.equals(ext)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 删除文件
     */
    public static boolean deleteFile(File file) {
        // 获取文件目录下的所有文件
        File[] files = file.listFiles();
        // 遍历
        if (files != null) {
            for(File file1 : files) {
                // 判断子目录是否存在子目录
                if(file1.isDirectory()) {
                    deleteFile(file1);
                } else {
                    file1.delete();
                }
            }
        }
        boolean delete = file.delete();
        return delete;
    }
}
