package net.hulyk.androidapp.json

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer
import java.io.InputStream

interface Parser {
    fun <T> parseList(stream: InputStream, type: KSerializer<T>): List<T>
}

@OptIn(InternalSerializationApi::class)
inline fun <reified T: Any> Parser.parseList(stream: InputStream): List<T> = parseList(stream, T::class.serializer())
