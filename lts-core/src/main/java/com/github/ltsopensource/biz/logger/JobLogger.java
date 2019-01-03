package com.github.ltsopensource.biz.logger;

import com.github.ltsopensource.biz.logger.domain.JobLogPo;
import com.github.ltsopensource.biz.logger.domain.JobLoggerRequest;
import com.github.ltsopensource.admin.response.PaginationRsp;

import java.util.List;

/**
 * 执行任务日志记录器
 *
 * @author Robert HG (254963746@qq.com) on 3/24/15.
 */
public interface JobLogger {

    void log(JobLogPo jobLogPo);

    void log(List<JobLogPo> jobLogPos);

    PaginationRsp<JobLogPo> search(JobLoggerRequest request);
}