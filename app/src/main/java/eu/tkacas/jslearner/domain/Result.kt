package eu.tkacas.jslearner.domain

sealed class Result<out T> {
    data class Success<out T>(val result: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}