package com.gate.service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RequestBean implements Serializable {
    private String id;

    private String name;

    private String content;

    private String testStr;
}
