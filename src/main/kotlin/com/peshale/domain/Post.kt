package com.peshale.domain

import kotlin.random.Random

/*
com.peshale.domain.Post objects https://vk.com/dev/objects/post
 */

class Post(
    id: Int,
    ownerId: Int,
    fromId: Int,
    createdBy:  Int,
    date: Long,
    text: String,
    replyOwnerId: Int,
    replyPostId: Int,
    friendsOnly: Boolean,
    comments: Comments,
    copyright: Copyright,
    likes: Likes,
    reposts: Reposts,
    views: Views,
    postType: String,
    signerId: Int,
    canPin: Boolean,
    canDelete: Boolean,
    canEdit: Boolean,
    isPinned: Boolean,
    markedAsAds: Boolean,
    isFavorite: Boolean,
    donut: Donut,
    postponedId: Int) {

    companion object {
        /*
    fun for testing purpose
     */
        fun prepareData(id: Int): Post {
            return Post(
                id,
                Random.nextInt(),
                Random.nextInt(),
                Random.nextInt(),
                System.currentTimeMillis(),
                "Post",
                Random.nextInt(),
                Random.nextInt(),
                false,
                Comments(1, true, true, true, true),
                Copyright(Random.nextInt(), true, "VK", "default"),
                Likes(Random.nextInt(), true, true, true),
                Reposts(Random.nextInt(), false),
                Views(Random.nextInt()),
                "Test",
                Random.nextInt(),
                true,
                true,
                true,
                false,
                false,
                false,
                Donut(false, 0, "", false, "default"),
                Random.nextInt(),
            )
        }
    }

    override fun toString(): String {
        return "Post()"
    }
}
