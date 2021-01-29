package com.imooc.project.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 资源表
 * </p>
 *
 * @author geyaping
 * @since 2021-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Resource implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "resource_id", type = IdType.AUTO)
    private Long resourceId;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 真实名字
     */
    private String resourceName;

    /**
     * 资源类型（0、目录 1、菜单 2、按钮）
     */
    private Integer resourceType;

    /**
     * 链接
     */
    private String url;

    /**
     * 编码
     */
    private String code;

    /**
     * 排序
     */
    private Integer sort;


}
