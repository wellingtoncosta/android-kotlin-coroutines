package br.com.wellingtoncosta.coroutines.di

import br.com.wellingtoncosta.coroutines.ui.ListUsersViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

/**
 * @author Wellington Costa on 03/12/18
 */

val uiModule = module {
    viewModel { ListUsersViewModel(get()) }
}