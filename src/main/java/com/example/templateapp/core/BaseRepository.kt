package com.example.templateapp.core

import com.example.templateapp.core.datatype.Result
import com.example.templateapp.data.datasource.remote.exceptions.handleNetworkExceptions
import retrofit2.Response


open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): Result<T> {
        try {
            val response = call()
            if (response.isSuccessful)
                return Result.Success(response.body()!!)
        } catch (e: Exception) {
            return Result.Error(handleNetworkExceptions(e))
        }
    }
}
