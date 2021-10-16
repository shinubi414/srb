package com.achao.srb.core.service;

import com.achao.srb.core.pojo.entity.UserLoginRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户登录记录表 服务类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
public interface UserLoginRecordService extends IService<UserLoginRecord> {

    void sendSMS(String phoneNum);
}
