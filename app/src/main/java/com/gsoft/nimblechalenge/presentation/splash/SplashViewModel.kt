package com.gsoft.nimblechalenge.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.gsoft.nimblechalenge.domain.usecases.authUsecases.IsLoggedInUseCase
import com.gsoft.nimblechalenge.domain.usecases.authUsecases.LogoutUseCase
import com.gsoft.nimblechalenge.domain.usecases.authUsecases.RefreshTokenUseCase
import com.gsoft.nimblechalenge.presentation.login.LoginScreenState
import com.gsoft.nimblechalenge.util.NetworkUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val refreshTokenUseCase: RefreshTokenUseCase,
    private val isLoggedInUseCase: IsLoggedInUseCase,
    private val networkUtils: NetworkUtils
) : ViewModel() {

    private val _state = mutableStateOf(SplashState())
    val state: State<SplashState> = _state

    init {
        isLogged()
    }

      fun isLogged(){
         viewModelScope.launch {
             val isLogged = isLoggedInUseCase.invoke()
             if (networkUtils.isNetworkConnected()){
                 _state.value = _state.value.copy(goToHome = false)
                 refreshToken()
             }else{
                 if (isLogged){
                     _state.value = _state.value.copy(goToHome = true)
                 }else{
                     _state.value = _state.value.copy(goToHome = false)
                     _state.value = _state.value.copy(goToLogin = false)
                     _state.value = _state.value.copy(goToNoConn = true)

                 }
             }

         }
    }

     fun refreshToken(){
        viewModelScope.launch {
            delay(1000) //just a hair of delay
            _state.value = _state.value.copy(isLoading = true)
            _state.value = _state.value.copy(isError = false)
            _state.value = _state.value.copy(isAuth = false)
            _state.value = _state.value.copy(goToHome = false)
            _state.value = _state.value.copy(goToLogin = false)

            try {
                val tokenResponse = refreshTokenUseCase.invoke()
                if (tokenResponse){
                    _state.value = _state.value.copy(isLoading = false)
                    _state.value = _state.value.copy(isError = false)
                    _state.value = _state.value.copy(isAuth = true)
                    _state.value = _state.value.copy(error = null)
                    _state.value = _state.value.copy(goToLogin = false)
                    _state.value = _state.value.copy(goToHome = true)
                }else{
                    _state.value = _state.value.copy(isLoading = false)
                    _state.value = _state.value.copy(isError = false)
                    _state.value = _state.value.copy(isAuth = false)
                    _state.value = _state.value.copy(error = null)
                    _state.value = _state.value.copy(goToHome = false)
                    _state.value = _state.value.copy(goToLogin = true)
                }

            }catch (e: Exception){
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(isError = true)
                _state.value = _state.value.copy(isAuth = false)
                _state.value = _state.value.copy(goToLogin = true)
                _state.value = _state.value.copy(goToHome = false)
                _state.value = _state.value.copy(error = "Something Happens! Try again Later")

            }
        }
    }


}