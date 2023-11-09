package com.gsoft.nimblechalenge.presentation.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gsoft.nimblechalenge.MainCoroutineRule
import com.gsoft.nimblechalenge.data.model.TokenAttributes
import com.gsoft.nimblechalenge.data.model.TokenData
import com.gsoft.nimblechalenge.data.model.TokenResponse
import com.gsoft.nimblechalenge.domain.usecases.authUsecases.SigninUseCase
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
class LoginViewModelTest {

    private val singinUseCase: SigninUseCase = mockk()

    private lateinit var loginViewModel: LoginViewModel


    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        loginViewModel = LoginViewModel(singinUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }


    @Test
    fun `login success`() = runBlocking {

        val tokenResponse = TokenResponse(
            data = TokenData(
                id = "a",
                type = "b",
                attributes = TokenAttributes(
                    access_token = "token",
                    refresh_token = "refresh",
                    token_type = "bearer"
                )
            ),
            error = null
        )
        coEvery { singinUseCase.invoke("a", "b") } returns tokenResponse
        loginViewModel.login("a", "b")

        delay(1000)

        coVerify { singinUseCase.invoke("a", "b") }

        assert(loginViewModel.state.value.isAuth)
        assert(!loginViewModel.state.value.isError)
    }



    @Test
    fun `login fail`() = runBlocking {
        coEvery { singinUseCase.invoke("a", "b") } throws Exception("error")
        loginViewModel.login("a", "b")

        delay(1000)

        coVerify { singinUseCase.invoke("a", "b") }

        assert(!loginViewModel.state.value.isAuth)
        assert(loginViewModel.state.value.isError)
    }
}