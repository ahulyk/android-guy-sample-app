package net.hulyk.androidapp.ml

import android.graphics.*
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.face.FaceDetector
import com.squareup.picasso.Picasso
import net.hulyk.androidapp.domain.model.BitmapSize
import net.hulyk.androidapp.domain.model.BitmapWrapper
import net.hulyk.androidapp.domain.model.FaceDetectorResult
import net.hulyk.androidapp.domain.model.FaceRectModel
import net.hulyk.androidapp.ext.await
import timber.log.Timber
import javax.inject.Inject


class FaceDetectorWrapperImpl @Inject constructor(
    private val detector: FaceDetector
) : FaceDetectorWrapper {

    override suspend fun detectFace(url: String): FaceDetectorResult {
        val (_, inputImage) = url2InputImage(url)

        val faceSquares = detector.process(inputImage).await()
            .map { it.boundingBox.run { rectToModel(this) } }

        return FaceDetectorResult(
            faceSquares,
            BitmapSize(inputImage.height, inputImage.width)
        )
    }

    //TODO rewrite to other way - do not pass Bitmap just rect objects
    override suspend fun detectFaceWithBitmap(url: String): FaceDetectorResult {
        val (bitmap, inputImage) = url2InputImage(url)

        val faceSquares = detector.process(inputImage).await()
            .map { it.boundingBox.run { rectToModel(this) } }

        return FaceDetectorResult(
            faceSquares,
            BitmapSize(inputImage.height, inputImage.width),
            BitmapWrapper(createBitmap(bitmap, faceSquares))
        )
    }

    private fun url2InputImage(url: String): Pair<Bitmap, InputImage> {
        val bitmap = Picasso.get().load(url)
            .resize(0, 1024)
            .onlyScaleDown().get()
        Timber.d("Image size: ${bitmap.height} x ${bitmap.width}")

        val inputImage = InputImage.fromBitmap(bitmap, 0)
        return bitmap to inputImage
    }

    private fun createBitmap(
        bitmap: Bitmap,
        faceSquares: List<FaceRectModel>
    ): Bitmap {
        val tempBitmap = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.RGB_565)
        val canvas = Canvas(tempBitmap)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
        val paint = Paint().apply {
            strokeWidth = (bitmap.width * 0.005).toFloat()
            color = Color.RED
            style = Paint.Style.STROKE
        }

        faceSquares.forEach {
            canvas.drawRect(
                it.left.toFloat(),
                it.top.toFloat(),
                it.right.toFloat(),
                it.bottom.toFloat(),
                paint
            )

        }
        return tempBitmap
    }

    private fun rectToModel(rect: Rect): FaceRectModel {
        return FaceRectModel(rect.bottom, rect.left, rect.right, rect.top)
    }

}