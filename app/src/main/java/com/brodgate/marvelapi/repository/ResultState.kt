package com.brodgate.marvelapi.repository

import com.brodgate.marvelapi.model.Result

sealed interface ResultState{
    data class ResultSuccess(val results: List<Result>?) : ResultState
    data class ResultError(val message : String) : ResultState
    object Idle : ResultState
}