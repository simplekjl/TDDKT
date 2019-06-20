package com.simplekjl.tddkt.di

import android.app.Application
import com.simplekjl.tddkt.BuildConfig
import com.simplekjl.tddkt.data.CacheImpl
import com.simplekjl.tddkt.data.NetworkImpl
import com.simplekjl.tddkt.data.Repository
import com.simplekjl.tddkt.data.RepositoryImpl
import com.simplekjl.tddkt.network.NetworkService
import com.simplekjl.tddkt.viewModels.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TDDKTApp : Application() {

    val appModule = module {
        single<Retrofit> {
            Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        single<NetworkService> { get<Retrofit>().create(NetworkService::class.java) }
        single<Repository> { RepositoryImpl(CacheImpl(), NetworkImpl(get())) }
        viewModel { MainViewModel(get()) }
    }


    override fun onCreate() {
        super.onCreate()
        //start koin
        startKoin {
            androidLogger()
            androidContext(this@TDDKTApp)
            modules(listOf(appModule))
        }
    }
}