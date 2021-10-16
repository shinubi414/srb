package com.achao.srb.sms.controller.api;

import com.achao.common.exception.Assert;
import com.achao.common.result.ResponseEnum;
import com.achao.common.result.ResponseResult;
import com.achao.common.utils.RegexValidateUtils;
import com.achao.srb.sms.client.CoreUserInfoClient;
import com.achao.srb.sms.service.SMSService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
//@CrossOrigin
@RequestMapping("/api/sms")
@Api(tags = "短信管理")
public class SMSController {

    @Resource
    SMSService smsService;

    @Resource
    CoreUserInfoClient coreUserInfoClient;

    @ApiOperation("验证短信发送")
    @GetMapping("/send/{phoneNum}")
    public ResponseResult send(@ApiParam(value = "手机号",required = true)@PathVariable String phoneNum){

        //校验手机号码是否为空
        Assert.notNull(phoneNum, ResponseEnum.MOBILE_NULL_ERROR);

        //校验手机号码是否合法
        Assert.isTrue(RegexValidateUtils.checkCellphone(phoneNum), ResponseEnum.MOBILE_ERROR);

        //校验手机号是否已注册
        boolean result = coreUserInfoClient.checkMobile(phoneNum);
        Assert.isTrue(result == false,ResponseEnum.MOBILE_EXIST_ERROR);

        //发送验证码
        smsService.sendSMS(phoneNum);

        return ResponseResult.ok().message("短信发送成功");
    }

}
