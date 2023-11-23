package com.gsoft.nimblechalenge.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import com.gsoft.nimblechalenge.MainCoroutineRule
import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.domain.model.Survey
import com.gsoft.nimblechalenge.domain.usecases.surveyUsecases.GetSurveys
import com.gsoft.nimblechalenge.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test



@ExperimentalCoroutinesApi
class HomeViewModelTest {


    private lateinit var  useCase : GetSurveys
    private lateinit var vm : HomeViewModel
    private val repo : SurveyRepository = mockk(relaxed = true)

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()


    private val dispatcher = StandardTestDispatcher()

    val survey = Survey(
        id = "2",
        title = "title",
        description = "description",
        cover_image_url = "image",
        survey_type = "survey",
        is_active = true,
        active_at = "active",
        created_at = "created",
        inactive_at = "inactive",
        thank_email_above_threshold = "above",
        thank_email_below_threshold = "below"
    )

    val myList : List<Survey?> = listOf(survey)

    val surveySuccess = Resource.success(data = myList, null)


    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        MockKAnnotations.init(this)
        useCase = GetSurveys(repo)
        vm = HomeViewModel(useCase)
        
    }

    @After
    fun close() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get survey success`() = runTest {
        //Given
        coEvery { useCase.invoke(vm.state.value.currentPage) } returns surveySuccess

        // When
        vm.getSurveys()

        // Then
        dispatcher.scheduler.advanceUntilIdle()

        coVerifyAll {
            useCase.invoke(any())
            vm.getSurveys()
        }

        assertEquals(myList, vm.state.value.surveys)

    }

    @Test
    fun `get survey error`() = runTest {
        //Given
        coEvery { useCase.invoke(vm.state.value.currentPage) } returns Resource.error(message = "error", data = null)

        // When
        vm.getSurveys()

        // Then
        dispatcher.scheduler.advanceUntilIdle()

        coVerifyAll {
            useCase.invoke(any())
            vm.getSurveys()
        }

        assertEquals(true, vm.state.value.isError)
    }


}