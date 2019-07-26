package com.kotlin.user.data.api

import com.kotlin.base.data.protocol.BaseResp
import io.reactivex.Observable
import retrofit2.http.POST

/*
    上传相关 接口
 */
interface UploadApi {

    /*
        获取七牛云上传凭证
     */
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}
