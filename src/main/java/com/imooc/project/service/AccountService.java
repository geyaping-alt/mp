package com.imooc.project.service;

import com.imooc.project.dto.LoginDTO;
import com.imooc.project.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author geyaping
 * @since 2021-01-28
 */
public interface AccountService extends IService<Account> {

    LoginDTO login(String userName, String password);
}
