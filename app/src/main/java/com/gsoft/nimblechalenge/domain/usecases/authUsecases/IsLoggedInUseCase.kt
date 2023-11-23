package com.gsoft.nimblechalenge.domain.usecases.authUsecases

import com.gsoft.nimblechalenge.data.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Named


class IsLoggedInUseCase  @Inject constructor(
    @Named("auth") private val authRepository: AuthRepository,
){
    suspend fun invoke() : Boolean{
        return authRepository.isLoggedIn()
    }
}