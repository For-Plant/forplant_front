package com.example.forplant_front

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MyApplication : Application() {
    companion object {
        private lateinit var user: SharedPreferences

        fun getUser(): SharedPreferences {
            return user
        }

        fun initializeUser(context: Context) {
            user = context.getSharedPreferences("user", Context.MODE_PRIVATE)
        }
    }

    override fun onCreate() {
        super.onCreate()
        initializeUser(this)
    }
}