package com.pockectstate.api.common.psenum;

/**
 * @author:Yixi
 * @date:2019/7/10
 * 标记的是消息项目的操作日志的类型
 *
 */
public enum  MsgLogType {
    validatecode(1),getbackpassword(2);
    private int index;

    public int getIndex() {
        return index;
    }

    private  MsgLogType(int index) {
        this.index = index;
    }

}
