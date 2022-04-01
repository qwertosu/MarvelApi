package com.brodgate.marvelapi.di

import com.brodgate.marvelapi.ui.charecters.details.CharacterListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel { CharacterListViewModel() }
}