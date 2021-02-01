package com.imooc.project.query;

import lombok.Data;

@Data
public class AccountQuery {

    /**
     * 真实名字
     */
    private String realName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间范围
     */
    private String createrTimeRange;

    private Long page;

    private Long limit;
}
