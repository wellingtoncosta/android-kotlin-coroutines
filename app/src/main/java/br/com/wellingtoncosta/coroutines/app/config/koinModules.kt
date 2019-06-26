package br.com.wellingtoncosta.coroutines.app.config

import br.com.wellingtoncosta.coroutines.resources.repository.UserDataRepository
import br.com.wellingtoncosta.coroutines.resources.remote.api.GithubApi
import br.com.wellingtoncosta.coroutines.domain.repository.UserRepository
import br.com.wellingtoncosta.coroutines.app.ui.ListUsersViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val uiModule = module {
    viewModel { ListUsersViewModel(get()) }
}

val repositoryModule = module {
    single<UserRepository> { UserDataRepository(get()) }
}

val remoteModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { createApi<GithubApi>(get()) }
}
