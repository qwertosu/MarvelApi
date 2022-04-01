package com.brodgate.marvelapi.ui.charecters.details

import com.brodgate.marvelapi.model.Result

sealed interface CharacterViewState{
    data class IsCenterLoading(val isLoading : Boolean = false) : CharacterViewState
    data class IsRowLoading(val isLoading : Boolean = false) : CharacterViewState
    data class ShowToastMessage(val message : String = "") : CharacterViewState
    data class CharactersResult(val result : List<Result>) : CharacterViewState
    object Idle : CharacterViewState

}