package com.gsoft.nimblechalenge.data.model

import com.google.gson.annotations.SerializedName
import com.gsoft.nimblechalenge.util.Constants

data class ResetPasswordResponse(
    @SerializedName("meta")
    val meta : ResetMeta? = null,
    @SerializedName("errors")
    val errors : List<ResetErrors>? = null
)

data class ResetMeta(
    val message : String
)

data class ResetErrors(
    val detail : String,
    val code : String
)


data class ResetPasswordRequestBody(
    val user : User,
    val client_id : String = Constants.CLIENT_ID,
    val client_secret : String = Constants.CLIENT_SECRET,
)

data class User(
    val email: String
)
