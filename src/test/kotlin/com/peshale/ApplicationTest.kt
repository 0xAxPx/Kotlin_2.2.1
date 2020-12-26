package com.peshale

import com.peshale.wall.WallService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


internal class ApplicationTest() {

    @Test
    fun `test that size of array should be 10`() {
        val post = WallService(Application.run(12)).posts
        assertTrue(12 == post.size)

    }




}