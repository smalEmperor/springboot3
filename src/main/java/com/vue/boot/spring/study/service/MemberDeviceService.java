package com.vue.boot.spring.study.service;

import cn.hutool.json.JSONUtil;
import com.vue.boot.spring.study.common.PageResult;
import com.vue.boot.spring.study.domain.MemberDevice;
import com.vue.boot.spring.study.domain.dto.DeviceParam;
import com.vue.boot.spring.study.domain.vo.DeviceVo;
import com.vue.boot.spring.study.mapper.MemberDeviceMapper;
import com.vue.boot.spring.study.util.BeanCopierUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import io.github.linpeilie.Converter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static cn.hutool.core.text.CharSequenceUtil.isNotBlank;

/**
 *
 */
@Slf4j
@Service
public class MemberDeviceService {

    @Resource
    private MemberDeviceMapper memberDeviceMapper;

    @Autowired
    private Converter converter;

    public Map<String, Object> deviceList(DeviceParam deviceParam) {
        log.info("查询参数" + JSONUtil.toJsonStr(deviceParam));
        Page<MemberDevice> paginate = memberDeviceMapper.paginate(deviceParam.getCurrent(), deviceParam.getSize(),
                QueryWrapper.create()
                        .like(MemberDevice::getPersonNickName, deviceParam.getPersonNickName(), isNotBlank(deviceParam.getPersonNickName()))
                        .like(MemberDevice::getNetworkNumber,deviceParam.getNetworkNumber(), isNotBlank(deviceParam.getNetworkNumber()))
                        .like(MemberDevice::getSafeImei1, deviceParam.getSafeImei1(), isNotBlank(deviceParam.getSafeImei1()))
                        .isNotNull(MemberDevice::getSafeImei1)
                        .orderBy(MemberDevice::getId, false));
        List<MemberDevice> records = paginate.getRecords();
        List<DeviceVo> convert = converter.convert(records, DeviceVo.class);
        return PageResult.requestSuccessPage(paginate, convert);
    }

    public void deviceAdd(DeviceParam deviceParam) {
        log.info("新增参数" + JSONUtil.toJsonStr(deviceParam));
        MemberDevice memberDevice = converter.convert(deviceParam, MemberDevice.class);
        //MemberDevice memberDevice = BeanCopierUtil.copyBean(deviceParam, MemberDevice.class);
        memberDevice.setPersonStatus(0);
        memberDevice.setDeviceType("CET-AL00");
        memberDevice.setUpdateDate(LocalDateTime.now());
        memberDevice.setCreateDate(LocalDateTime.now());
        memberDeviceMapper.insertSelective(memberDevice);
    }

    public void deviceDelete(DeviceParam deviceParam) {
        memberDeviceMapper.deleteById(deviceParam.getId());
    }

    public void deviceUpdate(DeviceParam deviceParam) {
        MemberDevice memberDevice = BeanCopierUtil.copyBean(deviceParam, MemberDevice.class);
        memberDeviceMapper.update(memberDevice);
    }

    public MemberDevice deviceById(Integer id) {
        return memberDeviceMapper.selectOneById(id);
    }
}




