package com.achao.srb.core.controller.api;


import com.achao.common.result.ResponseResult;
import com.achao.srb.base.utils.JwtUtils;
import com.achao.srb.core.pojo.vo.UserBindVO;
import com.achao.srb.core.service.UserBindService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户绑定表 前端控制器
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */

@Api("账户绑定")
@RestController
@RequestMapping("api/core/userBind")
@Slf4j
public class UserBindController {

    @Resource
    UserBindService userBindService;

    @ApiOperation("账户绑定提交数据")
    @PostMapping
    public ResponseResult bing(@RequestBody UserBindVO userBindVO, HttpServletRequest request){
        String token = request.getHeader("token");
        Long userId = JwtUtils.getUserId(token);

        String formStr =  userBindService.commitBindUser(userBindVO,userId);

        return ResponseResult.ok().data("formStr",formStr);

    }
}

