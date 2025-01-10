package com.vue.boot.spring.study.service;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.vue.boot.spring.study.common.PageResult;
import com.vue.boot.spring.study.domain.PushLogRecord;
import com.vue.boot.spring.study.domain.dto.PushLogParams;
import com.vue.boot.spring.study.mapper.PushLogRecordMapper;
import com.vue.boot.spring.study.util.BeanCopierUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Map;

import static cn.hutool.core.text.CharSequenceUtil.isNotBlank;

/**
 *
 */
@Slf4j
@Service
public class PushLogRecordService {

    @Resource
    private PushLogRecordMapper pushLogRecordMapper;

    public Map<String, Object> logList(PushLogParams pushLogParams) {
        log.info("查询参数" + JSONUtil.toJsonStr(pushLogParams));
        Page<PushLogRecord> paginate = pushLogRecordMapper.paginate(pushLogParams.getCurrent(), pushLogParams.getSize(),
                QueryWrapper.create()
                .like(PushLogRecord::getImei, pushLogParams.getImei(), isNotBlank(pushLogParams.getImei()))
                .and(PushLogRecord::getPushStatus).eq(pushLogParams.getPushStatus(), ObjectUtil.isNotNull(pushLogParams.getPushStatus()))
                .ge(PushLogRecord::getRecordTime, pushLogParams.getStartDate(), ObjectUtil.isNotNull(pushLogParams.getStartDate()))
                .le(PushLogRecord::getRecordTime, pushLogParams.getEndDate(), ObjectUtil.isNotNull(pushLogParams.getEndDate()))
                .orderBy(PushLogRecord::getId, false));
        return PageResult.requestSuccessPage(paginate);
    }

    public void logAdd(PushLogParams pushLogParams) {
        LocalDateTime now = LocalDateTime.now();
        DecimalFormat df = new DecimalFormat("#.0");
        PushLogRecord pushLogRecord = BeanCopierUtil.copyBean(pushLogParams, PushLogRecord.class);
        pushLogRecord.setSuccessRate(Double.valueOf(df.format((double)pushLogParams.getSuccessCount() / pushLogParams.getPushCount())));
        pushLogRecord.setFailRate(Double.valueOf(df.format((double)pushLogParams.getFailCount() / pushLogParams.getPushCount())));
        pushLogRecord.setRecordTime(now);
        pushLogRecord.setRecordYear(now.getYear());
        pushLogRecord.setRecordMonth(now.getMonthValue());
        pushLogRecord.setRecordDay(now.getDayOfMonth());
        pushLogRecordMapper.insert(pushLogRecord);
    }

    public void logDelete(PushLogParams pushLogParams) {
        pushLogRecordMapper.deleteById(pushLogParams.getId());
    }

    public void logUpdate(PushLogParams pushLogParams) {
        DecimalFormat df = new DecimalFormat("#.0");
        PushLogRecord pushLogRecord = BeanCopierUtil.copyBean(pushLogParams, PushLogRecord.class);
        if (pushLogParams.getSuccessCount() != null && pushLogParams.getPushCount() != null) {
            pushLogRecord.setSuccessRate(Double.valueOf(df.format((double)pushLogParams.getSuccessCount() / pushLogParams.getPushCount())));
        }
        if (pushLogParams.getFailCount() != null && pushLogParams.getPushCount() != null) {
            pushLogRecord.setFailRate(Double.valueOf(df.format((double)pushLogParams.getFailCount() / pushLogParams.getPushCount())));
        }
        pushLogRecordMapper.update(pushLogRecord);
    }
}




