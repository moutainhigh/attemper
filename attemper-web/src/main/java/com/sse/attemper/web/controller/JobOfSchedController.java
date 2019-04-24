package com.sse.attemper.web.controller;

import com.sse.attemper.common.constant.APIPath;
import com.sse.attemper.common.param.dispatch.job.*;
import com.sse.attemper.common.result.CommonResult;
import com.sse.attemper.common.result.dispatch.job.FlowJob;
import com.sse.attemper.web.service.job.JobOfSchedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ldang
 */
@Api("Job")
@RestController
public class JobOfSchedController {

    @Autowired
    private JobOfSchedService service;

    @ApiOperation("Add job")
    @ApiImplicitParam(value = "JobSaveParam", name = "param", dataType = "JobSaveParam", required = true)
    @PostMapping(APIPath.JobPath.ADD)
    public CommonResult<FlowJob> add(@RequestBody JobSaveParam param) {
        return CommonResult.putResult(service.add(param));
    }

    @ApiOperation("Update job")
    @ApiImplicitParam(value = "JobSaveParam", name = "param", dataType = "JobSaveParam", required = true)
    @PutMapping(APIPath.JobPath.UPDATE)
    public CommonResult<FlowJob> update(@RequestBody JobSaveParam param) {
        return CommonResult.putResult(service.update(param));
    }

    @ApiOperation("Remove jobs")
    @ApiImplicitParam(value = "JobNamesParam", name = "param", dataType = "JobNamesParam", required = true)
    @DeleteMapping(APIPath.JobPath.REMOVE)
    public CommonResult<Void> remove(@RequestBody JobNamesParam param) {
        return CommonResult.putResult(service.remove(param));
    }

    @ApiOperation("Publish job to quartz and camunda")
    @ApiImplicitParam(value = "JobPublishParam", name = "param", dataType = "JobPublishParam", required = true)
    @PutMapping(APIPath.JobPath.PUBLISH)
    public CommonResult<Void> publish(@RequestBody JobPublishParam param) {
        return CommonResult.putResult(service.publish(param));
    }

    @ApiOperation("Copy job")
    @ApiImplicitParam(value = "JobCopyParam", name = "param", dataType = "JobCopyParam", required = true)
    @PutMapping(APIPath.JobPath.COPY)
    public CommonResult<FlowJob> copy(@RequestBody JobCopyParam param) {
        return CommonResult.putResult(service.copy(param));
    }

    @ApiOperation("Exchange current reversion to the latest reversion")
    @ApiImplicitParam(value = "JobGetParam", name = "param", dataType = "JobGetParam", required = true)
    @PutMapping(APIPath.JobPath.EXCHANGE)
    public CommonResult<FlowJob> exchange(@RequestBody JobGetParam param) {
        return CommonResult.putResult(service.exchange(param));
    }

    @ApiOperation("Manual start jobs")
    @ApiImplicitParam(value = "JobNamesParam", name = "param", dataType = "JobNamesParam", required = true)
    @PostMapping(APIPath.JobPath.MANUAL)
    public CommonResult<Void> manual(@RequestBody JobNamesParam param) {
        return CommonResult.putResult(service.manual(param));
    }
}