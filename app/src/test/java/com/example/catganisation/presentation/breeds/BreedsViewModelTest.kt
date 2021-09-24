package com.example.catganisation.presentation.breeds

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.catganisation.*
import com.example.catganisation.domain.ViewResult
import com.example.catganisation.domain.usecase.GetBreedsByOriginTask
import com.example.catganisation.domain.usecase.GetBreedsTask
import com.example.catganisation.domain.usecase.GetFiltersTask
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.*

class BreedsViewModelTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: BreedsViewModel

    @MockK
    private lateinit var getBreedsTask: GetBreedsTask

    @MockK
    private lateinit var getBreedsByOriginTask: GetBreedsByOriginTask

    @MockK
    private lateinit var getFiltersTask: GetFiltersTask

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
    }

    private fun initViewModel() {
        viewModel = BreedsViewModel(getBreedsTask, getBreedsByOriginTask, getFiltersTask)
    }

    @Test
    fun `init viewModel returns liveData with Success`() =
        mainCoroutineRule.runBlockingTest {
            val taskResult = flowOf(listOf(breed))
            val filterResult = flowOf(listOf(filter))
            val liveDataResult = ViewResult.Success(
                BreedsViewState(
                    listOf(breed), listOf(filter),
                    BreedsViewModel.ALL_BREEDS
                )
            )

            coEvery { getBreedsTask() } returns taskResult
            coEvery { getFiltersTask() } returns filterResult

            initViewModel()

            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `init viewModel returns liveData with Error when breedsTask throws error`() =
        mainCoroutineRule.runBlockingTest {
            val filterResult = flowOf(listOf(filter))
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { getBreedsTask() } throws RuntimeException(errorMessage)
            coEvery { getFiltersTask() } returns filterResult

            initViewModel()

            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `init viewModel returns liveData with Error when filterTask throws error`() =
        mainCoroutineRule.runBlockingTest {
            val taskResult = flowOf(listOf(breed))
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { getBreedsTask() } returns taskResult
            coEvery { getFiltersTask() } throws RuntimeException(errorMessage)

            initViewModel()

            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `filter returns liveData with Success with France filter`() =
        mainCoroutineRule.runBlockingTest {
            val taskResult = flowOf(listOf(breed))
            val liveDataResult = ViewResult.Success(filteredFranceViewState)

            coEvery { getBreedsByOriginTask(filterFrance) } returns taskResult

            initViewModel()
            viewModel.filter(filterFrance)
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `filter returns liveData with Error with France filter when getBreedsByOrigin throws exception`() =
        mainCoroutineRule.runBlockingTest {
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { getBreedsByOriginTask(filterFrance) } throws RuntimeException(
                errorMessage
            )

            initViewModel()
            viewModel.filter(filterFrance)
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `filter returns liveData with Success with All Breeds filter`() =
        mainCoroutineRule.runBlockingTest {
            val taskResult = flowOf(listOf(breed))
            val liveDataResult = ViewResult.Success(filteredAllBreedsViewState)

            coEvery { getBreedsTask() } returns taskResult

            initViewModel()
            viewModel.cachedFilter = filterFrance
            viewModel.filter(filterAllBreeds)
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }

    @Test
    fun `filter returns liveData with Error with All Breeds filter and getBreeds throws exception`() =
        mainCoroutineRule.runBlockingTest {
            val liveDataResult = ViewResult.Error(errorMessage)

            coEvery { getBreedsTask() } throws RuntimeException(errorMessage)

            initViewModel()
            viewModel.cachedFilter = filterFrance
            viewModel.filter(filterAllBreeds)
            Assert.assertEquals(viewModel.viewState.getOrAwaitValue(), liveDataResult)
        }
}