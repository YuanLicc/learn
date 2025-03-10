package com.yl.learn.cloud.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order/")
@Slf4j
public class OrderController {

    @GetMapping("add")
    @LoadBalanced
    public String add(@RequestParam("id") Integer id, @RequestParam("name") String name) {
        String logText = String.format("添加订单: %d, name: %s", id, name);


        log.info(logText);
        return "success";
    }

}
