package uz.gita.contactauth.presentation.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.contactauth.data.local.model.AuthData
import uz.gita.contactauth.domain.auth.impl.AuthRepositoryImpl
import uz.gita.contactauth.presentation.RegisterScreenViewModel

class RegisterScreenViewModelImpl : RegisterScreenViewModel, ViewModel() {

    private val repository = AuthRepositoryImpl()
    override var loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    override val errorMessage: MutableLiveData<String> = repository.errorMessage()
    override val successLiveData: MutableLiveData<String> = repository.successMessage()
    override val contactSuccessLiveData: MutableLiveData<String> = MutableLiveData()

    override fun createAccount(authData: AuthData) {

        viewModelScope.launch(Dispatchers.IO) {
            if (authData.name.isNotEmpty() && authData.password.isNotEmpty()) {

                if (authData.password.length > 5 && repository.salom) {
                    repository.createAccount(authData)
                    successLiveData.postValue(repository.successMessage().toString())
                } else {
                    errorMessage.postValue(repository.errorMessage().value.toString())
                }
            } else {
                errorMessage.postValue("Maydonlar bo'sh")
            }
        }
    }
}