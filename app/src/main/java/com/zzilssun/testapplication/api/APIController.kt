package com.zzilssun.testapplication.api

import com.zzilssun.testapplication.models.OwnerModel
import com.zzilssun.testapplication.models.RepoModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIController {
    fun requestUser(userName: String): Observable<OwnerModel> {
        return getService().requestUser(userName)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun requestUserRepo(userName: String): Observable<ArrayList<RepoModel>> {
        return getService().requestUserRepo(userName)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun getService(): APIService {
        return getRetrofitWithGsonConverter(okHttpClient).create(APIService::class.java)
    }

    private val okHttpClient: OkHttpClient
        get() {
            val okHttpBuilder = OkHttpClient().newBuilder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .addInterceptor {
                    it.proceed(it.request().newBuilder().addHeader("X-User-Agent", "mrtimemaker-mobile").build())
                }

            return okHttpBuilder.build()
        }

    /**
     * Gson Converter 를 사용하는 Retrofit 2 를 가져온다.
     *
     * @return Retrofit
     */
    private fun getRetrofitWithGsonConverter(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(APIConsts.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private object Holder {
        val INSTANCE = APIController()
    }

    companion object {
        val instance: APIController by lazy { Holder.INSTANCE }
    }
}