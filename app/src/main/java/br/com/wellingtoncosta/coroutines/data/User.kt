package br.com.wellingtoncosta.coroutines.data

import com.google.gson.annotations.SerializedName

/**
 * @author WellingtonCosta on 22/04/18.
 */
data class User (
        @SerializedName("id") val id: Long,
        @SerializedName("avatar_url") val avatarUrl: String,
        @SerializedName("name") val name: String,
        @SerializedName("login") val username: String,
        @SerializedName("location") val location: String
)