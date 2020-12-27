package com.peshale.utility

import kotlin.random.Random

class Utilities {

    companion object {

        fun randomPosInt(): Int {
            return Random.nextInt(1, Int.MAX_VALUE)
        }

    }

}