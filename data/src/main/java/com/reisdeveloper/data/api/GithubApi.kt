package com.reisdeveloper.data.api

import com.reisdeveloper.data.model.UserDetailsResponse
import com.reisdeveloper.data.model.UserReposResponse
import com.reisdeveloper.data.model.UserResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int? = null
    ): List<UserResponse>

    @GET("/users/{user}")
    suspend fun getUserDetails(
        @Path("user") user: String
    ): UserDetailsResponse

    @GET("/users/{user}/repos")
    suspend fun getUserRepos(
        @Path("user") user: String
    ): List<UserReposResponse>


    companion object {
        private const val BASE_URL = "https://api.github.com"

        fun create(): GithubApi {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .addInterceptor(HeaderInterceptor)
                .build()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubApi::class.java)
        }
    }
}