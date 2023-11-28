package com.gsoft.nimblechalenge.domain.usecases.authUsecases

import com.gsoft.nimblechalenge.data.model.ResetPasswordResponse
import com.gsoft.nimblechalenge.data.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Named

class ResetPasswordUseCase  @Inject constructor(
    @Named("auth") private val authRepository: AuthRepository
){
    suspend fun invoke(email: String): ResetPasswordResponse {
        return authRepository.passwordReset(email)
    }
}