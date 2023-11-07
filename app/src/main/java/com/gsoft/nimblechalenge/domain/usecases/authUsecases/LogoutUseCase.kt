package com.gsoft.nimblechalenge.domain.usecases.authUsecases

import com.gsoft.nimblechalenge.data.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Named

class LogoutUseCase  @Inject constructor(
    @Named("auth") private val authRepository: AuthRepository,
){
    suspend fun invoke() {
        return authRepository.logout()
    }
}