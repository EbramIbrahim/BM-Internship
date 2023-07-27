package com.example.bmcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmcompose.ui.theme.BMComposeTheme
import com.example.bmcompose.ui.theme.StateViewModel

class MainActivity : ComponentActivity() {

    private val stateViewModel by viewModels<StateViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BMComposeTheme {
                IncreaseCounter()
            }
        }
    }

    @Preview
    @Composable
    fun IncreaseCounter() {

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // first way
            val count: MutableState<Int> = rememberSaveable { mutableStateOf(0) }
            // second way
            val counterViewModel = stateViewModel.counter
            Text(text = "You have had ${counterViewModel.value} glasses")
            Button(onClick = { stateViewModel.increment() }, Modifier.padding(top = 10.dp)) {
                Text("Add one")
            }

        }
    }
}

