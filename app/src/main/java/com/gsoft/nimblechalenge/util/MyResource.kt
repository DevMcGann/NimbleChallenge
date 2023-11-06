package com.gsoft.nimblechalenge.util

import java.lang.Exception

sealed class MyResource<out T> {
    object Loading: MyResource<Nothing>()
    data class Success<out T>(val data: T): MyResource<T>()
    data class Failure(val exception: Exception): MyResource<Nothing>()
}