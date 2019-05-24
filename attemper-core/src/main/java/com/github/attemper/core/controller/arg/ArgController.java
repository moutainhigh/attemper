package com.github.attemper.core.controller.arg;

import com.github.attemper.common.constant.APIPath;
import com.github.attemper.common.param.dispatch.arg.*;
import com.github.attemper.common.result.CommonResult;
import com.github.attemper.common.result.dispatch.arg.Arg;
import com.github.attemper.common.result.dispatch.datasource.DataSourceInfo;
import com.github.attemper.core.service.arg.ArgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api("Arg")
@RestController
public class ArgController {

    @Autowired
    private ArgService service;

    @ApiOperation("Add arg")
    @ApiImplicitParam(value = "ArgSaveParam", name = "param", dataType = "ArgSaveParam", required = true)
    @PostMapping(value = APIPath.ArgPath.ADD)
    public CommonResult<Arg> add(@RequestBody ArgSaveParam param) {
        return CommonResult.putResult(service.add(param));
    }

    @ApiOperation("Update arg")
    @ApiImplicitParam(value = "ArgSaveParam", name = "param", dataType = "ArgSaveParam", required = true)
    @PutMapping(value = APIPath.ArgPath.UPDATE)
    public CommonResult<Arg> update(@RequestBody ArgSaveParam param) {
        return CommonResult.putResult(service.update(param));
    }

    @ApiOperation("Get arg")
    @ApiImplicitParam(value = "ArgGetParam", name = "param", dataType = "ArgGetParam", required = true)
    @GetMapping(value = APIPath.ArgPath.GET)
    public CommonResult<Arg> get(ArgGetParam param) {
        return CommonResult.putResult(service.get(param));
    }

    @ApiOperation("List args")
    @ApiImplicitParam(value = "ArgListParam", name = "param", dataType = "ArgListParam", required = true)
    @GetMapping(APIPath.ArgPath.LIST)
    public CommonResult<Map<String, Object>> list(ArgListParam param) {
        return CommonResult.putResult(service.list(param));
    }

    @ApiOperation("Remove args")
    @ApiImplicitParam(value = "ArgRemoveParam", name = "param", dataType = "ArgRemoveParam", required = true)
    @DeleteMapping(value = APIPath.ArgPath.REMOVE)
    public CommonResult<Void> remove(@RequestBody ArgRemoveParam param) {
        return CommonResult.putResult(service.remove(param));
    }

    @ApiOperation("Get dataSource of arg")
    @ApiImplicitParam(value = "ArgGetParam", name = "param", dataType = "ArgGetParam", required = true)
    @GetMapping(APIPath.ArgPath.GET_DATASOURCE)
    public CommonResult<DataSourceInfo> getDatasource(ArgGetParam param) {
        return CommonResult.putResult(service.getDatasource(param));
    }

    @ApiOperation("Update dataSource of arg")
    @ApiImplicitParam(value = "ArgDatasourceSaveParam", name = "param", dataType = "ArgDatasourceSaveParam", required = true)
    @PutMapping(APIPath.ArgPath.UPDATE_DATASOURCE)
    public CommonResult<Void> updateArgDatasource(@RequestBody ArgDatasourceSaveParam param) {
        return CommonResult.putResult(service.updateArgDatasource(param));
    }
}
