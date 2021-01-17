package com.peshale.domain

import com.peshale.domain.attachments.Attachment
import com.peshale.utility.Utilities
import kotlin.random.Random

/*
com.peshale.domain.Post objects https://vk.com/dev/objects/post
 */

data class Post (
    //we set 0 for each post, WallService is responsible to assign unique id for each new post
    var postId: Int = 0,
    var ownerId: Int,
    val fromId: Int,
    val createdBy:  Int,
    val date: Long,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    var comments: Comment?,
    val copyright: Copyright?,
    val likes: Likes,
    val reposts: Reposts?,
    val views: Views?,
    val postType: String,
    val signerId: Int,
    val attachments: Attachment?,
    val canPin: Boolean,
    val canDelete: Boolean,
    val canEdit: Boolean,
    val isPinned: Boolean,
    val markedAsAds: Boolean,
    val isFavorite: Boolean,
    val donut: Donut,
    val postponedId: Int) {

    companion object {
        /*
    fun for testing purpose
     */
        fun createPostWithRandomData(postId: Int, attachment: Attachment, comment: Comment?): Post {
            return Post(
                postId = postId,
                ownerId = Utilities.randomPosInt(),
                fromId = Utilities.randomPosInt(),
                createdBy = Utilities.randomPosInt(),
                date = System.currentTimeMillis(),
                text = "Post",
                replyOwnerId = Utilities.randomPosInt(),
                replyPostId = Utilities.randomPosInt(),
                friendsOnly = false,
                comments = comment,
                copyright = Copyright(Utilities.randomPosInt(), true, "VK", "default"),
                likes = Likes(Utilities.randomPosInt(), true, true, true),
                reposts = Reposts(Utilities.randomPosInt(), false),
                views = Views(Random.nextInt()),
                postType = "Test",
                signerId = Utilities.randomPosInt(),
                attachments = attachment,
                canPin = true,
                canDelete = true,
                canEdit = false,
                isPinned = false,
                markedAsAds = false,
                isFavorite = true,
                donut = Donut(false, 0, "", false, "default"),
                postponedId = Utilities.randomPosInt()
            )
        }
    }
}
