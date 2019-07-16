package com.pockectstate.entity.user;

public class UserWallet {
    private Integer id;

    private Integer uid;

    private Integer psbean;

    private Integer commmoney;

    private Integer balamoney;

    private String no;

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

    public Integer getPsbean() {
        return psbean;
    }

    public void setPsbean(Integer psbean) {
        this.psbean = psbean;
    }

    public Integer getCommmoney() {
        return commmoney;
    }

    public void setCommmoney(Integer commmoney) {
        this.commmoney = commmoney;
    }

    public Integer getBalamoney() {
        return balamoney;
    }

    public void setBalamoney(Integer balamoney) {
        this.balamoney = balamoney;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no == null ? null : no.trim();
    }
}