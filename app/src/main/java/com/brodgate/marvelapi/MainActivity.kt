package com.brodgate.marvelapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brodgate.marvelapi.ui.theme.MainContainer
import com.brodgate.marvelapi.ui.theme.MarvelApiTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelApiTheme {
                MainContainer()
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview(){
    MainContainer()
}

