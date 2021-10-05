package net.hulyk.androidapp.domain.usecase

import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import net.hulyk.androidapp.domain.dispatchers.CoroutineDispatchers
import net.hulyk.androidapp.dto.PersonData
import net.hulyk.androidapp.repo.PersonRepository
import org.junit.Assert
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class LoadPersonsUseCaseTest {

    private val dispatcher = TestCoroutineDispatcher()
    private val dispatchers: CoroutineDispatchers =
        CoroutineDispatchers(dispatcher, dispatcher, dispatcher)

    private val personRepository: PersonRepository = mock()
    private val calcFaceSizeUseCase: CalcFaceSizeUseCase = mock()
    private val useCase by lazy {
        LoadPersonsUseCase(
            dispatchers,
            personRepository,
            calcFaceSizeUseCase
        )
    }

    @Test
    fun `load persons success`() {
        runBlocking {
            whenever(personRepository.getPersons()).thenReturn(emptyList())

            assert(useCase().isSuccess)
        }
    }

    @Test
    fun `verify Url normalization`() {
        runBlocking {
            whenever(personRepository.getPersons()).thenReturn(
                listOf(PersonData("test1", "img"))
            )
            whenever(calcFaceSizeUseCase(anyString())).thenReturn(Result.success(0.33f))

            assertTrue(
                useCase().getOrThrow().first().avatarImage.startsWith("file:///android_asset/")
            )
        }
    }

    @Test
    fun `verify sorting order`() {
        runBlocking {
            whenever(personRepository.getPersons())
                .thenReturn(
                    listOf(
                        PersonData("test1", "img"),
                        PersonData("test3", "img4"),
                        PersonData("test2", "img2")
                    )
                )
            whenever(calcFaceSizeUseCase(anyString()))
                .thenReturn(Result.success(0.15f))
                .thenReturn(Result.success(0.30f))
                .thenReturn(Result.success(0.15f))

            Assert.assertArrayEquals(
                useCase().getOrThrow().map { it.name }.toTypedArray(),
                arrayOf("test3", "test1", "test2")
            )
        }
    }

}