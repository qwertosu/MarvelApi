package com.brodgate.marvelapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brodgate.marvelapi.ui.charecters.details.CharacterListViewModel
import com.brodgate.marvelapi.ui.theme.MarvelApiTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CharacterListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelApiTheme {
                MainContainer(this, viewModel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}

