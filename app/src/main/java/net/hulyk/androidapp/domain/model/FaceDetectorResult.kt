package net.hulyk.androidapp.domain.model

class FaceDetectorResult(
    val faces: List<FaceRectModel>,
    val imageSize: BitmapSize,
    val image: BitmapWrapper? = null,
)