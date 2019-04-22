package com.sse.attemper.core.dao.mapper.monitor;

import com.sse.attemper.common.base.BaseMapper;
import com.sse.attemper.common.result.dispatch.monitor.JobInstance;
import com.sse.attemper.common.result.dispatch.monitor.JobInstanceAct;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface JobExecutionMapper extends BaseMapper<JobInstance> {

    void delete(JobInstance jobInstance);

    void deleteAct(JobInstanceAct jobInstanceAct);

    List<JobInstance> list(Map<String, Object> paramMap);
}
