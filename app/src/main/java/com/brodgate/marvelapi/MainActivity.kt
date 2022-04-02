package com.brodgate.marvelapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.brodgate.marvelapi.repository.MarvelRepository
import com.brodgate.marvelapi.ui.theme.MarvelApiTheme
import org.koin.android.ext.android.get

class MainActivity : ComponentActivity() {

    private val repository : MarvelRepository = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarvelApiTheme {
                MainContainer(this)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}

