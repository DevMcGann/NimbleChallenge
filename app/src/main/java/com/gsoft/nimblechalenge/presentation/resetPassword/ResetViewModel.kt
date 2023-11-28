package com.gsoft.nimblechalenge.presentation.resetPassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.nimblechalenge.domain.usecases.authUsecases.ResetPasswordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase,
) : ViewModel() {


    private val _state = mutableStateOf(ResetPasswordState())
    val state: State<ResetPasswordState> = _state

    fun resetPassword(email:String) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            try {
                val response = resetPasswordUseCase.invoke(email = email )
                if (response.meta != null) {
                    _state.value = _state.value.copy(isLoading = false)
                    _state.value = _state.value.copy(isError = false)
                    _state.value = _state.value.copy(showNotification = true)
                    _state.value = _state.value.copy(goToLogin = true)
                }

                if (response.errors != null) {
                    _state.value = _state.value.copy(isLoading = false)
                    _state.value = _state.value.copy(isError = true)
                    _state.value = _state.value.copy(error = response.errors[0].detail)  //yeah maybe this is not the best way and should be handled better
                }


            } catch (e: Exception) {
                _state.value = _state.value.copy(isError = true)
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(error = e.message.toString())
            }
        }
    }


}