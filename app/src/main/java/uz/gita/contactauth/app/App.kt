package uz.gita.contactauth.app

import android.app.Application
import uz.gita.contactauth.data.local.room.database.AppDatabase
import uz.gita.contactauth.data.local.shp.MySharedPreference
import uz.gita.contactauth.domain.auth.impl.AuthRepositoryImpl
import uz.gita.contactauth.domain.contact.impl.ContactRepositoryImpl

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        MySharedPreference.init(this)
        AppDatabase.init(this)
        AuthRepositoryImpl.init()
        ContactRepositoryImpl.init()

        instance = this
    }
}