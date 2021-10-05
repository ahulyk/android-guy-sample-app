package net.hulyk.androidapp.domain.model

class FaceRectModel(
    val bottom: Int = 0,
    val left: Int = 0,
    val right: Int = 0,
    val top: Int = 0
) {
    private val height = bottom - top
    private val width = right - left

    fun square() = (height * width).toFloat()
}