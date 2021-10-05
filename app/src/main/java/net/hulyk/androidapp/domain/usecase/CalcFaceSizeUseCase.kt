package net.hulyk.androidapp.domain.usecase

import kotlinx.coroutines.withContext
import net.hulyk.androidapp.domain.Mockable
import net.hulyk.androidapp.domain.dispatchers.CoroutineDispatchers
import net.hulyk.androidapp.ml.FaceDetectorWrapper
import javax.inject.Inject

@Mockable
class CalcFaceSizeUseCase @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val detector: FaceDetectorWrapper
) {

    suspend operator fun invoke(url: String?): Result<Float> = runCatching {
        withContext(dispatchers.default) {
            requireNotNull(url) { "Please provide valid url!" }

            val result = detector.detectFace(url)
            val face = result.faces.maxByOrNull { it.square() }
            requireNotNull(face) { "No face is detected!" }

            face.square() / result.imageSize.square()
        }
    }

}