package br.com.wellingtoncosta.coroutines.domain.repository

import br.com.wellingtoncosta.coroutines.domain.model.User
import kotlinx.coroutines.experimental.Deferred

/**
 * @author wellingtoncosta on 23/04/18
 */
interface UserRepository {

    fun getAll(): Deferred<List<User>>

    fun getByUsername(username: String): Deferred<User>

}