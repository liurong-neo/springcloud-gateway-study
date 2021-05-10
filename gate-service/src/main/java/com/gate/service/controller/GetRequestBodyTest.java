package com.gate.service.controller;

import com.gate.service.dto.RequestBean;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;



@RestController
public class GetRequestBodyTest {


    //http://localhost:5501/requestBodyGet?test=asdfasdf

    @PostMapping("/requestBodyGet")
    public Object requestBodyGet(HttpServletRequest request, @RequestBody RequestBean requestBean,@RequestParam String test){
        Map<String,String> map = new HashMap<>();
        map.put("Id",requestBean.getId());
        map.put("name",requestBean.getName());
        map.put("content",requestBean.getContent());
        return map;
    }

}
