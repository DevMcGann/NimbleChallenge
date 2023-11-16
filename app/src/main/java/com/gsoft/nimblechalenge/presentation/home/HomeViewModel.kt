package com.gsoft.nimblechalenge.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.nimblechalenge.domain.usecases.surveyUsecases.GetSurveys
import com.gsoft.nimblechalenge.util.MyResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getSurveys: GetSurveys
): ViewModel() {


    private val _state = mutableStateOf(HomeScreenState())
    var state: State<HomeScreenState> = _state



    fun getSurveys(){
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = _state.value.copy(isLoading = true)
            _state.value = _state.value.copy(isError = false)
            _state.value = _state.value.copy( surveyData = null)
            _state.value = _state.value.copy(errorMessage = "")
            delay(2000)
            try{
                when (val response = getSurveys.invoke(state.value.currentPage)){
                    is MyResource.Success -> {
                        _state.value = _state.value.copy(surveys = response.data)
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
                            surveyData = null,
                            surveys = emptyList()
                        )
                    }
                }

            }catch (e: Exception){
                _state.value = _state.value.copy(isError = true)
                _state.value = _state.value.copy(isLoading = false)
                _state.value = _state.value.copy(errorMessage = e.message.toString())
            }
        }
    }


    fun getCurrentDateFormattedString(): String {
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("EEEE, MMMM d", Locale.ENGLISH)
        return dateFormat.format(calendar.time).uppercase()
    }


}