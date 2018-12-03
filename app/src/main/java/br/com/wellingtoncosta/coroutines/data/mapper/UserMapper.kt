package br.com.wellingtoncosta.coroutines.data.mapper

import br.com.wellingtoncosta.coroutines.data.remote.response.UserResponse
import br.com.wellingtoncosta.coroutines.domain.model.User

/**
 * @author Wellington Costa on 03/12/18
 */

fun UserResponse.toModel() = User(this.avatarUrl, this.username)