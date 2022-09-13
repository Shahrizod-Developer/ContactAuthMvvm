package uz.gita.contactauth.presentation.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.contactauth.data.local.model.AuthStateEnum
import uz.gita.contactauth.domain.auth.impl.AuthRepositoryImpl
import uz.gita.contactauth.presentation.SplashViewModel

class SplashViewModelImpl : SplashViewModel, ViewModel() {
    private val authRepository = AuthRepositoryImpl()
    override val openMainScreenLiveData = MutableLiveData<Unit>()
    override val openLoginScreenLiveData = MutableLiveData<Unit>()
    override val loadingLiveData = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch {
            loadingLiveData.value = true
            val state = authRepository.checkState()
            delay(2000)
            loadingLiveData.value = false
            when (state) {
                AuthStateEnum.LOGIN -> {
                    openMainScreenLiveData.value = Unit
                }
                AuthStateEnum.LOG_UT -> {
                    openLoginScreenLiveData.value = Unit
                }
            }
        }
    }
}