package com.example.codingtest_fauzanabdillah.features

import android.app.Application
import com.example.codingtest_fauzanabdillah.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // Inisialisasi Koin
        startKoin {
            androidContext(this@MainApp)
            modules(appModule)
        }
    }
}