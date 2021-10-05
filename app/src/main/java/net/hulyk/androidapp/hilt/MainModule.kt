package net.hulyk.androidapp.hilt

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import net.hulyk.androidapp.asset.AssetProvider
import net.hulyk.androidapp.asset.AssetProviderImpl
import net.hulyk.androidapp.domain.dispatchers.CoroutineDispatchers
import net.hulyk.androidapp.json.JsonParserImpl
import net.hulyk.androidapp.json.Parser
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MainModule {

    @Provides
    @Singleton
    fun provideParser(json: Json): Parser = JsonParserImpl(json)

    @Provides
    @Singleton
    fun provideJson(): Json = Json { isLenient = true }

    @Provides
    @Singleton
    fun provideAppCoroutineDispatchers(): CoroutineDispatchers =
        CoroutineDispatchers(Dispatchers.Default, Dispatchers.IO, Dispatchers.Main)

}