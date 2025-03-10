package com.yl.learn.cloud.log.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log/")
@Slf4j
public class LogController {

    @GetMapping("add")
    public String add(@RequestParam("log") String logText) {
        log.info(String.format("写入日志：%s", logText));
        return "success";
    }

}
