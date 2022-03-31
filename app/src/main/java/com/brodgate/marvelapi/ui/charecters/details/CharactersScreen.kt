package com.brodgate.marvelapi.ui.charecters.details

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.brodgate.marvelapi.R
import com.brodgate.marvelapi.dpToSp
import com.brodgate.marvelapi.isScrolledToEnd
import com.brodgate.marvelapi.model.Result


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharactersScreen(
    context: Context,
    navController: NavController,
    viewModel: CharacterListViewModel,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {
    val scaffoldState = rememberScaffoldState()
    val characters = remember { mutableStateListOf<Result>() }
    var isCenterLoading by remember { mutableStateOf(false) }
    var isRowLoading by remember { mutableStateOf(false) }
    val scrollState = rememberLazyListState()
    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledToEnd()
        }
    }
    LaunchedEffect("effects") {
        viewModel.viewState.collect {
            when (it) {
                CharacterViewState.Idle -> {

                }
                is CharacterViewState.IsCenterLoading -> {
                    isCenterLoading = it.isLoading
                }
                is CharacterViewState.ShowMessage -> {
                    val message = it.message
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
                is CharacterViewState.CharactersResult -> {
                    if (it.result.isNotEmpty()) {
                        characters.apply {
                            clear()
                            addAll(it.result)
                        }
                    }
                }
                is CharacterViewState.IsRowLoading -> {
                    isRowLoading = it.isLoading
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopBar(viewModel)
        }
    ) {
        if (isCenterLoading.not()) {
            val firstCharMap = characters.groupBy { it.name?.first() }
            val lastKey = if(firstCharMap.keys.isEmpty()) 0 else firstCharMap.keys.last()
            val lastItemsSize = firstCharMap[lastKey]?.size ?: 0
            AnimatedVisibility(visible = isCenterLoading.not()) {
                LazyColumn(state = scrollState) {
                    firstCharMap.forEach { (initial, groupedChars) ->
                        stickyHeader {
                            Text(
                                text = "SECTION: $initial",
                                color = Color.White,
                                modifier = Modifier
                                    .background(color = Color.Black)
                                    .padding(8.dp)
                                    .fillMaxWidth()
                            )
                        }
                        itemsIndexed(groupedChars) { i, item ->
                            CharacterRow(item)
                            if (endOfListReached && !viewModel.isNetworkCallInProgress.value) {
                                viewModel.getCharacters()
                            }
                        }
                    }
                }
                if (isRowLoading) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                        CircularProgressIndicator(color = Color.White)
                    }
                }
            }
        } else {
            CenterLoadingScreen()
        }
    }
}

@Composable
fun CenterLoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xff21282d)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(color = Color.White)
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
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 1.dp, start = 3.dp, end = 3.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xff21282d))
        ) {
            val imageUrl = item.thumbnail?.path + "/portrait_xlarge.jpg"
            Log.d("IMAGE_URL", imageUrl)
            val name = item.name
            val description = item.description
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl.replace("http://", "https://"))
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.kotlin),
                contentDescription = "character",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
                    .size(64.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.White, CircleShape)
            )
            Column {
                Text(
                    text = name ?: "",
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 5.dp),
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(
                        color = Color.Green,
                        fontSize = dpToSp(20.dp),
                        fontFamily = FontFamily.Monospace,
                    )
                )
                Text(
                    text = description ?: "",
                    color = Color.White,
                    modifier = Modifier.padding(top = 10.dp, start = 5.dp)

                )
            }
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