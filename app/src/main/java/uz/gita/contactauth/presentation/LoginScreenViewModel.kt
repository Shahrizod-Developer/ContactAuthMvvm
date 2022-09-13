package uz.gita.contactauth.presentation

import androidx.lifecycle.LiveData
import uz.gita.contactauth.data.local.model.AuthData

interface LoginScreenViewModel {

    val loadingLiveData: LiveData<Boolean>
    val errorMessage: LiveData<String>
    val successLiveData: LiveData<String>

    fun login(authData: AuthData)
}