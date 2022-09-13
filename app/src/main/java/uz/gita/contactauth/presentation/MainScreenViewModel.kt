package uz.gita.contactauth.presentation

import androidx.lifecycle.LiveData
import uz.gita.contactauth.data.local.room.entity.ContactEntity

interface MainScreenViewModel {

    val errorLiveData: LiveData<String>

    val successLiveData: LiveData<String>

    val listLiveData: LiveData<List<ContactEntity>>

    fun sync()

    fun delete(contactEntity: ContactEntity)

    fun deleteAccount()

    fun logout()
}