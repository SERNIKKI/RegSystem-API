package com.bs.regsystemapi.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.bs.regsystemapi.entity.Preview;
import com.bs.regsystemapi.modal.dto.preview.QueryPreviewForm;
import com.bs.regsystemapi.service.PreviewService;
import com.bs.regsystemapi.utils.ManagePageResult;
import com.bs.regsystemapi.utils.ResponseResult;
import com.bs.regsystemapi.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qpj
 * @date 2022/3/28 22:29
 */
@RestController
@RequestMapping("/preview")
@Api(value = "首页轮播图相关接口")
public class PreviewController {

    @Autowired
    private PreviewService previewService;

    @ApiOperation(value = "获取轮播图列表")
    @PostMapping("/list")
    public ResponseResult getPreviewList(@RequestBody QueryPreviewForm form) {
        ManagePageResult previewList = previewService.getPreviewList(form);
        return ResponseResult.ok().put(previewList);
    }

    @ApiOperation(value = "获取pc端轮播图")
    @GetMapping("/pcList")
    public ResponseResult getPCPreview() {
        List<Preview> pcPreview = previewService.getPCPreview();
        return ResponseResult.ok().put(pcPreview);
    }

    @ApiOperation(value = "获取app端轮播图")
    @GetMapping("/appList")
    public ResponseResult getAPPPreview() {
        List<Preview> pcPreview = previewService.getAPPPreview();
        return ResponseResult.ok().put(pcPreview);
    }

    @ApiOperation(value = "获取轮播图详情")
    @GetMapping("/info/{previewNo}")
    public ResponseResult getPreviewInfo(@PathVariable("previewNo")String previewNo) {
        if(StringUtils.isEmpty(previewNo))  {
            return ResponseResult.error("轮播图的no不能为空！");
        }
        Preview previewInfo = previewService.getPreviewInfo(previewNo);
        return ResponseResult.ok().put(previewInfo);
    }

    @ApiOperation(value = "删除轮播图")
    @GetMapping("/delete/{previewNo}")
    public ResponseResult deletePreview(@PathVariable("previewNo")String previewNo) {
        if(StringUtils.isEmpty(previewNo))  {
            return ResponseResult.error("轮播图的no不能为空！");
        }
        Integer integer = previewService.deletePreview(previewNo);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "恢复轮播图")
    @GetMapping("/recover/{previewNo}")
    public ResponseResult recoverPreview(@PathVariable("previewNo")String previewNo) {
        if(StringUtils.isEmpty(previewNo))  {
            return ResponseResult.error("轮播图的no不能为空！");
        }
        Integer integer = previewService.recoverPreview(previewNo);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "新增轮播图")
    @PostMapping("/save")
    public ResponseResult savePreview(@RequestBody Preview preview) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        previewService.savePreview(preview);
        return ResponseResult.ok();
    }

    @ApiOperation(value = "更改轮播图")
    @PostMapping("/update")
    public ResponseResult updatePreview(@RequestBody Preview preview) {
        if(!StpUtil.isLogin()) {
            return ResponseResult.error("获取用户信息失败！");
        }
        if(StringUtils.isEmpty(preview.getPreviewNo())) {
            return ResponseResult.error("轮播图no不能为空！");
        }
        previewService.updatePreview(preview);
        return ResponseResult.ok();
    }
}
