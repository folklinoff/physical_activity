package com.example.sportaandcharity

import android.app.Application
import android.os.StrictMode
import android.util.Log
import com.airbnb.lottie.BuildConfig
import com.example.sportaandcharity.localbase.CardsRepository
import com.example.sportaandcharity.localbase.MainDB
import com.example.sportaandcharity.localbase.PassPhrase
import com.example.sportaandcharity.localbase.PathsRepository
import com.example.sportaandcharity.network.MainApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import net.sqlcipher.database.SupportFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    val retrofit by lazy {
        Retrofit.Builder().client(client).baseUrl("https://api.op.citycard.goodgenius.ru/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
    val api by lazy { retrofit.create(MainApi::class.java) }


    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(Interceptor { chain ->
        val newRequest: Request = chain.request().newBuilder()
            //.addHeader("Authorization", "Bearer subescheator")
            .build()
        chain.proceed(newRequest)
    }).build()


    private val cardsDatabase by lazy {
        MainDB.getDatabase(
            applicationContext, SupportFactory(
                PassPhrase(applicationContext).getPassphrase()
            )
        )
    }


    val cardsRepository by lazy {
        CardsRepository(cardsDatabase.getDao())
    }.also { }

    val pathsRepository by lazy {
        PathsRepository(cardsDatabase.getDao(), api)
    }.also { }

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            )
        }

        super.onCreate()
    }
}
