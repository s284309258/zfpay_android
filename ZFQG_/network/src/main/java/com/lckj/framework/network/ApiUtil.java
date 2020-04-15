package com.lckj.framework.network;

public class ApiUtil {
    public static boolean isSuccessful(BaseResponse response){
        if(response != null && response.getCode() != null && response.getCode().equals(CBaseConst.API_CODE_SUCCESS) ){
            return true;
        }
        return false;
    }
}
