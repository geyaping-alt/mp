package com.imooc.project.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.imooc.project.entity.Account;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.imooc.project.entity.Resource;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author geyaping
 * @since 2021-01-28
 */
public interface AccountMapper extends BaseMapper<Account> {

    /**
     * 分页查询账户
     * @param page
     * @param wrapper
     * @return
     */
    IPage<Account> accountPage(Page<Account> page,@Param(Constants.WRAPPER) Wrapper<Account> wrapper);

    /**
     * 根据AccountId查询账户信息
     * @param id
     * @return
     */
    Account selectAccountById(Long id);
}
