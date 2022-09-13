package uz.gita.contactauth.data.local.model

import uz.gita.contactauth.data.remote.request.AuthRequest

data class AuthData(
    val name: String,
    val password:String
)
fun AuthData.toRequest() = AuthRequest(name, password)