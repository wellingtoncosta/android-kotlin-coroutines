package br.com.wellingtoncosta.coroutines.data

import br.com.wellingtoncosta.coroutines.domain.model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author WellingtonCosta on 22/04/18.
 */
interface GithubApi {

    @GET("users")
    fun getUsers(): Call<List<User>>

    @GET("users/{username}")
    fun getUserByUsername(@Path("username") username: String): Call<User>

}