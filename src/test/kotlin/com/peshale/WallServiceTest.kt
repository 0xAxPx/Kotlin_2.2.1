package com.peshale

import com.peshale.domain.Comment
import com.peshale.domain.Post
import com.peshale.domain.attachments.DocumentAttachments
import com.peshale.domain.attachments.VideoAttachments
import com.peshale.utility.Utilities
import com.peshale.wall.WallService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class WallServiceTest {

    @BeforeEach
    fun cleanPostStore() = WallService.posts.clear()

    @Test
    fun `test that new post is added and number of posts == 1`() {
        val wallService = WallService()
        val attachment = VideoAttachments("Video", Any())
        wallService.add(Post.createPostWithRandomData(Utilities.randomPosInt(),attachment, null))
        assertTrue(1 == WallService.getNumberOfPosts())
    }

    @Test
    fun `test that we have unique id in the list of posts`() {
        val wallService = WallService()
        val setIds = linkedSetOf<Int>()
        val attachment = DocumentAttachments("Document", "War and Peace")
        for (i in 1 until 10) {
            val post = Post.createPostWithRandomData(Utilities.randomPosInt(), attachment, null)
            wallService.add(post)
            setIds.add(post.postId)
        }

        //to check uniqueness of id
        assertTrue(WallService.getNumberOfPosts() == setIds.size)
    }

    @Test
    fun `test update existing post and id and date fields should be changed`() {
        val wallService = WallService()
        val attachment = VideoAttachments("Photo", "instagram")
        val post = Post.createPostWithRandomData(Utilities.randomPosInt(), attachment, null)
        val currentId = post.postId
        val currentDate = post.date

        //add post on wall
        wallService.add(post)

        //update post
        post.ownerId = 111333444
        assertTrue(wallService.update(post))

        println(WallService.getNumberOfPosts() == 1)
        assertTrue(currentId == WallService.getPost(currentId).postId)
        assertTrue(currentDate == WallService.getPost(currentId).date)
        assertTrue(111333444 == WallService.getPost(currentId).ownerId)
    }

    @Test
    fun `test update non existing post, update() should return false`() {
        val wallService = WallService()
        val attachment = VideoAttachments("WebLink", "wiki")
        val post = Post.createPostWithRandomData(Utilities.randomPosInt(), attachment, null)
        val postId = post.postId
        println("Post with ID $postId to be saved")
        //add post on wall
        wallService.add(post)

        assertTrue(1 == WallService.getNumberOfPosts())

        val notExistingPost = Post.createPostWithRandomData(Utilities.randomPosInt(), attachment, null)

        //update not existing post
        wallService.update(notExistingPost)
        //check number of posts was not changed
        assertTrue(1 == WallService.getNumberOfPosts())
        assertFalse(333 == WallService.getPost(post.postId).postId)
    }

    @Test
    fun `test adding comment to existing post run successfully`() {
        val wallService = WallService()
        val attachment = VideoAttachments("Video", Any())
        val post = wallService.add(Post.createPostWithRandomData(Utilities.randomPosInt(), attachment, null))
        assertTrue(1 == WallService.getNumberOfPosts())
        val comment = Comment(
            commentId = 1,
            postId = post.postId,
            fromId = 2,
            date = System.currentTimeMillis(),
            text = "Hello!",
            donut = null,
            replyToUser = 2,
            replyToComment = 22,
            attachment = null,
            parentStack = emptyArray(),
            thread = "....."
        )

        wallService.createComment(comment)
        assertTrue(comment == wallService.getLastComment())
    }

    @Test
    fun `test adding comment to non existing post leads to throwing an exception`() {
        val wallService = WallService()
        val attachment = VideoAttachments("Video", Any())
        val post = wallService.add(Post.createPostWithRandomData(1, attachment, null))
        assertTrue(1 == WallService.getNumberOfPosts())
        val fakeId = 11111

        val comment1 = Comment(
            commentId = 1,
            postId = post.postId,
            fromId = 2,
            date = System.currentTimeMillis(),
            text = "Hello!",
            donut = null,
            replyToUser = 2,
            replyToComment = 22,
            attachment = null,
            parentStack = emptyArray(),
            thread = "....."
        )

        wallService.createComment(comment1)

        val post2 = wallService.add(Post.createPostWithRandomData(2, attachment, null))

        val comment2 = Comment(
            commentId = 1,
            postId = post2.postId,
            fromId = 2,
            date = System.currentTimeMillis(),
            text = "Hello!",
            donut = null,
            replyToUser = 2,
            replyToComment = 22,
            attachment = null,
            parentStack = emptyArray(),
            thread = "....."
        )

        wallService.createComment(comment2)

        val comment3 = Comment(
            commentId = 1,
            postId = fakeId,
            fromId = 2,
            date = System.currentTimeMillis(),
            text = "Hello!",
            donut = null,
            replyToUser = 2,
            replyToComment = 22,
            attachment = null,
            parentStack = emptyArray(),
            thread = "....."
        )

        val exception = Assertions.assertThrows(PostNotFoundException::class.java) {
            wallService.createComment(comment3)
        }

        assertTrue("Post $fakeId does not exist" == exception.message)
    }
}