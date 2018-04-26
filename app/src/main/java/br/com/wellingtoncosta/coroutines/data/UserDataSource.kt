package br.com.wellingtoncosta.coroutines.data

import br.com.wellingtoncosta.coroutines.domain.model.User
import kotlinx.coroutines.experimental.Deferred

/**
 * @author wellingtoncosta on 23/04/18
 */
interface UserDataSource {

    interface Remote {
        fun getAll(): Deferred<List<User>>

        fun getByUsername(username: String): Deferred<User>
    }

}