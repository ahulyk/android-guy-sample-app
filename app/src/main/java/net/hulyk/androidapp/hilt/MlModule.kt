package net.hulyk.androidapp.hilt


import com.google.mlkit.vision.face.FaceDetection
import com.google.mlkit.vision.face.FaceDetector
import com.google.mlkit.vision.face.FaceDetectorOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.hulyk.androidapp.ml.FaceDetectorWrapper
import net.hulyk.androidapp.ml.FaceDetectorWrapperImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MlModule {

    @Provides
    @Singleton
    fun provideMlKitOptions() = FaceDetectorOptions.Builder()
        .setPerformanceMode(FaceDetectorOptions.PERFORMANCE_MODE_ACCURATE)
        .setLandmarkMode(FaceDetectorOptions.LANDMARK_MODE_ALL)
        .setClassificationMode(FaceDetectorOptions.CLASSIFICATION_MODE_ALL)
        .build()

    @Provides
    @Singleton
    fun provideMlKit(options: FaceDetectorOptions) =
        FaceDetection.getClient(options)


    @Provides
    @Singleton
    fun provideMlWrapper(detector: FaceDetector): FaceDetectorWrapper =
        FaceDetectorWrapperImpl(detector)

}