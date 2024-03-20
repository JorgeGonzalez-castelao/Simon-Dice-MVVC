package com.example.simondicemvvc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.simondicemvvc.ui.theme.SimonDiceMVVCTheme

/**
 * Videojuego Simon Dice
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimonDiceMVVCTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    "Simon Dice"
                    // Creo un objeto de la clase IU para usar el metodo simon
                    var myViewModel = MyViewModel()
                    val miIU = UI(myViewModel)
                    miIU.simon(myViewModel)
                }
            }
        }
    }
}