package com.vue.boot.spring.study.controller;

import com.vue.boot.spring.study.common.BaseResp;
import com.vue.boot.spring.study.domain.MemberDevice;
import com.vue.boot.spring.study.domain.dto.DeviceParam;
import com.vue.boot.spring.study.service.MemberDeviceService;
import com.vue.boot.spring.study.util.ResultUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/device/")
public class DeviceController {

    private final MemberDeviceService memberDeviceService;

    /**
     * 1.1.1.1.4.9.3设备列表
     * @return
     */
    @PostMapping("list")
    public BaseResp<?> deviceList(@RequestBody DeviceParam deviceParam) {
        Map<String, Object> deviceList = memberDeviceService.deviceList(deviceParam);
        return ResultUtil.ok(deviceList);
    }

    /**
     * 1.1.1.1.4.9.3设备列表
     * @return
     */
    @GetMapping("{id}")
    public BaseResp<?> deviceById(@PathVariable Integer id) {
        MemberDevice deviceList = memberDeviceService.deviceById(id);
        return ResultUtil.ok(deviceList);
    }

    /**
     * 1.1.1.1.4.9.3设备新增
     * @return
     */
    @PostMapping("add")
    public BaseResp<?> deviceAdd(@Validated(DeviceParam.DeviceAdd.class) @RequestBody DeviceParam deviceParam) {
        memberDeviceService.deviceAdd(deviceParam);
        return ResultUtil.ok();
    }

    /**
     * 1.1.1.1.4.9.3设备删除
     * @return
     */
    @PostMapping("delete")
    public BaseResp<?> deviceDelete(@Validated(DeviceParam.DeviceDelete.class) @RequestBody DeviceParam deviceParam) {
        memberDeviceService.deviceDelete(deviceParam);
        return ResultUtil.ok();
    }

    /**
     * 1.1.1.1.4.9.3设备删除
     * @return
     */
    @PostMapping("update")
    public BaseResp<?> deviceUpdate(@Validated(DeviceParam.DeviceDelete.class) @RequestBody DeviceParam deviceParam) {
        memberDeviceService.deviceUpdate(deviceParam);
        return ResultUtil.ok();
    }
}
