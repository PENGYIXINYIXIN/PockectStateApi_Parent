package com.pockectstate.entity.goods;

import java.util.Date;

public class GoodsRepertory {
    private Integer id;

    private Integer gid;

    private String skuobj;

    private Integer repertory;

    private Date ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getSkuobj() {
        return skuobj;
    }

    public void setSkuobj(String skuobj) {
        this.skuobj = skuobj == null ? null : skuobj.trim();
    }

    public Integer getRepertory() {
        return repertory;
    }

    public void setRepertory(Integer repertory) {
        this.repertory = repertory;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}