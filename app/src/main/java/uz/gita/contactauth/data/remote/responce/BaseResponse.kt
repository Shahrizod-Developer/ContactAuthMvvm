package uz.gita.contactauth.data.remote.responce

data class BaseResponse<T>(
    val message: String,
    val data: T?
)
