package com.gsoft.nimblechalenge.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.nimblechalenge.domain.usecases.surveyUsecases.GetSurveyUseCase
import com.gsoft.nimblechalenge.util.MyResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurveyUseCase: GetSurveyUseCase
): ViewModel() {


    private val _state = mutableStateOf(HomeScreenState())
    var state: State<HomeScreenState> = _state


     fun getSurvey() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            _state.value = _state.value.copy(isError = false)
            _state.value = _state.value.copy( surveyData = null)
            _state.value = _state.value.copy(errorMessage = "")
            delay(2000) //just for the sake of this challenge,  most of the times api is so fast that the shimmer screen cant be seen
            try{
                when (val response = getSurveyUseCase.invoke()){
                    is MyResource.Success -> {
                        _state.value = _state.value.copy(surveyData = response.data)
                        _state.value = _state.value.copy(isLoading = false)
                    }
                    is MyResource.Failure -> {
                        _state.value = _state.value.copy(isLoading = false)
                        _state.value = _state.value.copy(isError = true)
                        _state.value = _state.value.copy(errorMessage = response.exception.message.toString())

                    }

                    else -> {
                        _state.value = _state.value.copy(
                            isLoading = false,
                            isError = false,
                            errorMessage = "",
                            surveyData = null
                        )
                    }
                }

            }catch (e: Exception){
                _state.value = _state.value.copy(isError = true)
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(errorMessage = e.message.toString())
            }
        }//corroutine
    }


    fun getCurrentDateFormattedString(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, MMMM d", Locale.ENGLISH)
        return dateFormat.format(calendar.time).uppercase()
    }


}