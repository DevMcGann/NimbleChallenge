package com.gsoft.nimblechalenge.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gsoft.nimblechalenge.MainCoroutineRule
import com.gsoft.nimblechalenge.data.model.SurveyAttributes
import com.gsoft.nimblechalenge.data.model.SurveyItem
import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.domain.usecases.surveyUsecases.GetSurveyUseCase
import com.gsoft.nimblechalenge.util.MyResource
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @RelaxedMockK
    private lateinit var useCase: GetSurveyUseCase


    private lateinit var viewModel: HomeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(useCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get surveys success`() = runTest {
        //Given
        //
        val surveyItem = SurveyItem(id = "id", type = "type", attributes = SurveyAttributes(title = "title", description = "description", cover_image_url = "image", survey_type = "survey", is_active = true, active_at = "active", created_at = "created", inactive_at = "inactive", thank_email_above_threshold = "above", thank_email_below_threshold = "below"))
        val surveyResponse = SurveyResponse(data = listOf(surveyItem), meta = null)
        val result = MyResource.Success(surveyResponse)

        coEvery { useCase.invoke() } returns result

        //When
        viewModel.getSurvey()

        //Then
        advanceUntilIdle()
        assert(viewModel.state.value.surveyData == result.data)
    }


    @Test
    fun `get Surveys error`() = runTest {
        val response  = Exception("some")
        val result = MyResource.Failure(response)

        coEvery { useCase.invoke() } returns result

        //When
        viewModel.getSurvey()

        //Then
        advanceUntilIdle()
        assert(viewModel.state.value.isError)
    }




}