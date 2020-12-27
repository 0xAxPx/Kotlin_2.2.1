package com.peshale

import com.peshale.domain.Post
import com.peshale.wall.WallService
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.random.Random


internal class WallServiceTest {

    @BeforeEach
    fun cleanPostStore() = WallService.postsStore.clear()

    @Test
    fun `test that new post is added and number of posts == 1`() {
        val wallService = WallService()
        wallService.add(Post.createPostWithRandomData(1))
        assertTrue(1 == WallService.getNumberOfPosts())
    }

    @Test
    fun `test that after adding of two posts number of posts == 2`() {
        val wallService = WallService()
        val post1 = Post.createPostWithRandomData(1)
        val post2 = Post.createPostWithRandomData(1)
        val post3 = Post.createPostWithRandomData(3)
        wallService.add(post1)
        wallService.add(post2)
        wallService.add(post3)
        assertTrue(3 == WallService.getNumberOfPosts())
        assertTrue(1 == WallService.getPost(1).id)
        assertTrue(1 == WallService.getPost(2).id)
    }

    @Test
    fun `test update existing post and id and date fields should be changed`() {
        val wallService = WallService()
        val post = Post.createPostWithRandomData(22)
        val currentId = post.id
        val currentDate = post.date
        //add post on wall
        wallService.add(post)

        //update post
        post.ownerId = 111333444
        assertTrue(wallService.update(post))

        println(WallService.getNumberOfPosts() == 1)
        assertTrue(currentId == WallService.getPost(22).id)
        assertTrue(currentDate == WallService.getPost(22).date)
        assertTrue(111333444 == WallService.getPost(22).ownerId)
    }

    @Test
    fun `test update non existing post, update() should return false`() {
        val wallService = WallService()
        val post = Post.createPostWithRandomData(Random.nextInt())
        val postId = post.id
        println("Post with ID $postId to be saved")
        //add post on wall
        wallService.add(post)

        assertTrue(1 == WallService.getNumberOfPosts())

        val notExistingPost = Post.createPostWithRandomData(333)

        //update not existing post
        wallService.update(notExistingPost)
        //check number of posts was not changed
        assertTrue(1 == WallService.getNumberOfPosts())
        assertFalse(333 == WallService.getPost(post.id).id)
    }
}