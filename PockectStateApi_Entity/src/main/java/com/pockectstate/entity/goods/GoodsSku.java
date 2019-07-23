package com.pockectstate.entity.goods;

import java.util.Date;

public class GoodsSku {
    private Integer id;

    private Integer gid;

    private Integer vid;

    private Integer ssort;

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

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getSsort() {
        return ssort;
    }

    public void setSsort(Integer ssort) {
        this.ssort = ssort;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}