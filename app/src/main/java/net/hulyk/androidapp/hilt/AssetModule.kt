package net.hulyk.androidapp.hilt


import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.hulyk.androidapp.asset.AssetProvider
import net.hulyk.androidapp.asset.AssetProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AssetModule {

    @Binds
    @Singleton
    abstract fun provideAssetProvider(assetProvider: AssetProviderImpl): AssetProvider
}