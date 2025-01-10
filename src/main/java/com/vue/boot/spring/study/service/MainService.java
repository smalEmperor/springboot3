package com.vue.boot.spring.study.service;

import cn.hutool.core.util.RandomUtil;
import com.vue.boot.spring.study.domain.MemberDevice;
import com.vue.boot.spring.study.mapper.MemberDeviceMapper;
import com.vue.boot.spring.study.mapper.PushLogRecordMapper;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class MainService {

    @Resource
    private MemberDeviceMapper memberDeviceMapper;

    @Resource
    private PushLogRecordMapper pushLogRecordMapper;

    public Map<String, Object> count() {
        Map<String, Object> result = new HashMap<>(8);
        long deviceTotalCount = memberDeviceMapper.selectCountByQuery(QueryWrapper.create().isNotNull(MemberDevice::getSafeImei1));
        result.put("deviceTotalCount", deviceTotalCount);
        result.put("deviceOnlineCount", RandomUtil.randomLong(deviceTotalCount - 100, deviceTotalCount));
        long logTotalCount = pushLogRecordMapper.selectCountByQuery(QueryWrapper.create());
        result.put("logTotalCount", logTotalCount);
        result.put("logErrCount", RandomUtil.randomLong(1, 1000));
        return result;
    }
}
