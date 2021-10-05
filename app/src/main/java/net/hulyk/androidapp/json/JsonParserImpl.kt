package net.hulyk.androidapp.json

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream

class JsonParserImpl(private val json: Json) : Parser {
    override fun <T> parseList(stream: InputStream, type: KSerializer<T>): List<T> {
        return json.decodeFromStream(ListSerializer(type), stream)
    }
}