package br.com.wellingtoncosta.coroutines.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.wellingtoncosta.coroutines.domain.model.User
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import br.com.wellingtoncosta.coroutines.ui.base.CoroutineViewModel
import kotlinx.coroutines.launch

/**
 * @author Wellington Costa on 23/04/18
 */
class ListUsersViewModel(
        private val repository: UserRepository
) : CoroutineViewModel() {

    private val users: MutableLiveData<List<User>> = MutableLiveData()
    private val loading: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<Throwable> = MutableLiveData()

    fun users() = users as LiveData<List<User>>
    fun loading() = loading as LiveData<Boolean>
    fun error() = error as LiveData<Throwable>

    fun getAll() {
        jobs add launch {
            loading.value = true
            try {
                users.value = repository.getAll().await()
                loading.value = false
            } catch(t: Throwable) {
                users.value = emptyList()
                error.value = t
            } finally {
                loading.value = false
            }
        }
    }

    fun getByUsername(username: String) {
        jobs add launch {
            loading.value = true
            try {
                val user = repository.getByUsername(username).await()
                users.value = listOf(user)
            } catch(t: Throwable) {
                users.value = emptyList()
                error.value = t
            }
            finally {
                loading.value = false
            }
        }
    }

}