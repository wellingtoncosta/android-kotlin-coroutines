package br.com.wellingtoncosta.coroutines.di.modules

import android.content.Context
import br.com.wellingtoncosta.coroutines.App
import br.com.wellingtoncosta.coroutines.di.modules.presentation.ViewModelModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author wellingtoncosta on 23/04/18
 */
@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    @Singleton
    fun providesContext(app: App): Context {
        return app.applicationContext
    }

}