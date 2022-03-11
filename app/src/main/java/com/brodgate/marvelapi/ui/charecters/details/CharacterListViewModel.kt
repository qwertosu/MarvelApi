package com.brodgate.marvelapi.ui.charecters.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brodgate.marvelapi.repository.MarvelRepository
import com.brodgate.marvelapi.repository.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel : ViewModel() {

    private val _viewState = MutableSharedFlow<CharacterViewState>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val viewState = _viewState.asSharedFlow()

    private val repository = MarvelRepository()

    init {
        startFlow()
    }

    private fun startFlow() {
        viewModelScope.launch {
            repository.resultState.collect {
                when (it) {
                    ResultState.Idle -> {
                        repository.getCharacters()
                    }
                    is ResultState.ResultError -> {
                        val errorMsg = it.message
                        sendMessage(errorMsg)
                    }
                    is ResultState.ResultSuccess -> {
                        val results = it.results
                        withContext(Dispatchers.Main) {
                            _viewState.tryEmit(
                                CharacterViewState.CharactersResult(
                                    results ?: emptyList()
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun getCharacterData(characterId: Int) {

    }

    fun getCharacters(){
        viewModelScope.launch {
            repository.getCharacters()
        }
    }

    fun sendMessage(msg: String) {
        _viewState.tryEmit(CharacterViewState.ShowMessage(msg))
    }
}