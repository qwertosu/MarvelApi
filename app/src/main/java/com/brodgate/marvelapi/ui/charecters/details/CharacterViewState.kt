package com.brodgate.marvelapi.ui.charecters.details

sealed interface CharacterViewState{
    data class IsLoading(val isLoading : Boolean = false) : CharacterViewState
    object InProgress : CharacterViewState
}