package com.gata.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class TestController {


    /**
     * 服务状态显示
     *
     * @return
     */
    @GetMapping("/echo")
    public String echo() {
        return "succ";
    }
}
