package br.com.wellingtoncosta.coroutines.ui

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

    val users: MutableLiveData<List<User>> = MutableLiveData()
    val loading: MutableLiveData<Boolean> = MutableLiveData()
    val error: MutableLiveData<Throwable> = MutableLiveData()

    fun getAll() {
        launch {
            loading.value = true
            try {
                users.value = repository.getAll()
                loading.value = false
            } catch(t: Throwable) {
                users.value = emptyList()
                error.value = t
            } finally {
                loading.value = false
            }
        } addTo jobs
    }

    fun getByUsername(username: String) {
        launch {
            loading.value = true
            try {
                val user = repository.getByUsername(username)
                users.value = listOf(user)
            } catch(t: Throwable) {
                users.value = emptyList()
                error.value = t
            }
            finally {
                loading.value = false
            }
        } addTo jobs
    }

}