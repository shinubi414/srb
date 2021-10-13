package com.achao.srb.core.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author achao
 * @since 2021-10-13
 */

@RestController
@RequestMapping("/web/integralGrade")
public class IntegralGradeController {

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }
}

