package com.myECapplication.sajiiidali.ecsaver.kotlin.GoogleDriveBackup.app

import android.app.Application
import android.content.Context

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        fun getInstance(): Context {
            return instance
        }

        lateinit var instance: App
            private set
    }
}