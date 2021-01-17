package com.peshale

import com.peshale.domain.Post
import com.peshale.domain.attachments.*
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
        val attachment = VideoAttachments("Video", Video())
        wallService.add(Post.createPostWithRandomData(attachment))
        assertTrue(1 == WallService.getNumberOfPosts())
    }

    @Test
    fun `test that we have unique id in the list of posts`() {
        val wallService = WallService()
        val setIds = linkedSetOf<Int>()
        val attachment = DocumentAttachments("Document", Document())
        for (i in 1 until 100_000) {
            val post = Post.createPostWithRandomData(attachment)
            wallService.add(post)
            setIds.add(post.id)
        }

        //to check uniqueness of id
        assertTrue(WallService.getNumberOfPosts() == setIds.size)
    }

    @Test
    fun `test update existing post and id and date fields should be changed`() {
        val wallService = WallService()
        val attachment = VideoAttachments("Video", Video())
        val post = Post.createPostWithRandomData(attachment)
        val currentId = post.id
        val currentDate = post.date

        //add post on wall
        wallService.add(post)

        //update post
        post.ownerId = 111333444
        assertTrue(wallService.update(post))

        println(WallService.getNumberOfPosts() == 1)
        assertTrue(currentId == WallService.getPost(currentId).id)
        assertTrue(currentDate == WallService.getPost(currentId).date)
        assertTrue(111333444 == WallService.getPost(currentId).ownerId)
    }

    @Test
    fun `test update non existing post, update() should return false`() {
        val wallService = WallService()
        val attachment = WebLinkAttachments("WebLink", WebLink())
        val post = Post.createPostWithRandomData(attachment)
        val postId = post.id
        println("Post with ID $postId to be saved")
        //add post on wall
        wallService.add(post)

        assertTrue(1 == WallService.getNumberOfPosts())

        val notExistingPost = Post.createPostWithRandomData(attachment)

        //update not existing post
        wallService.update(notExistingPost)
        //check number of posts was not changed
        assertTrue(1 == WallService.getNumberOfPosts())
        assertFalse(333 == WallService.getPost(post.id).id)
    }
}