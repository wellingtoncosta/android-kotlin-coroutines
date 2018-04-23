package br.com.wellingtoncosta.coroutines

import br.com.wellingtoncosta.coroutines.di.components.DaggerAppComponent
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

/**
 * @author WellingtonCosta on 22/04/18.
 */
open class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
    }

    override fun applicationInjector(): AndroidInjector<out App> {
        return DaggerAppComponent.builder().create(this)
    }

}