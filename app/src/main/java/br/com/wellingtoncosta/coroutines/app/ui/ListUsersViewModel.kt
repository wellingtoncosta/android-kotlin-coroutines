package br.com.wellingtoncosta.coroutines.app.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.wellingtoncosta.coroutines.domain.model.User
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * @author Wellington Costa on 23/04/18
 */
class ListUsersViewModel(
        private val repository: UserRepository
) : ViewModel() {

    private val viewModelJob = SupervisorJob()

    private val viewModeScope = CoroutineScope(Main + viewModelJob)

    private val _users: MutableLiveData<List<User>> = MutableLiveData()

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()

    private val _error: MutableLiveData<Throwable> = MutableLiveData()

    val users: LiveData<List<User>> get() = _users

    val loading: LiveData<Boolean> get() = _loading

    val error: LiveData<Throwable> get() = _error

    fun getAll() {
        viewModeScope.launch {
            _loading.value = true
            try {
                _users.value = repository.getAll()
                _loading.value = false
            } catch(t: Throwable) {
                _users.value = emptyList()
                _error.value = t
            } finally {
                _loading.value = false
            }
        }
    }

    fun getByUsername(username: String) {
        viewModeScope.launch {
            _loading.value = true
            try {
                val user = repository.getByUsername(username)
                _users.value = listOf(user)
            } catch(t: Throwable) {
                _users.value = emptyList()
                _error.value = t
            }
            finally {
                _loading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}