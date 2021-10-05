package net.hulyk.androidapp.ext

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy
import java.io.Serializable

inline fun <reified Args : NavArgs> SavedStateHandle.navArgs() = NavArgsLazy(Args::class) {
    Bundle().apply {
        keys().forEach {
            val value = get<Any>(it)
            if (value is Serializable) {
                putSerializable(it, value)
            } else if (value is Parcelable) {
                putParcelable(it, value)
            }
        }
    }
}