package com.gsoft.nimblechalenge.domain.usecases.surveyUsecases

import com.gsoft.nimblechalenge.data.repository.SurveyRepository
import com.gsoft.nimblechalenge.domain.model.Survey
import com.gsoft.nimblechalenge.util.MyResource
import com.gsoft.nimblechalenge.util.Resource
import javax.inject.Inject
import javax.inject.Named

class GetSurveys @Inject constructor (
    @Named("api")private val apiRepository: SurveyRepository
) {
    suspend operator fun invoke(page:Int) : Resource<List<Survey?>> {
        return  apiRepository.getSurveys(page)
    }
}