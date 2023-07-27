package com.example.bmcompose.ui.theme

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class StateViewModel: ViewModel() {

    var counter: MutableState<Int> = mutableStateOf(0)
        private set


    fun increment() {
        counter.value++
    }






}