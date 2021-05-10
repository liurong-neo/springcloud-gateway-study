package com.gate.service.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author brusion
 * @date 2019/8/2
 * @description:
 */
@Component
@FeignClient(name = "provider-client")
//@FeignClient(value = "provider-client1" ,url = "http://127.0.0.1:8080/")
public interface ClientFeign {
    String a = "testbyliurong";

    @GetMapping("/client")
//    @RequestMapping(method = RequestMethod.GET, value = "/client",headers = {"APP_ID="+a,"test=YYY"})
    public Object index();
}
