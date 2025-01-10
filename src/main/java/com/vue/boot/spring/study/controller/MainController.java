package com.vue.boot.spring.study.controller;

import com.vue.boot.spring.study.common.BaseResp;
import com.vue.boot.spring.study.exception.CustomException;
import com.vue.boot.spring.study.service.AccountService;
import com.vue.boot.spring.study.service.MainService;
import com.vue.boot.spring.study.util.ResultUtil;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.x.file.storage.core.FileInfo;
import org.dromara.x.file.storage.core.FileStorageService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/main/")
public class MainController {

    private final MainService mainService;

    private final FileStorageService fileStorageService;

    private final AccountService accountService;

    /**
     * 1.1.1.1.4.9.3设备列表
     * @return
     */
    @PostMapping("count")
    public BaseResp<?> count() {
        Map<String, Object> deviceList = mainService.count();
        return ResultUtil.ok(deviceList);
    }

    @PostMapping("upload")
    public BaseResp<?> uploadLogFile(@NotNull(message = "文件对象不能为空") MultipartFile file,
                                     @NotNull(message = "id不能为空") Long id) {
        String fileName = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + "." + id + file.getOriginalFilename();
        FileInfo fileInfo = fileStorageService.of(file)
                .setSaveFilename(fileName)
                .upload();
        if (fileInfo == null) {
            throw new CustomException(500, "文件上传失败");
        }
        //获取文件大小
        //Account account = new Account();
        //封装进实体类
//        account.setId(id);
//        account.setAvatar(fileInfo.getUrl());
//        accountService.setAvatar(account);
        return ResultUtil.ok(fileInfo.getUrl());
    }
}
