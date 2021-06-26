package com.hiku.generative
import java.util.*

class MarkovChain {
    fun main(startVertex: Int): Int {
        var rangeA: Double
        var rangeB: Double

        println("start vertex: $startVertex")

        val myNumbers = Constants.MARKOV_MATRIX
        var vertex = startVertex
//            val myNumbers = doubleArrayOf(0.6, 0.7, 0.8, 1.0)
        var r = Random()
        var randomValue = 0 + (1 - 0) * r.nextDouble()
        //println(randomValue)
        //        rangeA=0.0000000000000001;
        for (startVertex in myNumbers.indices) {
            if (startVertex != 0) {
//                rangeA = myNumbers[i-1];
            }
            rangeB = myNumbers[vertex][startVertex]
            if (randomValue <= rangeB) {
                //println("chance: " + myNumbers[vertex][startVertex])
                vertex = startVertex
                //println("end vertex: $vertex")
                break
            }
        }
        return(vertex)
    }
}