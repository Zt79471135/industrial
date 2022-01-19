package com.industrial.controller;

import com.industrial.service.AppOrderService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author zhu
 * @date 2022年01月19日 9:45
 */
@RestController
public class AppOrderController {
    @Resource
    private AppOrderService orderService;

}
