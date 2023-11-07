package com.gsoft.nimblechalenge.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.nimblechalenge.domain.usecases.authUsecases.SigninUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val signInUseCase: SigninUseCase,
    ) : ViewModel() {

    private val _state = mutableStateOf(LoginScreenState())
    val state: State<LoginScreenState> = _state

    fun login(email:String, password:String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val tokenResponse = signInUseCase.invoke(email = email , password = password )
                if(tokenResponse?.data?.attributes?.access_token != null){
                    _state.value = _state.value.copy(loginData = tokenResponse)
                    _state.value = _state.value.copy(isLoading = false)
                    _state.value = _state.value.copy(isError = false)
                    _state.value = _state.value.copy(isAuth = true)
                }else{
                    if (tokenResponse?.data == null && tokenResponse?.error != null){
                        _state.value = _state.value.copy(isLoading = false)
                        _state.value = _state.value.copy(isError = true)
                        _state.value = _state.value.copy(message = tokenResponse.error)
                    }
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(isError = true)
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(message = e.message.toString())
            }
        }

    }
}