package com.brodgate.marvelapi.ui.charecters.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.brodgate.marvelapi.model.Result
import com.brodgate.marvelapi.repository.MarvelRepository
import com.brodgate.marvelapi.repository.ResultState
import com.brodgate.marvelapi.ui.charecters.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharacterListViewModel : BaseViewModel<CharacterViewState>() {

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
                                if (!isNetworkCallInProgress.value) {
                                    tryEmit(CharacterViewState.IsCenterLoading(false))
                                }
                                tryEmit(CharacterViewState.IsRowLoading(false))
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
        _viewState.tryEmit(CharacterViewState.ShowToastMessage(msg))
    }
}