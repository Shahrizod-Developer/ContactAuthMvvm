package uz.gita.contactauth.data.server

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.contactauth.data.remote.api.AuthApi
import uz.gita.contactauth.data.remote.api.ContactApi


class RetrofitInstance {

    companion object {

        private val retrofit by lazy {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder().addInterceptor(logging).build()
            Retrofit.Builder()
                .baseUrl("https://c22a-94-141-76-59.eu.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val authApi: AuthApi by lazy { retrofit.create(AuthApi::class.java) }

        val contactApi: ContactApi by lazy { retrofit.create(ContactApi::class.java) }
    }
}