package com.lckj.jycm.network;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SysVersionService {

    @POST("getNewVersionInfo")
    Observable<NewVersionInfoBean> getNewVersionInfo(@Body NewVersionInfoRequest request);
}
