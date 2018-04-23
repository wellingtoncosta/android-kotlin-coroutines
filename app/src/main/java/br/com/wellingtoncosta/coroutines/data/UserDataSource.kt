package br.com.wellingtoncosta.coroutines.data

import br.com.wellingtoncosta.coroutines.domain.model.User

/**
 * @author wellingtoncosta on 23/04/18
 */
interface UserDataSource {

    interface Remote {
        fun getAll(): List<User>

        fun getByUsername(username: String): User
    }

}