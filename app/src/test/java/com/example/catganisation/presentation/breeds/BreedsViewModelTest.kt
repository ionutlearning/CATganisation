package com.example.catganisation.presentation.breeds

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catganisation.*
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.usecase.GetBreedsTask
import com.example.catganisation.domain.usecase.GetFiltersTask
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BreedsViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: BreedsViewModel

    @MockK
    private lateinit var breedsTask: GetBreedsTask

    @MockK
    private lateinit var filtersTask: GetFiltersTask

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        viewModel = BreedsViewModel(breedsTask, filtersTask)
    }

    @Test
    fun `getBreeds returns liveData with Success`() =
        mainCoroutineRule.runBlockingTest {
            val taskResult = flowOf(listOf(breedEntity))
            val filterResult = flowOf(listOf(filterEntity))
            val liveDataResult = ViewResult.Success(
                BreedsViewState(
                    listOf(breedEntity), listOf(filterEntity),
                    BreedsViewModel.ALL_BREEDS
                )
            )

            coEvery { breedsTask.getBreeds() } returns taskResult
            coEvery { filtersTask() } returns filterResult

            viewModel.getBreeds()
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `getBreeds returns liveData with Error when breedsTask throws error`() =
        mainCoroutineRule.runBlockingTest {
            val filterResult = flowOf(listOf(filterEntity))
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { breedsTask.getBreeds() } throws RuntimeException(errorMessage)
            coEvery { filtersTask() } returns filterResult

            viewModel.getBreeds()
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `getBreeds returns liveData with Error when filterTask throws error`() =
        mainCoroutineRule.runBlockingTest {
            val taskResult = flowOf(listOf(breedEntity))
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { breedsTask.getBreeds() } returns taskResult
            coEvery { filtersTask() } throws RuntimeException(errorMessage)

            viewModel.getBreeds()
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `filter returns liveData with Success with France filter`() =
        mainCoroutineRule.runBlockingTest {
            val taskResult = flowOf(listOf(breedEntity))
            val liveDataResult = ViewResult.Success(filteredFranceViewState)

            coEvery { breedsTask.getBreedsByOrigin(filterFrance) } returns taskResult

            viewModel.filter(filterFrance)
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `filter returns liveData with Error with France filter when getBreedsByOrigin throws exception`() =
        mainCoroutineRule.runBlockingTest {
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { breedsTask.getBreedsByOrigin(filterFrance) } throws RuntimeException(
                errorMessage
            )

            viewModel.filter(filterFrance)
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `filter returns liveData with Success with All Breeds filter`() =
        mainCoroutineRule.runBlockingTest {
            val taskResult = flowOf(listOf(breedEntity))
            val liveDataResult = ViewResult.Success(filteredAllBreedsViewState)

            coEvery { breedsTask.getBreeds() } returns taskResult

            viewModel.cachedFilter = filterFrance
            viewModel.filter(filterAllBreeds)
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `filter returns liveData with Error with All Breeds filter and getBreeds throws exception`() =
        mainCoroutineRule.runBlockingTest {
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { breedsTask.getBreeds() } throws RuntimeException(errorMessage)

            viewModel.cachedFilter = filterFrance
            viewModel.filter(filterAllBreeds)
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }
}