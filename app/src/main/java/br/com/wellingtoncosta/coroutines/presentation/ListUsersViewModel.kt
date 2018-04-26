package br.com.wellingtoncosta.coroutines.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.com.wellingtoncosta.coroutines.R
import br.com.wellingtoncosta.coroutines.domain.model.User
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * @author WellingtonCosta on 23/04/18
 */
 class ListUsersViewModel @Inject constructor(
        private val repository: UserRepository
) : ViewModel() {

    val users: MutableLiveData<List<User>> = MutableLiveData()

    val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()

    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    fun getAll() = async(UI) {
        errorMessage.value = -1
        try {
            loadingStatus.value = true
            val results = repository.getAll().await()
            users.value = results
            loadingStatus.value = false
        } catch(ex: Exception) {
            Log.e("getAll", ex.toString())
            users.value = emptyList()
            loadingStatus.value = false
            when(ex) {
                is UnknownHostException -> errorMessage.value = R.string.network_error
                else -> errorMessage.value = R.string.get_users_error
            }
        }
    }

    fun getByUsername(username: String) = async(UI) {
        errorMessage.value = -1
        try {
            loadingStatus.value = true
            val user = repository.getByUsername(username).await()
            users.value = listOf(user)
            loadingStatus.value = false
        } catch(ex: Exception) {
            Log.e("getByUsername", ex.toString())
            users.value = emptyList()
            loadingStatus.value = false
            when(ex) {
                is HttpException -> errorMessage.value = R.string.user_not_found
                else -> errorMessage.value = R.string.get_users_error
            }
        }
    }

}