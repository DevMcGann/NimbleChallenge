package com.gsoft.nimblechalenge.data.model

import com.gsoft.nimblechalenge.util.Constants

data class TokenResponse(
    val data: TokenData? = null,
    val error: String? = null
)

data class TokenData(
    val id: String,
    val type: String,
    val attributes: TokenAttributes
)

data class TokenAttributes(
    val access_token: String,
    val token_type: String,
    val refresh_token: String
)


data class TokenRequestBody(
    val grant_type: String = Constants.GRANT_TYPE_PASSWORD,
    val email: String,
    val password: String,
    val client_id: String = Constants.CLIENT_ID,
    val client_secret: String = Constants.CLIENT_SECRET
)


data class RefreshTokenRequestBody(
    val grant_type : String = Constants.GRANT_TYPE_REFRESH_TOKEN,
    val client_id : String = Constants.CLIENT_ID,
    val client_secret : String = Constants.CLIENT_SECRET,
    val refresh_token : String? = null
)

data class LogoutRequestBody(
    val client_id : String = Constants.CLIENT_ID,
    val client_secret : String = Constants.CLIENT_SECRET,
    val token : String
)



