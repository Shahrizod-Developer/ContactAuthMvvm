package uz.gita.contactauth.presentation

import androidx.lifecycle.LiveData

interface SplashViewModel {

    val openMainScreenLiveData: LiveData<Unit>
    val openLoginScreenLiveData: LiveData<Unit>
    val loadingLiveData: LiveData<Boolean>


}