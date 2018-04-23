package br.com.wellingtoncosta.coroutines.di.modules.presentation

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import br.com.wellingtoncosta.coroutines.di.keys.ViewModelKey
import br.com.wellingtoncosta.coroutines.presentation.ListUsersViewModel
import br.com.wellingtoncosta.coroutines.presentation.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author wellingtoncosta on 23/04/18
 */
@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListUsersViewModel::class)
    fun bindListUsersViewModel(listUsersViewModel: ListUsersViewModel): ViewModel

    @Binds
    fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

}