package com.example.simondicemvvc

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * ViewModel for the Game
 */
class MyViewModel : ViewModel() {
    /**
     * Function to generate a random number between 0 and the upperLimit
     * @param upperLimit Int indicating the upper limit of the random number
     * @return Int indicating the generated random number
     */
    fun generateRandomNumber(upperLimit: Int): Int {
        return (0..upperLimit).random()
    }

    /**
     * Adds a color to the user sequence
     * @param color Int indicating the color to be added to the user sequence
     */
    fun addColorToUserSequence(color: Int) {
        SingletonData.userSequence.add(color)
    }

    /**
     * Increases the round number by 1
     */
    fun increaseRound() {
        SingletonData.round.value++
    }

    /**
     * Resets the current round
     */
    fun resetRound() {
        SingletonData.round.value = 0
    }

    /**
     * Adds a color to the sequence
     * @param color Int indicating the color to be added to the sequence
     */
    fun addColorToSequence(color: Int) {
        SingletonData.sequence.add(color)
    }

    /**
     * Increases the color sequence
     * shows the user the color sequence
     */
    fun increaseMachineSequence() {
        SingletonData.status = GameState.SEQUENCE
        addColorToSequence(generateRandomNumber(Colors.values().size - 1))
        getMachineSequence()
    }

    /**
     * Increases the user color sequence
     */
    fun increaseUserSequence(color: Int) {
        SingletonData.status = GameState.INPUT
        SingletonData.userSequence.add(color)
        getUserSequence()
    }

    /**
     * Gets the last element of the user sequence
     */
    fun getLastElementOfUserSequence(): Int {
        return SingletonData.userSequence.last()
    }

    /**
     * Checks if the user sequence is correct
     * @return Boolean indicating if the user sequence is correct
     */
    fun checkUserSequence(): Boolean {
        SingletonData.status = GameState.CHECKING
        return SingletonData.sequence == SingletonData.userSequence
    }

    /**
     * Shows the user color sequence
     */
    fun getUserSequence(): MutableList<Int> {
        Log.d(SingletonData.logTag, "User: ${SingletonData.userSequence.toString()}")
        return SingletonData.userSequence
    }

    /**
     * Getter for the color sequence
     */
    fun getColorSequence(): MutableList<Int> {
        return SingletonData.sequence
    }

    /**
     * Getter for the user color sequence
     */
    fun getUserColorSequence(): MutableList<Int> {
        return SingletonData.userSequence
    }

    /**
     * Shows the machine color sequence and logs the sequence
     */
    fun getMachineSequence(): MutableList<Int> {
        SingletonData.status = GameState.SEQUENCE
        Log.d(SingletonData.logTag, "Machine: ${SingletonData.sequence.toString()}")
        return SingletonData.sequence
    }

    /**
     * Shows the user color sequence and logs the sequence
     */
    fun showUserSequence(): MutableList<Int> {
        Log.d(SingletonData.logTag, "User: ${SingletonData.userSequence.toString()}")
        return SingletonData.userSequence
    }

    /**
     * Resets the sequence
     */
    fun resetSequence() {
        SingletonData.sequence.clear()
    }

    /**
     * Resets the user sequence
     */
    fun resetUserSequence() {
        SingletonData.userSequence.clear()
    }

    /**
     * Resets the game state
     */
    fun resetGameState() {
        SingletonData.status = GameState.START
    }

    /**
     * Resets the game record
     */
    fun resetRecord() {
        SingletonData.highScore = 0
    }

    /**
     * Gets the last element of the machine sequence
     * @return Int indicating the last element of the machine sequence
     */
    fun getLastElementOfMachineSequence(): Int {
        return SingletonData.sequence.last()
    }

    /**
     * Resets the game
     */
    fun resetGame() {
        resetRound()
        resetSequence()
        resetUserSequence()
        resetGameState()
        resetRecord()
    }

    /**
     * Checks if the record has been surpassed
     * @return Boolean indicating if the record has been surpassed
     */
    fun checkRecord(): Boolean {
        return SingletonData.round.value > SingletonData.highScore
    }

    /**
     * Updates the record
     */
    fun updateRecord() {
        SingletonData.highScore = SingletonData.round.value
    }
    /**
     * get status
     */
    fun getStatus(): GameState {
        return SingletonData.status
    }
    /**
     * set status
     */
    fun setStatus(state:GameState){
        SingletonData.status = state
    }
    /**
     * Returns the current round
     */
    fun getRound(): Int {
        return SingletonData.round.value
    }

    /**
     * Checks if the user sequence is correct
     */
    fun checkSequence(): Boolean {
        return SingletonData.sequence == SingletonData.userSequence
    }

    /**
     * Darkens the color
     */
    fun darkenColor(color: Color, factor: Float): Color {
        val r = (color.red * (1 - factor)).coerceIn(0f, 1f)
        val g = (color.green * (1 - factor)).coerceIn(0f, 1f)
        val b = (color.blue * (1 - factor)).coerceIn(0f, 1f)
        return Color(r, g, b, color.alpha)
    }

    fun showMachineSequence(time: Long) {
        Log.d("DijoSimon", "Showing the sequence")
        viewModelScope.launch {
            for (i in SingletonData.sequence) {
                Log.d("DijoSimon","Valor de la secuencia existente: ${SingletonData.sequence}")
                SingletonData.auxColor= SingletonData.colorList[i].value
                SingletonData.numOfColors[i].color.value= darkenColor(SingletonData.auxColor,0.5f)
                delay(time)
                SingletonData.numOfColors[i].color.value= SingletonData.auxColor
                delay(time)
                Log.d("DijoSimon", "Mostramos el color $i oscurecido")
            }
        }
    }
    /**
     * Increase the user sequence
     */
    fun blinkUserWhiteShow(numColor: Int) = runBlocking {
        blinkUserWhite(numColor)
    }

    fun blinkUserWhite(color: Int) {
        Log.d("SimonSays", "Whitening")

        viewModelScope.launch {
            SingletonData.auxColor = SingletonData.colorList[color].value
            SingletonData.colorList[color].value = Color.White
            delay(75)
            SingletonData.colorList[color].value = SingletonData.auxColor
        }

        Log.d("SimonSays", "Lighting up pressed")
    }

    }








