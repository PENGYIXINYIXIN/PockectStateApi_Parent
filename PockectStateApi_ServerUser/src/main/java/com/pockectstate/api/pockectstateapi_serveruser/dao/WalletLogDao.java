package com.pockectstate.api.pockectstateapi_serveruser.dao;


import com.pockectstate.entity.user.WalletLog;

public interface WalletLogDao {
    int insert(WalletLog record);

    int insertSelective(WalletLog record);
}