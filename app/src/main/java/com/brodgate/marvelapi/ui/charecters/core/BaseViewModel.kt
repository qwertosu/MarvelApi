package com.brodgate.marvelapi.ui.charecters.core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


abstract class BaseViewModel<T> : ViewModel() {

    protected val _viewState = MutableSharedFlow<T>(
        replay = 3,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val viewState = _viewState.asSharedFlow()
}