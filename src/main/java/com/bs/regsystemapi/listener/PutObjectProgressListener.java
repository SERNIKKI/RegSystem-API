package com.bs.regsystemapi.listener;

import cn.dev33.satoken.session.SaSession;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * @author qpj
 * @date 2022/3/8 14:40
 */
public class PutObjectProgressListener implements ProgressListener {

    private static final Logger logger = LoggerFactory.getLogger(PutObjectProgressListener.class);

    private SaSession session;
    private long bytesWritten = 0;
    private long totalBytes = -1;
    private boolean succeed = false;
    private int percent = 0;

    //构造方法中加入session
    public PutObjectProgressListener() {
    }
    public PutObjectProgressListener(SaSession mSession, String type) {
        this.session = mSession;
        session.set("upload_percent", percent);
    }

    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                logger.info("Start to upload......");
                break;
            case REQUEST_CONTENT_LENGTH_EVENT:
                this.totalBytes = bytes;
                logger.info(this.totalBytes + " bytes in total will be uploaded to OSS");
                break;
            case REQUEST_BYTE_TRANSFER_EVENT:
                this.bytesWritten += bytes;
                if (this.totalBytes != -1) {
                    percent = (int) (this.bytesWritten * 100.0 / this.totalBytes);
                    //将进度percent放入session中
                    session.set("upload_percent", percent);
                    logger.info(bytes + " bytes have been written at this time, upload progress: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                } else {
                    logger.info(bytes + " bytes have been written at this time, upload ratio: unknown" + "(" + this.bytesWritten + "/...)");
                }
                break;
            case TRANSFER_COMPLETED_EVENT:
                this.succeed = true;
                logger.info("Succeed to upload, " + this.bytesWritten + " bytes have been transferred in total");
                break;
            case TRANSFER_FAILED_EVENT:
                logger.info("Failed to upload, " + this.bytesWritten + " bytes have been transferred");
                break;
            default:
                break;
        }
    }

    public boolean isSucceed() {
        return succeed;
    }
}
