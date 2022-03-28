package com.brodgate.marvelapi.ui.charecters.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brodgate.marvelapi.model.Result
import com.brodgate.marvelapi.repository.MarvelRepository
import com.brodgate.marvelapi.repository.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
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

    init {
        startFlow()
    }

    private fun startFlow() {
        viewModelScope.launch {
            repository.resultState.collect {
                when (it) {
                    ResultState.Idle -> {
                        _viewState.tryEmit(CharacterViewState.IsLoading(true))
                        repository.getCharacters()
                    }
                    is ResultState.ResultError -> {
                        val errorMsg = it.message
                        sendMessage(errorMsg)
                    }
                    is ResultState.ResultSuccess -> {
                        characters.addAll(it.results ?: emptyList())
                        withContext(Dispatchers.Main) {
                            _viewState.tryEmit(CharacterViewState.IsLoading(false))
                            _viewState.tryEmit(
                                CharacterViewState.CharactersResult(
                                    characters.toList()
                                )
                            )
                            offset += 100
                        }
                    }
                }
            }
        }
    }

    fun getCharacterData(characterId: Int) {

    }

    fun getCharacters() {
        viewModelScope.launch {
            repository.getCharacters(offset)
        }
    }

    fun sendMessage(msg: String) {
        _viewState.tryEmit(CharacterViewState.ShowMessage(msg))
    }
}