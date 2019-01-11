package com.thor.core.controller;

import com.stark.sdk.common.result.CommonResult;
import com.thor.core.service.BaseJobService;
import com.thor.sdk.common.constant.ThorAPIConst;
import com.thor.sdk.common.constant.ThorAPIPath;
import com.thor.sdk.common.param.job.BaseJobListParam;
import com.thor.sdk.common.param.job.BaseJobRemoveParam;
import com.thor.sdk.common.param.job.BaseJobSaveParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** 
 * @auth ldang
 */
@Api(tags = ThorAPIConst.Tag.JOB)
@RestController
public class BaseJobController {
	
	@Autowired
	private BaseJobService service;

	@ApiOperation(ThorAPIConst.Operation.Job.Base.ADD)
	@ApiImplicitParam(value = "被新增的数据", name = "saveParam", dataType = "BaseJobSaveParam", required = true)
	@PostMapping(ThorAPIPath.Job.Base.ADD)
	public CommonResult add(@RequestBody BaseJobSaveParam saveParam) {
		return CommonResult.putResult(service.add(saveParam));
	}

	@ApiOperation(ThorAPIConst.Operation.Job.Base.UPDATE)
	@ApiImplicitParam(value = "被更新的数据", name = "saveParam", dataType = "BaseJobSaveParam", required = true)
	@PutMapping(ThorAPIPath.Job.Base.UPDATE)
	public CommonResult update(@RequestBody BaseJobSaveParam saveParam) {
		return CommonResult.putResult(service.update(saveParam));
	}

	@ApiOperation(ThorAPIConst.Operation.Job.Base.LIST)
	@ApiImplicitParam(value = "查询参数", name = "listParam", dataType = "BaseJobListParam", required = true)
	@GetMapping(ThorAPIPath.Job.Base.LIST)
	public CommonResult list(BaseJobListParam listParam) {
		return CommonResult.putResult(service.list(listParam));
	}

	@ApiOperation(ThorAPIConst.Operation.Job.Base.REMOVE)
	@ApiImplicitParam(value = "被删除的主键数组", name = "removeParam", dataType = "BaseJobRemoveParam", required = true)
	@DeleteMapping(ThorAPIPath.Job.Base.REMOVE)
	public CommonResult remove(@RequestBody BaseJobRemoveParam removeParam) {
        service.remove(removeParam);
		return CommonResult.ok();
	}
	/**
	@ApiOperation(StarkAPIConst.Operation.Tenant.GET)
    @ApiImplicitParam(value = "查询单个对象信息", name = "getParam", dataType = "TenantGetParam", required = true)
	@GetMapping(StarkAPIPath.Tenant.GET)
	public CommonResult get(TenantGetParam getParam) {
		return CommonResult.putResult(service.get(getParam));
    }*/

}