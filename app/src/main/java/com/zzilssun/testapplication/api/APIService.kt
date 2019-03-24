package com.zzilssun.testapplication.api

import com.google.gson.JsonObject
import com.zzilssun.testapplication.models.OwnerModel
import com.zzilssun.testapplication.models.RepoModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET(APIConsts.PATH_USER)
    fun requestUser(@Path("userName") userName: String): Observable<OwnerModel>

    @GET(APIConsts.PATH_REPO)
    fun requestUserRepo(@Path("userName") userName: String): Observable<ArrayList<RepoModel>>
}