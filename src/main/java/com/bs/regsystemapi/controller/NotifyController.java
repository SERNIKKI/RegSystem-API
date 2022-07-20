package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Notify;
import com.bs.regsystemapi.entity.UserEntity;
import com.bs.regsystemapi.enumeration.common.Action;
import com.bs.regsystemapi.enumeration.common.Status;
import com.bs.regsystemapi.enumeration.common.Table;
import com.bs.regsystemapi.modal.dto.notify.QueryNotifyForm;
import com.bs.regsystemapi.modal.dto.notify.QueryUserNotifyForm;
import com.bs.regsystemapi.modal.dto.notify.SendNotifyForm;
import com.bs.regsystemapi.modal.vo.notify.NotSend;
import com.bs.regsystemapi.service.LogService;
import com.bs.regsystemapi.service.NotifyService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.NoGeneratorUtil;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:26
 */
@RestController
@RequestMapping("/notify")
@Api(value = "通知相关接口")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;
    @Autowired
    private LogService logService;

    @ApiOperation(value = "管理员获取通知列表")
    @PostMapping("/list")
    @Transactional
    public ResponseResult getNotifyList(@RequestBody QueryNotifyForm form) {
        ManagePageResult notifyList = notifyService.getNotifyList(form);
        return ResponseResult.ok().put(notifyList);
    }


    @ApiOperation(value = "获取没有发送成功的通知")
    @GetMapping("/notSendList/{notifyNo}")
    public ResponseResult getNotSendList(@PathVariable("notifyNo")String notifyNo) {
        if(StringUtils.isEmpty(notifyNo)) {
           return ResponseResult.error("通知no不能为空");
        }
        List<NotSend> notSendList = notifyService.getNotSendList(notifyNo);
        return ResponseResult.ok().put(notSendList);
    }

    @ApiOperation(value = "管理员删除通知")
    @GetMapping("/delete/{notifyNo}")
    public ResponseResult deleteNotify(@PathVariable("notifyNo")String notifyNo) {
        if(StringUtils.isEmpty(notifyNo)) {
            return ResponseResult.error("通知no不能为空");
        }
        Integer integer = notifyService.deleteNotify(notifyNo);
        if(integer > 0) {
            logService.addLog(Action.DELETE, Table.NOTIFY,notifyNo);
        } else {
            logService.addLog(Action.DELETE, Table.NOTIFY,notifyNo, Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "用户删除通知")
    @GetMapping("/deleteByUser/{systemNo}")
    public ResponseResult deleteNotifyByUser(@PathVariable("systemNo")String systemNo) {
        if(StringUtils.isEmpty(systemNo)) {
            return ResponseResult.error("no不能为空");
        }
        Integer integer = notifyService.deleteNotifyByUser(systemNo);
//        if(integer > 0) {
//            logService.addLog(Action.DELETE, Table.NOTIFY,systemNo);
//        } else {
//            logService.addLog(Action.DELETE, Table.NOTIFY,systemNo, Status.FAIL);
//        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "用户获取通知列表")
    @PostMapping("/listByUser")
    public ResponseResult getNotifyListForUser(@RequestBody QueryUserNotifyForm form) {
        ManagePageResult notifyListForUser = notifyService.getNotifyListForUser(form);
        return ResponseResult.ok().put(notifyListForUser);
    }

    @ApiOperation(value = "获取用户未读通知条数")
    @GetMapping("/count/{notifyObject}")
    public ResponseResult getCountNotRead(@PathVariable("notifyObject")String notifyObject) {
        if(StringUtils.isEmpty(notifyObject)) {
            return ResponseResult.error("用户no不能为空");
        }
        Integer countNotRead = notifyService.getCountNotRead(notifyObject);
        return ResponseResult.ok().put(countNotRead);
    }

    @ApiOperation(value = "用户已读")
    @GetMapping("/read/{systemNo}")
    public ResponseResult toRead(@PathVariable("systemNo")String systemNo) {
        if(StringUtils.isEmpty(systemNo)) {
            return ResponseResult.error("no不能为空");
        }
        notifyService.toRead(systemNo);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "一键已读")
    @GetMapping("/batchRead/{notifyObject}")
    public ResponseResult batchToRead(@PathVariable("notifyObject")String notifyObject) {
        if(StringUtils.isEmpty(notifyObject)) {
            return ResponseResult.error("用户no不能为空");
        }
        notifyService.batchToRead(notifyObject);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "发送通知")
    @PostMapping("/send")
    @Transactional
    public ResponseResult sendNotify(@RequestBody SendNotifyForm form) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败");
        }
        UserEntity user = (UserEntity)StpUtil.getSession().get("user");
        String userRealName = user.getUserRealName();
        List<String> notifyObjects = form.getNotifyObjects();
        List<Notify> notifies = new ArrayList<>();
        Notify formNotify = form.getNotify();
        String notifyNo = NoGeneratorUtil.getNo();
        System.out.println(notifyObjects);
        for(String userNo : notifyObjects) {
            Notify notify = new Notify();
            notify.setNotifyTitle(formNotify.getNotifyTitle());
            notify.setNotifyType(formNotify.getNotifyType());
            notify.setNotifyContent(formNotify.getNotifyContent());
            notify.setNotifyNo(notifyNo);
            notify.setSystemNo(NoGeneratorUtil.getNo());
            notify.setSendTime(new Date());
            notify.setCreateName(userRealName);
            notify.setNotifyObject(userNo);
            notifies.add(notify);
        }
        boolean b = notifyService.saveBatch(notifies);
        if(b) {
            logService.addLog(Action.BATCH_INSERT, Table.NOTIFY,notifyObjects.toString());
        } else {
            logService.addLog(Action.BATCH_INSERT, Table.NOTIFY,notifyObjects.toString(), Status.FAIL);
        }
        return ResponseResult.ok();
    }

    @ApiOperation(value = "获取通知详情")
    @GetMapping("/info/{systemNo}")
    @Transactional
    public ResponseResult getNotifyInfo(@PathVariable("systemNo") String systemNo) {
        if(StringUtils.isEmpty(systemNo)) {
            return ResponseResult.error("no不能为空");
        }
        Notify notifyInfo = notifyService.getNotifyInfo(systemNo);
        notifyService.toRead(systemNo);
        return ResponseResult.ok().put(notifyInfo);
    }

    @ApiOperation(value = "管理员获取通知详情")
    @GetMapping("/info/admin/{notifyNo}")
    public ResponseResult adminGetNotifyInfo(@PathVariable("notifyNo") String notifyNo) {
        if(StringUtils.isEmpty(notifyNo)) {
            return ResponseResult.error("no不能为空");
        }
        SendNotifyForm form = notifyService.adminGetNotifyInfo(notifyNo);
        return ResponseResult.ok().put(form);
    }
}
