package com.gsoft.nimblechalenge.domain.usecases.surveyUsecases

import com.gsoft.nimblechalenge.data.model.SurveyResponse
import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.util.MyResource
import javax.inject.Inject
import javax.inject.Named

class GetSurveyUseCase @Inject constructor (
    @Named("api")private val apiRepository: SurveyRepository
) {
    suspend operator fun invoke() : MyResource<SurveyResponse?> {
        return  apiRepository.getSurveyList()
    }
}