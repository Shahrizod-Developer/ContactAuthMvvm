package uz.gita.contactauth.data.remote.api

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import uz.gita.contactauth.data.remote.request.ContactRequest
import uz.gita.contactauth.data.remote.responce.BaseResponse
import uz.gita.contactauth.data.remote.responce.ContactResponse

interface ContactApi {

    @GET("contact")
    fun getAllContacts(
        @Header("token") token: String
    ): Call<BaseResponse<List<ContactResponse>>>


    @POST("contact")
    suspend fun addContact(
        @Header("token") token: String,
        @Body request: ContactRequest
    ): Response<BaseResponse<ContactResponse>>

    @PUT("contact")
    suspend fun updateContact(
        @Header("token") token: String,
        @Query("id") id: Int,
        @Body request: ContactRequest
    ): Response<BaseResponse<ContactRequest>>

    @GET("contact")
    fun getContact(
        @Header("token") token: String,
        @Query("id") id: Int
    ): Response<BaseResponse<ContactResponse>>

    @DELETE("contact")
    suspend fun deleteContact(
        @Header("token") token: String,
        @Query("id") id: Int
    ): Response<BaseResponse<ContactResponse>>
}