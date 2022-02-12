package com.brodgate.marvelapi.ui.charecters.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class CharacterListViewModel : ViewModel() {

    private val _viewState = MutableStateFlow(CharacterViewState.IsLoading())
    val viewState = _viewState.asStateFlow()

    fun getCharacterData(characterId : Int){

    }


}