package uz.gita.contactauth.presentation

import androidx.lifecycle.LiveData
import uz.gita.contactauth.data.local.model.AuthData

interface RegisterScreenViewModel {

    val loadingLiveData: LiveData<Boolean>
    val errorMessage: LiveData<String>
    val successLiveData: LiveData<String>
    val contactSuccessLiveData: LiveData<String>

    fun createAccount(authData: AuthData)
}