package com.brodgate.marvelapi

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.brodgate.marvelapi.ui.charecters.core.NavigationGraph
import com.brodgate.marvelapi.ui.charecters.details.CharacterListViewModel
import com.brodgate.marvelapi.ui.charecters.details.CharactersScreen


@Composable
fun MainContainer(context: Context) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NavigationGraph.Route.CHARACTERS_LIST) {
        composable(route = NavigationGraph.Route.CHARACTERS_LIST) {
            CharactersScreen(context, navController)
        }
    }
}