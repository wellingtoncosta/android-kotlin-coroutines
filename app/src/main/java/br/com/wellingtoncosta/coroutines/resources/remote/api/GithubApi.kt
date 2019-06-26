package br.com.wellingtoncosta.coroutines.resources.remote.api

import br.com.wellingtoncosta.coroutines.resources.remote.response.UserResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Wellington Costa on 22/04/18.
 */
interface GithubApi {

    @GET("users")
    fun getAll(): Deferred<List<UserResponse>>

    @GET("users/{username}")
    fun getByUsername(@Path("username") username: String): Deferred<UserResponse>

}