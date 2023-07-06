package com.reisdeveloper.githubapiconsumer

import android.app.Application
import com.reisdeveloper.data.di.dataModule
import com.reisdeveloper.githubapiconsumer.di.appModule
import com.reisdeveloper.lib.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext

class GitHubApiConsumerApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@GitHubApiConsumerApplication)
            modules(
                listOf(
                    appModule
                )
            )
        }
        GlobalContext.loadKoinModules(
            listOf(
                domainModule,
                dataModule
            )
        )

    }
}