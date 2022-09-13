package uz.gita.contactauth.data.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import uz.gita.contactauth.data.remote.request.AuthRequest
import uz.gita.contactauth.data.remote.responce.AuthResponse
import uz.gita.contactauth.data.remote.responce.BaseResponse

interface AuthApi {

    @POST("register")
    suspend fun createAccount(
        @Body authRequest: AuthRequest
    ): Response<BaseResponse<AuthResponse>>

    @POST("unregister")
    suspend fun deleteAccount(
        @Body authRequest: AuthRequest
    ): Response<BaseResponse<AuthResponse>>

    @POST("login")
    suspend fun login(
        @Body authRequest: AuthRequest
    ): Response<BaseResponse<AuthResponse>>

    @POST("logout")
    suspend fun logout(
        @Body authRequest: AuthRequest
    ): Response<BaseResponse<AuthResponse>>
}