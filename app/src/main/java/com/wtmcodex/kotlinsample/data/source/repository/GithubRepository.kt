package com.wtmcodex.kotlinsample.data.source.repository

import com.wtmcodex.kotlinsample.data.Local
import com.wtmcodex.kotlinsample.data.Remote
import com.wtmcodex.kotlinsample.data.entities.GithubUserModel
import com.wtmcodex.kotlinsample.data.source.datasource.GithubDataSource
import com.wtmcodex.kotlinsample.entities.baseClass.GithubResponse
import io.reactivex.Single
import io.reactivex.annotations.NonNull
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 * <p />
 * By marking the constructor with {@code @Inject} and the class with {@code @Singleton}, Dagger
 * injects the dependencies required to create an instance of the TasksRespository (if it fails, it
 * emits a compiler error). It uses {@link TasksRepositoryModule} to do so, and the constructed
 * instance is available in {@link AppComponent}.
 * <p />
 * Dagger generated code doesn't require public access to the constructor or class, and
 * therefore, to ensure the developer doesn't instantiate the class manually and bypasses Dagger,
 * it's good practice minimise the visibility of the class/constructor as much as possible.
 */


// Constructor
/**
 * By marking the constructor with {@code @Inject}, Dagger will try to inject the dependencies
 * required to create an instance of the TasksRepository. Because {@link TasksDataSource} is an
 * interface, we must provide to Dagger a way to build those arguments, this is done in
 * {@link TasksRepositoryModule}.
 * <P>
 * When two arguments or more have the same type, we must provide to Dagger a way to
 * differentiate them. This is done using a qualifier.
 * <p>
 * Dagger strictly enforces that arguments not marked with {@code @Nullable} are not injected
 * with {@code @Nullable} values.
 */
@Singleton
class GithubRepository @Inject constructor(@Remote @NonNull private val mGithubRemoteDataSource: GithubDataSource,
                                           @Local @NonNull private val mGithubLocalDataSource: GithubDataSource)
    : Repository(), GithubDataSource {


    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    private var mCacheIsDirty = false


    override fun getGithubUsers(query: String, pageNumber: Int): Single<GithubResponse<GithubUserModel>> {
        return mGithubRemoteDataSource.getGithubUsers(query, pageNumber)
    }

    override fun clearCache() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}