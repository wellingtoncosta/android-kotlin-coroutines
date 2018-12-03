package br.com.wellingtoncosta.coroutines.data.remote.response

import com.squareup.moshi.Json

/**
 * @author Wellington Costa on 03/12/18
 */
data class UserResponse(
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "login") val username: String
)