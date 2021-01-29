package com.imooc.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.imooc.project.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author geyaping
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "customer_id", type = IdType.AUTO)
    private Long customerId;

    /**
     * 真实名字
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 地址
     */
    private String address;


}
