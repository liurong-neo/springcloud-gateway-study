package com.gate.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author brusion
 * @date 2019/8/2
 * @description:
 */
@RestController
public class ServiceController {

    @Autowired
    private ClientFeign clientFeign;

    @GetMapping("/index")
    public Object indexData(HttpServletRequest request, @RequestParam("pageNo") int pageNo){
        Map<String,String> map = new HashMap<>();
        String boxarea = request.getParameter("boxArea");
        map.put("data","index-data");
        map.put("code","0");
        map.put("msg","index-data");
        map.put("boxArea",boxarea);
        return map;
    }

    @GetMapping("/client/data")
    public Object clientData(){

        return clientFeign.index();
    }
}
