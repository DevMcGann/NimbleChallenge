package com.gsoft.nimblechalenge.domain.usecases.authUsecases

import com.gsoft.nimblechalenge.data.model.TokenResponse
import com.gsoft.nimblechalenge.data.repository.AuthRepository
import javax.inject.Inject
import javax.inject.Named

class SigninUseCase  @Inject constructor(
    @Named("auth") private val authRepository: AuthRepository
){
    suspend fun invoke(email: String, password: String): TokenResponse? {
        return authRepository.login(email, password)
    }
}