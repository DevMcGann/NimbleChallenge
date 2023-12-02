package com.gsoft.nimblechalenge.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gsoft.nimblechalenge.domain.model.Survey
import com.gsoft.nimblechalenge.domain.usecases.surveyUsecases.GetSurveys
import com.gsoft.nimblechalenge.util.Resource
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
            setLoadingState()
            delay(2000)
            try{
                when (val response = getSurveys.invoke(state.value.currentPage)){
                     Resource.success(response.data) -> {
                        setSuccessState(response.data.orEmpty())
                    }
                    Resource.error(message = response.message.orEmpty(), data = null) -> {
                        setErrorState(response.message.orEmpty())

                    }

                    else -> {
                        setErrorState(response.message.orEmpty())
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

    fun setLoadingState(){
        _state.value = _state.value.copy(isLoading = true)
        _state.value = _state.value.copy(isError = false)
        _state.value = _state.value.copy( surveyData = null)
        _state.value = _state.value.copy(errorMessage = "")
    }

    fun setSuccessState(data :  List<Survey?>){
        _state.value = _state.value.copy(surveys = data)
        _state.value = _state.value.copy(isLoading = false)
    }

    private fun setErrorState(message: String){
        _state.value = _state.value.copy(isLoading = false)
        _state.value = _state.value.copy(isError = true)
        _state.value = _state.value.copy(errorMessage = message)
        _state.value = _state.value.copy(surveys = emptyList())
    }

    fun increasePage(){
        _state.value = _state.value.copy(currentPage = _state.value.currentPage + 1)
        getSurveys()
    }


}