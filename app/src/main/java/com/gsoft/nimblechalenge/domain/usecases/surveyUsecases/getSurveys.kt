package com.gsoft.nimblechalenge.domain.usecases.surveyUsecases

import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.domain.model.SurveyDomain
import com.gsoft.nimblechalenge.util.MyResource
import javax.inject.Inject
import javax.inject.Named

class GetSurveys @Inject constructor (
    @Named("api")private val apiRepository: SurveyRepository
) {
    suspend operator fun invoke(page : Int) : MyResource<List<SurveyDomain>?> {
        return  apiRepository.getSurveys(page)
    }
}