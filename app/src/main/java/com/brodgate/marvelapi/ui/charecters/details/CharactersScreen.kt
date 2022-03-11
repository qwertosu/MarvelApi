package com.brodgate.marvelapi.ui.charecters.details

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.brodgate.marvelapi.R
import com.brodgate.marvelapi.model.Result
import kotlinx.coroutines.flow.collect


@Composable
fun CharactersScreen(
    context: Context,
    navController: NavController,
    viewModel: CharacterListViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val scaffoldState = rememberScaffoldState()
    val characters = remember { mutableStateListOf<Result>() }
    val lifecycleState = rememberUpdatedState(lifecycleOwner)
    LaunchedEffect("effects") {
        viewModel.viewState.collect {
            when (it) {
                CharacterViewState.Idle -> {

                }
                is CharacterViewState.IsLoading -> {

                }
                is CharacterViewState.ShowMessage -> {
                    val message = it.message
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                is CharacterViewState.CharactersResult -> {
                    if (it.result.isNotEmpty()) {
                        characters.addAll(it.result)
                    }
                }
            }
        }
    }

//    DisposableEffect(lifecycleState.value) {
//        val lifecycle = lifecycleState.value.lifecycle
//        val observer = LifecycleEventObserver { owner, event ->
//            when (event) {
//                Lifecycle.Event.ON_RESUME -> {
//                    viewModel.getCharacters()
//                }
//                else -> {}
//            }
//        }
//        lifecycle.addObserver(observer)
//        onDispose { lifecycle.removeObserver(observer) }
//    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(viewModel)
        }
    ) {
        LazyColumn {
            items(characters) { item ->
                CharacterRow(item)
            }
        }
    }
}

class CharacterParams : PreviewParameterProvider<Result> {

    override val values: Sequence<Result>
        get() = listOf(
            Result(
                name = "Character name",
                description = "Character Description"
            )
        ).asSequence()

}

@Preview
@Composable
fun CharacterRow(@PreviewParameter(CharacterParams::class) item: Result) {
    Row(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.kotlin),
            contentDescription = "character",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(top = 5.dp, start = 5.dp)
                .size(64.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Blue, CircleShape)
        )
        Column {
            Text(
                text = item.name ?: "",
                color = Color.White,
                modifier = Modifier.padding(top = 10.dp, start = 5.dp)
            )
            Text(
                text = item.description ?: "",
                color = Color.White,
                modifier = Modifier.padding(top = 10.dp, start = 5.dp)
            )
        }
    }
}

@Composable
fun TopBar(viewModel: CharacterListViewModel) {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                modifier = Modifier.padding(horizontal = 12.dp),
                contentDescription = "Action icon"
            )
        },
        title = { Text("TopBar") },
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.clickable {
            val s = "Test"
            viewModel.sendMessage(s)
        }
    )
}