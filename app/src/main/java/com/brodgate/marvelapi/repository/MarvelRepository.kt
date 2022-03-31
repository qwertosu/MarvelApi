package com.brodgate.marvelapi.repository

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext

class MarvelRepository {

    private val characterApiDataManager by lazy {
        CharacterApiDataManager()
    }

    private val errorHandler by lazy {
        CoroutineExceptionHandler { _, exception ->
            println("Exception: $exception")
        }
    }

    private val _resultState = MutableStateFlow<ResultState>(ResultState.Idle)
    val resultState = _resultState.asStateFlow()

    suspend fun getCharacters(offset: Int = 0) {
        withContext(Dispatchers.IO + errorHandler) {
            val charactersData = characterApiDataManager.getCharacters(offset)
            val results = charactersData.data?.results
            _resultState.update { ResultState.ResultSuccess(results) }
        }
    }
}