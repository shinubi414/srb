package com.achao.srb.core.service;

import com.achao.srb.core.pojo.entity.UserInfo;
import com.achao.srb.core.pojo.query.UserInfoQuery;
import com.achao.srb.core.pojo.vo.LoginVO;
import com.achao.srb.core.pojo.vo.RegisterVO;
import com.achao.srb.core.pojo.vo.UserInfoVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户基本信息 服务类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
public interface UserInfoService extends IService<UserInfo> {

    void register(RegisterVO registerVO);

    UserInfoVO login(LoginVO loginVO, String ip);


    IPage<UserInfo> listPage(Page<UserInfo> pageParam, UserInfoQuery userInfoQuery);

    void lock(Long id, Integer status);

    boolean checkMobile(String mobile);
}
