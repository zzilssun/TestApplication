package com.zzilssun.testapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zzilssun.testapplication.adaters.RepoAdater
import com.zzilssun.testapplication.api.APIController
import kotlinx.android.synthetic.main.activity_repo.*

class RepoActivity : AppCompatActivity() {
    lateinit var userName: String
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        recycler_view.adapter = RepoAdater()

        userName = intent.data?.pathSegments!![0] ?: ""

        APIController.instance.requestUser(userName).subscribe {
            Glide.with(applicationContext).load(it.avatar_url).into(img_user)
            txt_username.text = it.login
        }

        APIController.instance.requestUserRepo(userName).subscribe { items ->
            (recycler_view.adapter as? RepoAdater)?.let {
                it.putItems(items)
            }
        }
    }
}