package br.com.wellingtoncosta.coroutines.domain.repository

import br.com.wellingtoncosta.coroutines.domain.model.User
import kotlinx.coroutines.Deferred

/**
 * @author Wellington Costa on 23/04/18
 */
interface UserRepository {

    suspend fun getAll(): List<User>

    suspend fun getByUsername(username: String): User

}