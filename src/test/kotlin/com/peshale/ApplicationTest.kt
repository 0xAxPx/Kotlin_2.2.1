package com.peshale

import com.peshale.wall.Wall
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


internal class ApplicationTest() {

    @Test
    fun `test that size of array should be 10`() {
        val post = Wall(Application.run(12)).posts
        assertTrue(12 == post.size)

    }




}