package br.com.wellingtoncosta.coroutines.data

import br.com.wellingtoncosta.coroutines.domain.model.User
import javax.inject.Inject

/**
 * @author wellingtoncosta on 23/04/18
 */
class UserRemoteDataSource @Inject constructor(
        private val githubApi: GithubApi
) : UserDataSource.Remote {

    override fun getAll() = githubApi.getUsers().execute().body()!!

    override fun getByUsername(username: String) =
            githubApi.getUserByUsername(username).execute().body()!!

}