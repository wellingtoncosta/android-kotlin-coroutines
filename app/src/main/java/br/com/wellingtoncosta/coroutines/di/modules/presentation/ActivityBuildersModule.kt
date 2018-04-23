package br.com.wellingtoncosta.coroutines.di.modules.presentation

import br.com.wellingtoncosta.coroutines.presentation.ListUsersActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author wellingtoncosta on 23/04/18
 */
@Module
interface ActivityBuildersModule {

    @ContributesAndroidInjector
    fun contributeMainActivity(): ListUsersActivity

}