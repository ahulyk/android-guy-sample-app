package net.hulyk.androidapp.domain.usecase

import kotlinx.coroutines.withContext
import net.hulyk.androidapp.domain.dispatchers.CoroutineDispatchers
import net.hulyk.androidapp.domain.model.BitmapWrapper
import net.hulyk.androidapp.ml.FaceDetectorWrapper
import javax.inject.Inject

class GetFaceAreaFaceSizeUseCase @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val detector: FaceDetectorWrapper
) {

    suspend operator fun invoke(url: String?): Result<BitmapWrapper> = runCatching {
        withContext(dispatchers.default) {
            requireNotNull(url) { "Please provide valid url!" }

            val result = detector.detectFaceWithBitmap(url)
            require(result.faces.isNotEmpty()) { "No face is detected!" }

            requireNotNull(result.image)
        }
    }

}