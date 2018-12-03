package br.com.wellingtoncosta.coroutines.data

import br.com.wellingtoncosta.coroutines.data.mapper.toModel
import br.com.wellingtoncosta.coroutines.data.remote.api.GithubApi
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import br.com.wellingtoncosta.coroutines.extension.runAsyncOnIo

/**
 * @author Wellington Costa on 23/04/18
 */
class UserDataRepository(
        private val api: GithubApi
) : UserRepository {

    override suspend fun getAll() = runAsyncOnIo {
        api.getAll().await().map { it.toModel() }
    }

    override suspend fun getByUsername(username: String) = runAsyncOnIo {
        api.getByUsername(username).await().toModel()
    }

}