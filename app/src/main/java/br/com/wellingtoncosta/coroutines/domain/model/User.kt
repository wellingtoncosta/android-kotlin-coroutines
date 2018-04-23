package br.com.wellingtoncosta.coroutines.domain.model

import com.google.gson.annotations.SerializedName

/**
 * @author WellingtonCosta on 22/04/18.
 */
data class User (
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("login") val username: String
)