package com.brodgate.marvelapi.ui.charecters.details

import com.brodgate.marvelapi.model.Result

sealed interface CharacterViewState{
    data class IsLoading(val isLoading : Boolean = false) : CharacterViewState
    data class ShowMessage(val message : String = "") : CharacterViewState
    data class CharactersResult(val result : List<Result>) : CharacterViewState
    object Idle : CharacterViewState

}