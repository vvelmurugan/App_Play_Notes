package com.example.app_play

import android.app.Application

/**
 * Created by vel-4009 on 2019-09-30.
 *
 *  Love your Job
 *
 */

class BaseApplication : Application() {

    companion object
    {
        private var sInstance: BaseApplication? = null
        fun getInstance(): BaseApplication {
            return sInstance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        sInstance = this

    }
}