package com.imooc.project.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResultVO <T> {
    private long code;
    private String msg;
    private long count;
    private List<Object> data;
}
