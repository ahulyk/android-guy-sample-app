package net.hulyk.androidapp.asset

import java.io.InputStream

interface AssetProvider {
    fun openFileStream(fileName: String): InputStream
}