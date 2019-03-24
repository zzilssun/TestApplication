package com.zzilssun.testapplication.api

object APIConsts {
    const val BASE_URL = "https://api.github.com"

    // USER
    const val PATH_USER = "/users/{userName}"

    // REPO
    const val PATH_REPO = "$PATH_USER/repos"
}