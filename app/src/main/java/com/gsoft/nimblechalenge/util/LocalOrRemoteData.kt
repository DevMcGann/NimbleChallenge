package com.gsoft.epicchallenge.util


import retrofit2.Response

suspend inline fun <DB, REMOTE> LocalOrRemoteDataAccess(
    crossinline fetchFromLocal: suspend () -> DB,
    shouldFetchFromRemote: () -> Boolean = true,
    crossinline fetchFromRemote: suspend () -> Response<REMOTE>,
    crossinline processRemoteResponse: (response: Response<REMOTE>) -> Unit = { },
    crossinline saveRemoteData: (REMOTE) -> Unit = { },
    crossinline onFetchFailed: (errorBody: String?, statusCode: Int) -> Unit = { _: String?, _: Int -> }
): Resource<DB> {
    try {
        val localData = fetchFromLocal()

        return if (shouldFetchFromRemote) {
            val remoteResponse = fetchFromRemote()
            if (remoteResponse.isSuccessful) {
                processRemoteResponse(remoteResponse)
                remoteResponse.body()?.let { saveRemoteData(it) }
                Resource.success(localData)
            } else {
                onFetchFailed(remoteResponse.errorBody()?.string(), remoteResponse.code())
                Resource.error(remoteResponse.message(), localData)
            }
        } else {
            Resource.success(localData)
        }
    } catch (e: Exception) {
        return Resource.error(e.message.toString(), null)
    }
}
