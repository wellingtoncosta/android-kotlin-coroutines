package br.com.wellingtoncosta.coroutines.domain.repository

import br.com.wellingtoncosta.coroutines.domain.model.User

/**
 * @author wellingtoncosta on 23/04/18
 */
interface UserRepository {

    fun getAll(): List<User>

    fun getByUsername(username: String): User

}