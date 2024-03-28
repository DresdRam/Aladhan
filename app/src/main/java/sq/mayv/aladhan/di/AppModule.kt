package sq.mayv.aladhan.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sq.mayv.aladhan.network.AladhanApi
import sq.mayv.aladhan.repository.PrayersRepository
import sq.mayv.aladhan.util.PreferenceHelper
import sq.mayv.aladhan.util.PreferenceHelper.baseUrl
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun providePrayersRepository(api: AladhanApi) = PrayersRepository(api)

    @Provides
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences {
        return PreferenceHelper.getPreference(context)
    }

    @Singleton
    @Provides
    fun provideApi(preferences: SharedPreferences): AladhanApi {

        val baseUrl = preferences.baseUrl

        val client = OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(AladhanApi::class.java)
    }

}