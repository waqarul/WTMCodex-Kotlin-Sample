package com.wtmcodex.kotlinsample.di

import android.app.Application
import com.wtmcodex.kotlinsample.di.modules.*
import com.wtmcodex.kotlinsample.infrastructure.WTMCodexApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * This is a Dagger component. Refer to [com.wtmcodex.kotlinsample.infrastructure.DaggerApplication] for the list of Dagger components
 * used in this application.
 *
 *
 * Even though Dagger allows annotating a [Component] as a singleton, the code
 * itself must ensure only one instance of the class is created. This is done in [ ].
 * //[AndroidSupportInjectionModule]
 * // is the module from Dagger.Android that helps with the generation
 * // and location of subcomponents.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
        AndroidSupportInjectionModule::class,
        DatabaseModule::class,
        NetworkModule::class,
        ServicesModule::class,
        ActivityBindingModule::class,
        RepositoryManagerModule::class))
interface AppComponent : AndroidInjector<WTMCodexApplication> {

    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}