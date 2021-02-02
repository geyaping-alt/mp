package com.imooc.project.service.impl;

import apple.laf.JRSUIUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.imooc.project.entity.Resource;
import com.imooc.project.dao.ResourceMapper;
import com.imooc.project.service.ResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.imooc.project.vo.ResourceVO;
import com.imooc.project.vo.TreeVO;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author geyaping
 * @since 2021-01-28
 */
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    public List<ResourceVO> listResourceByRoleId(Long roleId) {
        QueryWrapper<Resource> wrapper = Wrappers.query();
        wrapper.eq("rr.role_id",roleId).isNull("re.parent_id").orderByAsc("re.sort");
        List<ResourceVO> resourceVOS = baseMapper.listResource(wrapper);
        resourceVOS.forEach(r->{
            Long resourceId = r.getResourceId();
            QueryWrapper<Resource> subWrapper = Wrappers.query();
            subWrapper.eq("rr.role_id",roleId).eq("re.parent_id",resourceId).orderByAsc("re.sort");
            List<ResourceVO> subResourceVOS = baseMapper.listResource(subWrapper);
            if(!CollectionUtils.isEmpty(subResourceVOS)){
                r.setSubs(subResourceVOS);
            }
        });
        return resourceVOS;
    }

    /**
     * 查询系统资源，供前端组件渲染
     * @return
     */
    @Override
    public List<TreeVO> listResoruce(Long roleId, Integer flag) {
        if(roleId == null){
            LambdaQueryWrapper<Resource> wrapper = Wrappers.<Resource>lambdaQuery()
                    .isNull(Resource::getParentId).orderByAsc(Resource::getSort);
            List<Resource> resources = list(wrapper);

            List<TreeVO> treeVOS = resources.stream().map(r->{
                TreeVO treeVO = new TreeVO();
                treeVO.setId(r.getResourceId());
                treeVO.setTitle(r.getResourceName());

                LambdaQueryWrapper<Resource> subWrapper = Wrappers.<Resource>lambdaQuery()
                        .eq(Resource::getParentId,r.getResourceId()).orderByAsc(Resource::getSort);
                List<Resource> subResources = list(subWrapper);
                if(CollectionUtils.isNotEmpty(subResources)){
                    List<TreeVO> children = subResources.stream().map(sub->{
                        TreeVO subTreeVO = new TreeVO();
                        subTreeVO.setId(sub.getResourceId());
                        subTreeVO.setTitle(sub.getResourceName());
                        return subTreeVO;
                    }).collect(Collectors.toList());
                    treeVO.setChildren(children);
                }
                return treeVO;
            }).collect(Collectors.toList());
            return treeVOS;
        }else {
            QueryWrapper<Resource> wrapper = Wrappers.<Resource>query();
            wrapper.eq(flag == 1,"rr.role_id",roleId)
                    .isNull("re.parent_id").orderByAsc("re.sort");
            List<TreeVO> treeVOS = baseMapper.listResourceByRoleId(wrapper,roleId);
            treeVOS.forEach(t->{
                t.setChecked(false);
                Long id = t.getId();
                QueryWrapper<Resource> subWrapper = Wrappers.<Resource>query();
                subWrapper.eq(flag == 1,"rr.role_id",roleId)
                        .eq("re.parent_id",id).orderByAsc("re.sort");
                List<TreeVO> children = baseMapper.listResourceByRoleId(subWrapper,roleId);
                if(CollectionUtils.isNotEmpty(children)){
                    t.setChildren(children);
                }
            });
            return treeVOS;
        }
    }
}
