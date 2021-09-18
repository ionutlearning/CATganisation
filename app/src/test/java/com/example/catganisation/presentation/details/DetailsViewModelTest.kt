package com.example.catganisation.presentation.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catganisation.*
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.usecase.GetBreedsTask
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailsViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailsViewModel

    @MockK
    private lateinit var task: GetBreedsTask

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = DetailsViewModel(task)
    }

    @Test
    fun `getBreedById returns liveData with Success`() =
        mainCoroutineRule.runBlockingTest {
            val breed = breedEntity
            val taskResult = flowOf(breed)
            val liveDataResult = ViewResult.Success(breed)

            coEvery { task.getBreedById(any()) } returns taskResult

            viewModel.getBreedById("1")
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `getBreedById returns liveData with Error`() =
        mainCoroutineRule.runBlockingTest {
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { task.getBreedById(any()) } throws RuntimeException(errorMessage)

            viewModel.getBreedById("1")
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }
}