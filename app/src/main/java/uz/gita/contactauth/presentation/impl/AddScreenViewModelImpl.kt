package uz.gita.contactauth.presentation.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.contactauth.data.local.room.entity.ContactEntity
import uz.gita.contactauth.domain.contact.impl.ContactRepositoryImpl
import uz.gita.contactauth.presentation.AddScreenViewModel

class AddScreenViewModelImpl : AddScreenViewModel, ViewModel() {

    private val repository = ContactRepositoryImpl()
    override val errorLiveData: LiveData<String> = repository.errorMessage()
    override val successLiveData: LiveData<String> = repository.successMessage()

    override val openScreenLiveData: MutableLiveData<Unit> = MutableLiveData()


    override fun add(contactEntity: ContactEntity) {

        contactEntity.phone =
            contactEntity.phone.replace(" ", "").replace("-", "").replace("(", "").replace(")", "")

        viewModelScope.launch(Dispatchers.IO) {
            repository.addContact(contactEntity)
            openScreenLiveData.postValue(Unit)
        }
    }
}