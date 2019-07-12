package com.pockectstate.api.common.psenum;

/**
 *@Author feri
 *@Date Created in 2019/7/9 16:15
 * 基于枚举 定义用户日志类型 1
 */
public enum LogType {
    register(1),login(2),findpassword(3),changeuserdetail(4);

    private int value;
    private LogType(int value){
        this.value=value;
    }

    public int getValue() {
        return value;
    }
}
