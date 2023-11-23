package com.gsoft.nimblechalenge.presentation.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gsoft.nimblechalenge.MainCoroutineRule
import com.gsoft.nimblechalenge.domain.usecases.authUsecases.IsLoggedInUseCase
import com.gsoft.nimblechalenge.domain.usecases.authUsecases.RefreshTokenUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class SplashViewModelTest {

    private val refreshTokenUseCase: RefreshTokenUseCase = mockk()
    private val isLoggedInUseCase: IsLoggedInUseCase = mockk()

    private lateinit var splashViewModel: SplashViewModel


    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        splashViewModel = SplashViewModel(refreshTokenUseCase, isLoggedInUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }



    @Test
    fun `refresh token success`() = runBlocking {
        coEvery { refreshTokenUseCase.invoke() } returns true
        splashViewModel.refreshToken()

        delay(1000)

        coVerify { refreshTokenUseCase.invoke() }

        assert(splashViewModel.state.value.isAuth)
        assert(!splashViewModel.state.value.isError)
    }



    @Test
    fun `refresh token failure`() = runBlocking {
        coEvery { refreshTokenUseCase.invoke() } throws Exception("Some error")

        splashViewModel.refreshToken()

        delay(1000)

        coVerify { refreshTokenUseCase.invoke() }

        assert(!splashViewModel.state.value.isAuth)
        assert(splashViewModel.state.value.isError)
    }


    @Test
    fun `isLogged success`() = runBlocking {
        coEvery { isLoggedInUseCase.invoke() } returns true
        splashViewModel.isLogged()

        delay(1000)

        coVerify { isLoggedInUseCase.invoke() }

        assert(splashViewModel.state.value.goToHome)
        assert(!splashViewModel.state.value.isError)
    }


    @Test
    fun `isLogged failure`() = runBlocking {
        coEvery { isLoggedInUseCase.invoke() } returns false
        splashViewModel.isLogged()

        delay(1000)

        coVerify { isLoggedInUseCase.invoke() }

        assert(!splashViewModel.state.value.goToHome)
    }

}