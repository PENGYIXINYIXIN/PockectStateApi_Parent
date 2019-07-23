package com.pockectstate.entity.goods;

import java.util.Date;

public class GoodsMedia {
    private Integer id;

    private Integer gid;

    private Integer type;

    private Integer ssort;

    private String imgurl;

    private Integer flag;

    private Integer msort;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSsort() {
        return ssort;
    }

    public void setSsort(Integer ssort) {
        this.ssort = ssort;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl == null ? null : imgurl.trim();
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getMsort() {
        return msort;
    }

    public void setMsort(Integer msort) {
        this.msort = msort;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}