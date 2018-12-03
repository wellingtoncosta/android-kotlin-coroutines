package br.com.wellingtoncosta.coroutines.di

import br.com.wellingtoncosta.coroutines.BuildConfig
import br.com.wellingtoncosta.coroutines.data.remote.api.GithubApi
import br.com.wellingtoncosta.coroutines.data.UserDataRepository
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Wellington Costa on 03/12/18
 */

/********************** REMOTE **********************/

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<GithubApi>(get()) }
}

const val API_URL = "https://api.github.com"
const val CONNECTION_TIMEOUT = 60000L

private fun provideOkHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)

    if(BuildConfig.DEBUG) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)
    }

    return client.build()
}

private fun createMoshi() = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private fun provideRetrofit(okHttpClient: OkHttpClient) = Retrofit.Builder()
        .baseUrl(API_URL)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(MoshiConverterFactory.create(createMoshi()))
        .client(okHttpClient)
        .build()

private inline fun <reified T> createApi(retrofit: Retrofit) = retrofit.create(T::class.java)

/********************** REPOSITORY **********************/

val repositoryModule = module {
    single { UserDataRepository(get()) as UserRepository }
}