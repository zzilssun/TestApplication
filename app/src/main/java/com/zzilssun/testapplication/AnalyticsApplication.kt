package com.zzilssun.testapplication

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class AnalyticsApplication : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}