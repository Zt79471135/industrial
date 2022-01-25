package com.industrial.domin;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.industrial.common.core.domain.entity.SysDept;
import com.industrial.domin.AppCategory;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;


/**
 * 描述:Treeselect树结构实体类
 * 日期：2022-01-25
 * 版权: Copyright (c) 2010-2017 广东合晟网络科技有限公司
 *
 * @author 合晟开发部-陈俊辉
 * @version industrial V1.0.0
 */
public class TreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<com.industrial.domin.TreeSelect> children;

    public TreeSelect()
    {

    }

    public TreeSelect(AppCategory dept)
    {
        this.id = dept.getId();
        this.label = dept.getCategoryName();
        this.children = dept.getChildren().stream().map(com.industrial.domin.TreeSelect::new).collect(Collectors.toList());
    }


    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public List<com.industrial.domin.TreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<com.industrial.domin.TreeSelect> children)
    {
        this.children = children;
    }
}
