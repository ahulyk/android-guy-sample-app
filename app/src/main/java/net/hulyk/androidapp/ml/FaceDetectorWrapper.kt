package net.hulyk.androidapp.ml

import net.hulyk.androidapp.domain.model.FaceDetectorResult

interface FaceDetectorWrapper {

    suspend fun detectFace(url: String): FaceDetectorResult

    suspend fun detectFaceWithBitmap(url: String): FaceDetectorResult
}