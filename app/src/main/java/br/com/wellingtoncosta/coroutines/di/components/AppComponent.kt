package br.com.wellingtoncosta.coroutines.di.components

import br.com.wellingtoncosta.coroutines.App
import br.com.wellingtoncosta.coroutines.di.modules.AppModule
import br.com.wellingtoncosta.coroutines.di.modules.data.RepositoryModule
import br.com.wellingtoncosta.coroutines.di.modules.data.RetrofitModule
import br.com.wellingtoncosta.coroutines.di.modules.presentation.ActivityBuildersModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author WellingtonCosta on 22/04/18.
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBuildersModule::class,
    AppModule::class,
    RetrofitModule::class,
    RepositoryModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}