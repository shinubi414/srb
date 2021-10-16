package com.achao.srb.core.controller.admin;


import com.achao.common.exception.Assert;
import com.achao.common.result.ResponseEnum;
import com.achao.common.result.ResponseResult;
import com.achao.common.utils.RegexValidateUtils;
import com.achao.srb.base.utils.JwtUtils;
import com.achao.srb.core.pojo.entity.UserInfo;
import com.achao.srb.core.pojo.query.UserInfoQuery;
import com.achao.srb.core.pojo.vo.LoginVO;
import com.achao.srb.core.pojo.vo.RegisterVO;
import com.achao.srb.core.pojo.vo.UserInfoVO;
import com.achao.srb.core.service.UserInfoService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Api(tags = "会员管理")
@RestController
@RequestMapping("/admin/core/userInfo")
@Slf4j
//@CrossOrigin
public class AdminUserInfoController {

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("获取会员分页列表")
    @GetMapping("/list/{page}/{limit}")
    public ResponseResult listPage(@ApiParam("当前页")@PathVariable Integer page, @ApiParam("当前页条目数")@PathVariable Integer limit, UserInfoQuery userInfoQuery){
        Page<UserInfo> pageParam = new Page<>();
        IPage<UserInfo> pageModel = userInfoService.listPage(pageParam,userInfoQuery);
        return ResponseResult.ok().data("pageModel",pageModel);
    }

    @ApiOperation("锁定和解锁")
    @PutMapping("/lock/{id}/{status}")
    public ResponseResult lock(
            @ApiParam(value = "用户id", required = true)
            @PathVariable("id") Long id,

            @ApiParam(value = "锁定状态（0：锁定 1：解锁）", required = true)
            @PathVariable("status") Integer status){

        userInfoService.lock(id, status);
        return ResponseResult.ok().message(status==1?"解锁成功":"锁定成功");
    }


}
