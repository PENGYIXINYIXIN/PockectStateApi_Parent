package com.pockectstate.entity.user;

import java.util.Date;

public class UserSign {
    private Integer id;

    private Integer uid;

    private Integer days;

    private Date sdate;

    private Integer saward;

    private Integer sextraaward;

    private String sinfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Date getSdate() {
        return sdate;
    }

    public void setSdate(Date sdate) {
        this.sdate = sdate;
    }

    public Integer getSaward() {
        return saward;
    }

    public void setSaward(Integer saward) {
        this.saward = saward;
    }

    public Integer getSextraaward() {
        return sextraaward;
    }

    public void setSextraaward(Integer sextraaward) {
        this.sextraaward = sextraaward;
    }

    public String getSinfo() {
        return sinfo;
    }

    public void setSinfo(String sinfo) {
        this.sinfo = sinfo == null ? null : sinfo.trim();
    }
}