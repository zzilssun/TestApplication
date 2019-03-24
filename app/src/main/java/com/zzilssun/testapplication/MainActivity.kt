package com.zzilssun.testapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.zzilssun.testapplication.api.APIController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        APIController.instance.requestUser("jakewharton").subscribe {
            Glide.with(applicationContext).load(it.avatar_url).into(img_user)
            txt_username.text = it.login

            btn_repo.apply {
                text = "testapp://repo/jakewharton"
                setOnClickListener {
                    startActivity(Intent(Intent.ACTION_VIEW).apply {
                        data = Uri.parse("testapp://repo/jakewharton")
                    })
                }
            }
        }
    }
}
