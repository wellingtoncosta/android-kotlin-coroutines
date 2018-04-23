package br.com.wellingtoncosta.coroutines.di.components

import br.com.wellingtoncosta.coroutines.App
import br.com.wellingtoncosta.coroutines.di.modules.data.RetrofitModule
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
    RetrofitModule::class
])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}