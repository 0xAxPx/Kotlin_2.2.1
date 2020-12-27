package com.peshale

import com.peshale.domain.Post
import com.peshale.wall.WallService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


internal class WallServiceTest() {

    @Test
    fun `test that new post is added and size != 0`() {
        val wallService = WallService()
        wallService.add(Post.createPostWithRandomData(1))
        assertTrue(1 == WallService.getNumberOfPosts())
    }

    @Test
    fun `test that post is updated excluding id and date created fields`() {
        val wallService = WallService()
        val post = Post.createPostWithRandomData(22)
        val currentId = post.id
        val currentDate = post.date
        //add post on wall
        wallService.add(post)

        //update post
        post.ownerId = 111333444
        wallService.update(post)
        
        println(WallService.getNumberOfPosts() == 1)
        assertTrue(currentId == WallService.getPost(22).id)
    }
}