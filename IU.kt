package com.example.simondicemvvc

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
var ctxt : Context? = null

/**
 * Class representing the user interface
 */
class UI(viewModel: MyViewModel) {
    @Composable
    fun simon(viewModel: MyViewModel) {
        Row {
            addColumn("left", viewModel)
            addColumn("right", viewModel)
        }
    }

    @Composable
    fun addColumn(columnSide: String, viewModel: MyViewModel) {
        if (columnSide.equals("left")) {
            Column {
                colorButton("red", viewModel)
                colorButton("green", viewModel)
                startAndRestartButton(viewModel)
            }
        }
        if (columnSide.equals("right")) {
            Column {
                roundText(viewModel)
                colorButton("yellow", viewModel)
                colorButton("blue", viewModel)
                sendButton(viewModel)
            }
        }
    }

    @Composable
    fun colorButton(color: String, viewModel: MyViewModel) {
        if (color.equals("red")) {
            Button(
                onClick = {
                    if (viewModel.getStatus().equals(GameState.INPUT)) {
                        pressedButton(0, color, viewModel)
                        viewModel.blinkUserWhite(0)
                    }
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Colors.RED.color.value)){}
        }
        if (color.equals("yellow")) {
            Button(
                onClick = {
                    if (viewModel.getStatus().equals(GameState.INPUT)) {
                        pressedButton(1, color, viewModel)
                        viewModel.blinkUserWhite(1)
                        Log.d("SimonSays", "State: ${viewModel.getStatus()}")
                    }
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Colors.YELLOW.color.value)){}
        }
        if (color.equals("green")) {
            Button(
                onClick = {
                    if (viewModel.getStatus().equals(GameState.INPUT)) {
                        pressedButton(2, color, viewModel)
                        viewModel.blinkUserWhite(2)
                    }
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Colors.GREEN.color.value)){}
        }
        if (color.equals("blue")) {
            Button(
                onClick = {
                    if (viewModel.getStatus().equals(GameState.INPUT)) {
                        pressedButton(3, color, viewModel)
                        viewModel.blinkUserWhite(3)
                    }
                },
                modifier = Modifier
                    .height(100.dp)
                    .width(175.dp)
                    .padding(40.dp, 0.dp),
                colors = ButtonDefaults.buttonColors(Colors.BLUE.color.value)){}
        }
    }

    @Composable
    fun startAndRestartButton(viewModel: MyViewModel) {
        Button(
            onClick = {
                if (viewModel.getRound() != 0) {
                    viewModel.resetRound()
                    viewModel.resetSequence()
                    viewModel.resetUserSequence()
                    Log.d(SingletonData.logTag, "Reset")
                } else {
                    viewModel.setStatus(GameState.START)
                    Log.d(SingletonData.logTag, "Start")
                    viewModel.resetSequence()
                    viewModel.resetUserSequence()
                    viewModel.increaseMachineSequence()
                    viewModel.showMachineSequence(300)
                    viewModel.setStatus(GameState.INPUT)
                }
            },
            modifier = Modifier
                .height(35.dp)
                .width((350 / 2).dp)
                .padding(horizontal = 30.dp, vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Black)
        ) {
            if (viewModel.getRound() == 0) {
                Text(text = "Start")
            } else {
                Text(text = "Restart")
            }
        }
    }

    @Composable
    fun sendButton(viewModel: MyViewModel) {
        ctxt = LocalContext.current
        Button(
            onClick = {
                if (viewModel.checkSequence()) {
                    Log.d(SingletonData.logTag, "Correct sequence")
                    viewModel.setStatus(GameState.CHECKING)
                    viewModel.increaseRound()
                    viewModel.increaseMachineSequence()
                    viewModel.showMachineSequence(300)
                    viewModel.resetUserSequence()
                    viewModel.setStatus(GameState.INPUT)
                    Log.d(SingletonData.logTag, "send, increase the round ${viewModel.getRound()}")
                } else {
                    Toast.makeText(ctxt , "Hacked", Toast.LENGTH_SHORT).show()
                    Log.d(SingletonData.logTag, "Incorrect sequence")
                    viewModel.resetSequence()
                    viewModel.resetUserSequence()
                    viewModel.resetRound()
                    Log.d(SingletonData.logTag, "send, restart the round ${viewModel.getRound()}")
                }
            },
            modifier = Modifier
                .height(30.dp)
                .width((300 / 2).dp)
                .padding(horizontal = 30.dp, vertical = 0.dp),
            colors = ButtonDefaults.buttonColors(Color.Gray)
        ) {
            Image(
                painter = painterResource(R.drawable.okayge),
                contentDescription = "Icon2"
            )
        }
    }

    @Composable
    fun roundText(viewModel: MyViewModel) {
        Text(text = "Round: ${viewModel.getRound()}")
    }

    fun pressedButton(color: Int, colorWhich: String, viewModel: MyViewModel) {
        viewModel.increaseUserSequence(color)
        Log.d(SingletonData.logTag, "Button pressed: $colorWhich")
    }
}
