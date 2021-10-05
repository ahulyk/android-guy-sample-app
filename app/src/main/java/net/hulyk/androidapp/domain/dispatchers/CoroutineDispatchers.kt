package net.hulyk.androidapp.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher

class CoroutineDispatchers(
    val default: CoroutineDispatcher,
    val io: CoroutineDispatcher,
    val main: CoroutineDispatcher,
)
