package com.gsoft.nimblechalenge.util


import retrofit2.Response

suspend inline fun <DB, REMOTE> LocalOrRemoteDataAccess(
    crossinline fetchFromLocal: suspend () -> DB,
    shouldFetchFromRemote: Boolean = true,
    crossinline fetchFromRemote: suspend () -> Response<REMOTE>,
    crossinline processRemoteResponse: (response: Response<REMOTE>) -> Unit = { },
    crossinline saveRemoteData: (REMOTE) -> Unit = { },
    crossinline onFetchFailed: (errorBody: String?, statusCode: Int) -> Unit = { _: String?, _: Int -> }
): MyResource<DB> {
    try {
        val localData = fetchFromLocal()

        return if (shouldFetchFromRemote) {
            val remoteResponse = fetchFromRemote()
            if (remoteResponse.isSuccessful) {
                processRemoteResponse(remoteResponse)
                remoteResponse.body()?.let { saveRemoteData(it) }
                MyResource.Success(localData)
            } else {
                onFetchFailed(remoteResponse.errorBody()?.string(), remoteResponse.code())
                MyResource.Failure(Exception("Request failed with code ${remoteResponse.code()}"))
            }
        } else {
            MyResource.Success(localData)
        }
    } catch (e: Exception) {
        return MyResource.Failure(e)
    }
}

