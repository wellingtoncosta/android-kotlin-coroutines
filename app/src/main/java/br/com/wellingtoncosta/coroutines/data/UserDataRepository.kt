package br.com.wellingtoncosta.coroutines.data

import br.com.wellingtoncosta.coroutines.data.mapper.toModel
import br.com.wellingtoncosta.coroutines.data.remote.api.GithubApi
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author Wellington Costa on 23/04/18
 */
class UserDataRepository(
        private val api: GithubApi
) : UserRepository {

    override suspend fun getAll() = withContext(IO) {
        async { api.getAll().await().map { it.toModel() } }
    }.await()

    override suspend fun getByUsername(username: String) = withContext(IO) {
        async { api.getByUsername(username).await().toModel() }
    }.await()

}