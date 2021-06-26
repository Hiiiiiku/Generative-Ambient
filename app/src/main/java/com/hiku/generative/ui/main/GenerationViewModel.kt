package com.hiku.generative.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.core.internal.n
import com.hiku.generative.GenerationFragment
import com.hiku.generative.MarkovChain
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class GenerationViewModel : ViewModel() {

    private var startVertex = 0

    fun runForever() {
        viewModelScope.launch {
            while(true) {
                delay(5000)
                startVertex = MarkovChain().main(startVertex)
                println("GenerationFragment: $startVertex")
            }
        }
    }



//    fun cancelRun() {
//        job?.cancelChildren()
//    }

}