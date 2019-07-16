package com.pockectstate.api.pockectstateapi_serveruser.dao;


import com.pockectstate.entity.user.UserWallet;
import org.apache.ibatis.annotations.Param;

public interface UserWalletDao {
    int insert(UserWallet record);
    //更改豆豆
   int updatePsbean(@Param("uid") int uid,@Param("psbean") int psbean);
}