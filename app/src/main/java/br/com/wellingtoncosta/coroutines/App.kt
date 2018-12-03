package br.com.wellingtoncosta.coroutines

import android.app.Application
import br.com.wellingtoncosta.coroutines.di.remoteModule
import br.com.wellingtoncosta.coroutines.di.repositoryModule
import br.com.wellingtoncosta.coroutines.di.uiModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.startKoin

/**
 * @author Wellington Costa on 22/04/18.
 */
open class App : Application() {

    private val appModules by lazy {
        listOf(remoteModule, repositoryModule, uiModule)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModules)

        Fresco.initialize(this)
    }

}