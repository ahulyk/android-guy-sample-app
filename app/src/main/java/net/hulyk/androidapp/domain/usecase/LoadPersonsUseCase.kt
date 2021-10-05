package net.hulyk.androidapp.domain.usecase

import kotlinx.coroutines.withContext
import net.hulyk.androidapp.domain.Mockable
import net.hulyk.androidapp.domain.dispatchers.CoroutineDispatchers
import net.hulyk.androidapp.domain.model.PersonModel
import net.hulyk.androidapp.repo.PersonRepository
import javax.inject.Inject

@Mockable
class LoadPersonsUseCase @Inject constructor(
    private val dispatchers: CoroutineDispatchers,
    private val personRepository: PersonRepository,
    private val calcFaceSizeUseCase: CalcFaceSizeUseCase
) {

    suspend operator fun invoke(): Result<List<PersonModel>> = runCatching {
        withContext(dispatchers.io) {
            personRepository.getPersons()
                .map {
                    val url = normalizeAssetUrl(it.img)
                    val faceSize = calcFaceSize(url)
                    PersonModel(it.name, url, faceSize)
                }
                .sortedWith(compareByDescending<PersonModel> { it.faceSize }.thenBy { it.name })
                .toList()
        }
    }

    private fun normalizeAssetUrl(path: String) = "file:///android_asset/${path}"
    private suspend fun calcFaceSize(url: String) = calcFaceSizeUseCase(url).getOrElse { 0f }

}