package com.gsoft.nimblechalenge.domain.usecases.authUsecases

import com.gsoft.nimblechalenge.data.repository.AuthRepository
import com.gsoft.nimblechalenge.util.Constants.KEY_REFRESH_TOKEN
import com.gsoft.nimblechalenge.util.SharePreferencesManager
import javax.inject.Inject
import javax.inject.Named

class RefreshTokenUseCase  @Inject constructor(
    @Named("auth") private val authRepository: AuthRepository,
    private val sharePreferencesManager: SharePreferencesManager
){
    suspend fun invoke(token:String) {
        if(sharePreferencesManager.getString(KEY_REFRESH_TOKEN, null) != null){
            authRepository.refreshToken(token)
        }
    }
}