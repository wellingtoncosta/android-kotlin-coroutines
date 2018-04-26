package br.com.wellingtoncosta.coroutines.data

import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import javax.inject.Inject

/**
 * @author wellingtoncosta on 23/04/18
 */
class UserRepositoryData @Inject constructor(
        private val remoteDataSource: UserDataSource.Remote
) : UserRepository {

    override fun getAll() = remoteDataSource.getAll()

    override fun getByUsername(username: String) = remoteDataSource.getByUsername(username)

}