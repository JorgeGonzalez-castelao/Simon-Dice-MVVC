package com.example.simondicemvvc

import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

/**
 * Enum indicating the game state
 */
enum class GameState {
    START, SEQUENCE, INPUT, CHECKING
}

/**
 * Enum indicating the colors to be used in the game
 */
enum class Colors(var color: MutableState<Color>) {
    RED(mutableStateOf(Color.Red)),
    YELLOW(mutableStateOf(Color.Yellow)),
    GREEN(mutableStateOf(Color.Green)),
    BLUE(mutableStateOf(Color.Blue));

    // Method to darken the color
    fun darken() {
        color.value = color.value.copy(alpha = 0.8f)
    }
}

/**
 * Class containing the data used in the game
 * @property round Int indicating the current round
 * @property sequence List<Int> containing the sequence of colors to be displayed
 * @property userSequence List<Int> containing the sequence of colors entered by the user
 * @property highScore Int indicating the highest score in the game
 * @property status Enum indicating the game status
 * @property logTag String indicating the tag to use for logging
 * @property auxColor Color indicating the color that has been pressed
 * @property numOfColors Int indicating the number of colors to be used in the game
 * @property colorList List<Color> containing the colors to be used in the game
 */
object SingletonData {
    // the round variable must be an int that reacts to changes made to it in the UI
    var round = mutableStateOf<Int>(0)
    var sequence = mutableListOf<Int>()
    var userSequence = mutableListOf<Int>()
    var highScore = 0
    var status = GameState.START
    // Constant indicating the tag to use for logging
    val logTag = "SimonSays"
    // Auxiliary variable to save the color that has been pressed
    var auxColor: Color = Color.White
    var numOfColors = Colors.values()
    var colorList = listOf(
        Colors.RED.color,
        Colors.YELLOW.color,
        Colors.GREEN.color,
        Colors.BLUE.color)
}

