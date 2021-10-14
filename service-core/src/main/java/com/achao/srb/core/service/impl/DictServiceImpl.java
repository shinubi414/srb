package com.achao.srb.core.service.impl;

import com.achao.srb.core.listener.ExcelDictDTOListener;
import com.achao.srb.core.pojo.dto.ExcelDictDTO;
import com.achao.srb.core.pojo.entity.Dict;
import com.achao.srb.core.mapper.DictMapper;
import com.achao.srb.core.service.DictService;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */
@Service
@Slf4j
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    DictMapper dictMapper;

    @Resource
    RedisTemplate redisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(dictMapper)).sheet().doRead();
        log.info("Excel导入成功");
    }

    @Override
    public List<ExcelDictDTO> getDictData() {

        List<Dict> dicts = baseMapper.selectList(null);
        List<ExcelDictDTO> dictDTOs = new ArrayList<>(dicts.size());
        //装换成ExcelDictDTO
        dicts.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict, excelDictDTO);
            dictDTOs.add(excelDictDTO);
        });
        return dictDTOs;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {

        try{
            List<Dict> dictList = (List<Dict>) redisTemplate.opsForValue().get("srb:core:disList:" + parentId);
            if (dictList != null){
                log.info("从redis中取值");
                return dictList;
            }
        }catch (Exception e){
            log.error("redis服务器异常：" + ExceptionUtils.getStackTrace(e));
        }


        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        log.info("从数据库中取值");
        List<Dict> dicts = baseMapper.selectList(queryWrapper);
        dicts.forEach(dict -> {
            boolean flag = hasChildren(parentId);
            dict.setHasChildren(false);
        });
        try {
            redisTemplate.opsForValue().set("srb:core:disList:" + parentId, dicts,5, TimeUnit.MINUTES);
            log.info("redis中存入数据");
        }catch (Exception e){
            log.error("redis服务器异常：" + ExceptionUtils.getStackTrace(e));
        }


        return dicts;
    }

    //判断是否有子节点
    private boolean hasChildren(Long parentId){
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        Integer integer = baseMapper.selectCount(queryWrapper);
        if(integer.intValue() > 0){
            return true;
        }else {
            return false;
        }
    }
}
