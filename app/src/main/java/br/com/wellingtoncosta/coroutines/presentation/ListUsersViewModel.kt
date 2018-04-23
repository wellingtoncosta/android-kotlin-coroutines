package br.com.wellingtoncosta.coroutines.presentation

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.wellingtoncosta.coroutines.domain.model.User
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

/**
 * @author WellingtonCosta on 23/04/18
 */
 class ListUsersViewModel @Inject constructor(
        private val repository: UserRepository
) : ViewModel() {

   val users: MutableLiveData<List<User>> = MutableLiveData()

   val loadingStatus: MutableLiveData<Boolean> = MutableLiveData()

   fun loadUsers() = async(UI) {
       try {
           loadingStatus.value = true
           val results = async { repository.getAll() }.await()
           users.value = results
           loadingStatus.value = false
       } catch(e: Exception) {
           users.value = emptyList()
           loadingStatus.value = false
       }
   }

}