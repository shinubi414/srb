package com.achao.srb.sms.service.impl;

import com.achao.common.exception.BusinessException;
import com.achao.common.result.ResponseEnum;
import com.achao.common.utils.RandomUtils;
import com.achao.srb.sms.service.SMSService;
import com.achao.srb.sms.utils.SMSProperties;
import com.cloopen.rest.sdk.BodyType;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class SMSServiceImpl implements SMSService {

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public void sendSMS(String phoneNum) {

        String serverIp = SMSProperties.SERVER_IP;
        //请求端口
        String serverPort = SMSProperties.SERVER_PORT;
        //主账号,登陆云通讯网站后,可在控制台首页看到开发者主账号ACCOUNT SID和主账号令牌AUTH TOKEN
        String accountSId = SMSProperties.ACCOUNT_SID;
        String accountToken = SMSProperties.ACCOUNT_TOKEN;
        //请使用管理控制台中已创建应用的APPID
        String appId = SMSProperties.APP_ID;
        CCPRestSmsSDK sdk = new CCPRestSmsSDK();
        sdk.init(serverIp, serverPort);
        sdk.setAccount(accountSId, accountToken);
        sdk.setAppId(appId);
        sdk.setBodyType(BodyType.Type_JSON);
        String to = phoneNum;
        String templateId= SMSProperties.TEMPLATE_ID;
        String code = RandomUtils.getFourBitRandom();
        String[] datas = {code,"10","变量3"};
        //String subAppend="1234";  //可选 扩展码，四位数字 0~9999
        //String reqId="fadfafas";  //可选 第三方自定义消息id，最大支持32位英文数字，同账号下同一自然天内不允许重复
        HashMap<String, Object> result = null;
        try{
            result = sdk.sendTemplateSMS(to,templateId,datas);
        }catch (Exception e){
            throw new BusinessException(ResponseEnum.CLOOPEN_SMS_ERROR,e);
        }
            //HashMap<String, Object> result = sdk.sendTemplateSMS(to,templateId,datas,subAppend,reqId);
            if("000000".equals(result.get("statusCode"))){
                redisTemplate.opsForValue().set("srb:sms:code:" + phoneNum, code, 10,TimeUnit.MINUTES);
            }else{
                //异常返回输出错误码和错误信息.
                //log.error("错误码=" + result.get("statusCode") +" 错误信息= "+result.get("statusMsg"));
                throw new BusinessException(String.valueOf(result.get("statusMsg")),ResponseEnum.CLOOPEN_SMS_ERROR.getCode());
            }


    }
}
