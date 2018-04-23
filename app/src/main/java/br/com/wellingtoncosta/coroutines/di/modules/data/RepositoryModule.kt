package br.com.wellingtoncosta.coroutines.di.modules.data

import br.com.wellingtoncosta.coroutines.data.UserDataSource
import br.com.wellingtoncosta.coroutines.data.UserRemoteDataSource
import br.com.wellingtoncosta.coroutines.data.UserRepositoryData
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

/**
 * @author wellingtoncosta on 23/04/18
 */
@Module
interface RepositoryModule {

    @Binds
    fun bindUserRepositoryData(imp: UserRepositoryData): UserRepository

    @Binds
    fun bindUserRemoteDataSource(imp: UserRemoteDataSource): UserDataSource.Remote

}