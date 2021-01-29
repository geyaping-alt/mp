package com.imooc.project.service;

import com.imooc.project.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.project.vo.ResourceVO;

import java.util.List;

/**
 * <p>
 * 资源表 服务类
 * </p>
 *
 * @author geyaping
 * @since 2021-01-28
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 根据角色Id查询，该角色具有的权限
     * @param roleId
     * @return
     */
    List<ResourceVO> listResourceByRoleId(Long roleId);
}
