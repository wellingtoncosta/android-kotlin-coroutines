package br.com.wellingtoncosta.coroutines.extension

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 * @author Wellington Costa on 03/12/18
 */

suspend fun <T> runAsyncOnIo(action: suspend () -> T) = withContext(IO) {
    async { action() }.await()
}