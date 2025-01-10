package com.vue.boot.spring.study.controller;

import com.vue.boot.spring.study.common.BaseResp;
import com.vue.boot.spring.study.domain.dto.PushLogParams;
import com.vue.boot.spring.study.service.PushLogRecordService;
import com.vue.boot.spring.study.util.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/log/")
public class LogController {

    private final PushLogRecordService pushLogRecordService;

    /**
     * 1.1.1.1.4.9.3设备列表
     * @return
     */
    @PostMapping("list")
    public BaseResp<?> logList(@RequestBody PushLogParams pushLogParams) {
        Map<String, Object> deviceList = pushLogRecordService.logList(pushLogParams);
        return ResultUtil.ok(deviceList);
    }

    /**
     * 1.1.1.1.4.9.3设备新增
     * @return
     */
    @PostMapping("add")
    public BaseResp<?> logAdd(@Validated(PushLogParams.LogAdd.class) @RequestBody PushLogParams pushLogParams) {
        pushLogRecordService.logAdd(pushLogParams);
        return ResultUtil.ok();
    }

    /**
     * 1.1.1.1.4.9.3设备删除
     * @return
     */
    @PostMapping("delete")
    public BaseResp<?> logDelete(@Validated(PushLogParams.LogDelete.class) @RequestBody PushLogParams pushLogParams) {
        pushLogRecordService.logDelete(pushLogParams);
        return ResultUtil.ok();
    }

    /**
     * 1.1.1.1.4.9.3设备删除
     * @return
     */
    @PostMapping("update")
    public BaseResp<?> logUpdate(@Validated(PushLogParams.LogUpdate.class) @RequestBody PushLogParams pushLogParams) {
        pushLogRecordService.logUpdate(pushLogParams);
        return ResultUtil.ok();
    }

}
