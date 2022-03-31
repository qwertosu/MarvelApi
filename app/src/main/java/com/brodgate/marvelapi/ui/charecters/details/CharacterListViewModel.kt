package com.brodgate.marvelapi.ui.charecters.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brodgate.marvelapi.model.Result
import com.brodgate.marvelapi.repository.MarvelRepository
import com.brodgate.marvelapi.repository.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel : ViewModel() {

    private val _viewState = MutableSharedFlow<CharacterViewState>(
        replay = 2,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val viewState = _viewState.asSharedFlow()

    private val repository = MarvelRepository()

    private val characters = mutableSetOf<Result>()

    private var offset = 0

    val isNetworkCallInProgress = mutableStateOf(false)

    init {
        startFlow()
    }

    private fun startFlow() {
        viewModelScope.launch {
            repository.resultState.collect {
                when (it) {
                    ResultState.Idle -> {
                        _viewState.tryEmit(CharacterViewState.IsCenterLoading(true))
                        repository.getCharacters()
                    }
                    is ResultState.ResultError -> {
                        val errorMsg = it.message
                        sendMessage(errorMsg)
                        isNetworkCallInProgress.value = false
                    }
                    is ResultState.ResultSuccess -> {
                        characters.addAll(it.results ?: emptyList())
                        withContext(Dispatchers.Main) {
                            _viewState.apply {
                                tryEmit(CharacterViewState.IsCenterLoading(false))
                                delay(10)
                                tryEmit(CharacterViewState.IsRowLoading(false))
                                delay(10)
                                tryEmit(CharacterViewState.CharactersResult(characters.toList()))
                                offset += 100
                                isNetworkCallInProgress.value = false
                            }
                        }
                    }
                }
            }
        }
    }

    fun getCharacterData(characterId: Int) {

    }

    fun getCharacters() {
        _viewState.tryEmit(CharacterViewState.IsRowLoading(true))
        isNetworkCallInProgress.value = true
        viewModelScope.launch {
            repository.getCharacters(offset)
        }
    }

    fun sendMessage(msg: String) {
        _viewState.tryEmit(CharacterViewState.ShowMessage(msg))
    }
}