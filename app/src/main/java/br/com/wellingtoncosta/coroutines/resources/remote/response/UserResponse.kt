package br.com.wellingtoncosta.coroutines.resources.remote.response

import br.com.wellingtoncosta.coroutines.domain.model.User
import com.squareup.moshi.Json

/**
 * @author Wellington Costa on 03/12/18
 */
data class UserResponse(
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "login") val username: String
)

fun UserResponse.toModel() = User(this.avatarUrl, this.username)