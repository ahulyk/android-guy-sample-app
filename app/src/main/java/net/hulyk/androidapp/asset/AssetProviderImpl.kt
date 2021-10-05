package net.hulyk.androidapp.asset

import android.content.Context
import android.content.res.AssetManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

class AssetProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AssetProvider {
    override fun openFileStream(fileName: String): InputStream {
        return context.assets.open(fileName, AssetManager.ACCESS_STREAMING)
    }
}