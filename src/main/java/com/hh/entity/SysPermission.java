package com.hh.entity;

import java.io.Serializable;

/**
 * Created by hh on 15-10-21.
 * 系统权限信息
 */
public class SysPermission implements Serializable{
    private Long id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型
     */
    private String type;

    /**
     *访问url地址
     */
    private String url;

    /**
     *权限代码字符串
     */
    private String perCode;

    /**
     *父结点id
     */
    private Long parentId;

    /**
     *父结点id列表串
     */
    private String parentIds;

    /**
     *排序号
     */
    private String sortString;

    /**
     *是否可用,1：可用，0不可用
     */
    private String available;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPerCode() {
        return perCode;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public String getSortString() {
        return sortString;
    }

    public void setSortString(String sortString) {
        this.sortString = sortString;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}
