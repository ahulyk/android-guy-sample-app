package net.hulyk.androidapp.domain.model

class BitmapSize(
    val height: Int = 0,
    val width: Int = 0,
) {
    fun square() = (height * width).toFloat()
}