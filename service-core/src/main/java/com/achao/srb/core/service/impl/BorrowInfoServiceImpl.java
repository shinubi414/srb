package com.achao.srb.core.service.impl;

import com.achao.common.exception.Assert;
import com.achao.common.result.ResponseEnum;
import com.achao.srb.core.mapper.BorrowInfoMapper;
import com.achao.srb.core.mapper.IntegralGradeMapper;
import com.achao.srb.core.mapper.UserInfoMapper;
import com.achao.srb.core.pojo.entity.BorrowInfo;
import com.achao.srb.core.pojo.entity.IntegralGrade;
import com.achao.srb.core.pojo.entity.UserInfo;
import com.achao.srb.core.service.BorrowInfoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * <p>
 * 借款信息表 服务实现类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
@Service
public class BorrowInfoServiceImpl extends ServiceImpl<BorrowInfoMapper, BorrowInfo> implements BorrowInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private IntegralGradeMapper integralGradeMapper;

    @Override
    public BigDecimal getBorrowAmount(Long userId) {

        //获取用户积分
        UserInfo userInfo = userInfoMapper.selectById(userId);
        Assert.notNull(userInfo, ResponseEnum.LOGIN_MOBILE_ERROR);
        Integer integral = userInfo.getIntegral();

        //根据积分查询借款额度
        QueryWrapper<IntegralGrade> queryWrapper = new QueryWrapper<>();
        queryWrapper.le("integral_start", integral);
        queryWrapper.ge("integral_end", integral);
        IntegralGrade integralGradeConfig = integralGradeMapper.selectOne(queryWrapper);
        if(integralGradeConfig == null){
            return new BigDecimal("0");
        }
        return integralGradeConfig.getBorrowAmount();
    }
}
