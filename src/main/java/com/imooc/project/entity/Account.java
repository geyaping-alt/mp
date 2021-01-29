package com.imooc.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.imooc.project.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 账号表
 * </p>
 *
 * @author geyaping
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "account_id", type = IdType.AUTO)
    private Long accountId;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐密
     */
    private String salt;

    /**
     * 真实名字
     */
    private String realName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 邮箱
     */
    private String email;


}
