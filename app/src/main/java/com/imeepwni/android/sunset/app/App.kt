package com.imeepwni.android.sunset.app

import android.app.*
import com.orhanobut.logger.*

/**
 * Create by guofeng on 2017/7/20.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        app = this

        configLogger()
    }

    private fun configLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter() {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return true
            }
        })
    }

    companion object {
        private lateinit var app: App

        fun getInstance() = app
    }
}