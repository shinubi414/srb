package com.achao.srb.core.controller.admin;


import com.achao.common.exception.Assert;
import com.achao.common.result.ResponseEnum;
import com.achao.common.result.ResponseResult;
import com.achao.srb.core.pojo.entity.IntegralGrade;
import com.achao.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
@Api(tags = "积分等级管理")
//@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    IntegralGradeService integralGradeService;

    @ApiOperation("等级积分列表")
    @GetMapping("/list")
    public ResponseResult listAll(){
        List<IntegralGrade> list = integralGradeService.list();
        return ResponseResult.ok().data("list",list).message("获取列表成功");
    }

    @ApiOperation(value = "根据id删除记录",notes = "逻辑删除数据记录")
    @DeleteMapping("/remove/{id}")
    public ResponseResult removeById(@ApiParam(value = "数据id") @PathVariable Long id){
        boolean result = integralGradeService.removeById(id);
        if (result){
            return ResponseResult.ok().message("删除成功");
        }else {
            return ResponseResult.error().message("删除失败");
        }

    }

    @ApiOperation(value = "根据id查找积分等级信息")
    @GetMapping("/get/{id}")
    public ResponseResult getById(@ApiParam(value = "数据id") @PathVariable Long id){
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if (integralGrade != null){
            return ResponseResult.ok().data("record",integralGrade);
        }else {
            return ResponseResult.error().message("数据不存在");
        }

    }

    @ApiOperation("新增积分等级")
    @PostMapping("/add")
    public ResponseResult save(@ApiParam("积分等级对象")@RequestBody IntegralGrade integralGrade){

        Assert.notNull(integralGrade.getBorrowAmount(),ResponseEnum.BORROW_AMOUNT_NULL_ERROR);

        boolean result = integralGradeService.save(integralGrade);
        if (result){
            return ResponseResult.ok().message("保存成功");
        }else {
            return ResponseResult.error().message("保存失败");
        }
    }

    @ApiOperation("更新积分等级")
    @PutMapping("/update")
    public ResponseResult updateById(@ApiParam("积分等级对象")@RequestBody IntegralGrade integralGrade){
        boolean result = integralGradeService.updateById(integralGrade);
        if (result){
            return ResponseResult.ok().message("更新成功");
        }else {
            return ResponseResult.error().message("更新失败");
        }
    }


}

