package com.hiku.generative

import java.util.*

class MarkovChain {
    object MarkovChain {
        @JvmStatic
        fun main(args: Array<String>) {
            var rangeA: Double
            var rangeB: Double
            //        double [][] myNumbers = {{0.1, 0.2, 0.3, 0.4},
//                {0.1, 0.2, 0.3, 0.4},
//                {0.1, 0.2, 0.3, 0.4},
//                {0.1, 0.2, 0.3, 0.4}};

            val myNumbers = arrayOf(
                arrayOf(0.6, 0.7, 0.8, 1.0),
                arrayOf(0.6, 0.7, 0.8, 1.0),
                arrayOf(0.6, 0.7, 0.8, 1.0),
                arrayOf(0.6, 0.7, 0.8, 1.0),
                arrayOf(0.6, 0.7, 0.8, 1.0)
            )
            var k = 0
            var vertex = 0
//            val myNumbers = doubleArrayOf(0.6, 0.7, 0.8, 1.0)
            while(k != 10)
            {
            var r = Random()
            var randomValue = 0 + (1 - 0) * r.nextDouble()
            println(randomValue)
            //        rangeA=0.0000000000000001;

                for (j in myNumbers.indices) {
                    if (j != 0) {
//                rangeA = myNumbers[i-1];
                    }
                    rangeB = myNumbers[vertex][j]
                    if (randomValue <= rangeB) {
                        println("chance: " + myNumbers[vertex][j])
                        vertex = j
                        println("vertex: $vertex")
                        break
                    }
                }
                k += 1
            }
        }
    }
}