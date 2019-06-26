package br.com.wellingtoncosta.coroutines.resources.repository

import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import br.com.wellingtoncosta.coroutines.resources.remote.api.GithubApi
import br.com.wellingtoncosta.coroutines.resources.remote.response.toModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * @author Wellington Costa on 23/04/18
 */
class UserDataRepository(
        private val api: GithubApi
) : UserRepository {

    override suspend fun getAll() = withContext(IO) {
        api.getAll().await().map { it.toModel() }
    }

    override suspend fun getByUsername(username: String) = withContext(IO) {
        api.getByUsername(username).await().toModel()
    }

}