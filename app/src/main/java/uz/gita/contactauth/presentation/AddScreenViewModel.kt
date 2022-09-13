package uz.gita.contactauth.presentation

import androidx.lifecycle.LiveData
import uz.gita.contactauth.data.local.room.entity.ContactEntity

interface AddScreenViewModel {

    val errorLiveData: LiveData<String>

    val successLiveData: LiveData<String>

    val openScreenLiveData: LiveData<Unit>

    fun add(contactEntity: ContactEntity)
}