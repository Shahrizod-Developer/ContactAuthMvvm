package uz.gita.contactauth.presentation.impl

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import uz.gita.contactauth.data.local.room.entity.ContactEntity
import uz.gita.contactauth.domain.auth.impl.AuthRepositoryImpl
import uz.gita.contactauth.domain.contact.impl.ContactRepositoryImpl
import uz.gita.contactauth.presentation.MainScreenViewModel

class MainScreenViewModelImpl : MainScreenViewModel, ViewModel() {

    private val repository = ContactRepositoryImpl()
    private val repositoryAuth = AuthRepositoryImpl()
    override val errorLiveData: LiveData<String> = repository.errorMessage()
    override val successLiveData: LiveData<String> = repository.successMessage()
    override val listLiveData: MediatorLiveData<List<ContactEntity>> = MediatorLiveData()

    init {
//        viewModelScope.launch(Dispatchers.IO) {
        listLiveData.addSource(repository.getAllContact()) {
            listLiveData.postValue(it)
        }
//        }

    }

    override fun sync() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.sync()
        }
    }

    override fun delete(contactEntity: ContactEntity) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(contactEntity)
        }

    }

    override fun deleteAccount() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryAuth.deleteAccount()
        }
    }

    override fun logout() {
        viewModelScope.launch(Dispatchers.IO) {
            repositoryAuth.logout()
        }

    }
}