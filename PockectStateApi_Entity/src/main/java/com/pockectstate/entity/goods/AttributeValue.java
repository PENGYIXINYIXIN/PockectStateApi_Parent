package com.pockectstate.entity.goods;

import java.util.Date;

public class AttributeValue {
    private Integer id;

    private Integer kid;

    private String kvalue;

    private Date ctime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getKid() {
        return kid;
    }

    public void setKid(Integer kid) {
        this.kid = kid;
    }

    public String getKvalue() {
        return kvalue;
    }

    public void setKvalue(String kvalue) {
        this.kvalue = kvalue == null ? null : kvalue.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}