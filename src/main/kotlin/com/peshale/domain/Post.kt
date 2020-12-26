package com.peshale.domain

import kotlin.random.Random

/*
com.peshale.domain.Post objects https://vk.com/dev/objects/post
 */

class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val createdBy:  Int,
    val date: Long,
    val text: String,
    val replyOwnerId: Int,
    val replyPostId: Int,
    val friendsOnly: Boolean,
    val comments: Comments,
    val copyright: Copyright,
    val likes: Likes,
    val reposts: Reposts,
    val views: Views,
    val postType: String,
    val signerId: Int,
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
        return "Post(id=$id, ownerId=$ownerId, fromId=$fromId, createdBy=$createdBy, date=$date, text='$text', replyOwnerId=$replyOwnerId, replyPostId=$replyPostId, friendsOnly=$friendsOnly, comments=$comments, copyright=$copyright, likes=$likes, reposts=$reposts, views=$views, postType='$postType', signerId=$signerId, canPin=$canPin, canDelete=$canDelete, canEdit=$canEdit, isPinned=$isPinned, markedAsAds=$markedAsAds, isFavorite=$isFavorite, donut=$donut, postponedId=$postponedId)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Post

        if (id != other.id) return false
        if (ownerId != other.ownerId) return false
        if (fromId != other.fromId) return false
        if (createdBy != other.createdBy) return false
        if (date != other.date) return false
        if (text != other.text) return false
        if (replyOwnerId != other.replyOwnerId) return false
        if (replyPostId != other.replyPostId) return false
        if (friendsOnly != other.friendsOnly) return false
        if (comments != other.comments) return false
        if (copyright != other.copyright) return false
        if (likes != other.likes) return false
        if (reposts != other.reposts) return false
        if (views != other.views) return false
        if (postType != other.postType) return false
        if (signerId != other.signerId) return false
        if (canPin != other.canPin) return false
        if (canDelete != other.canDelete) return false
        if (canEdit != other.canEdit) return false
        if (isPinned != other.isPinned) return false
        if (markedAsAds != other.markedAsAds) return false
        if (isFavorite != other.isFavorite) return false
        if (donut != other.donut) return false
        if (postponedId != other.postponedId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + ownerId
        result = 31 * result + fromId
        result = 31 * result + createdBy
        result = 31 * result + date.hashCode()
        result = 31 * result + text.hashCode()
        result = 31 * result + replyOwnerId
        result = 31 * result + replyPostId
        result = 31 * result + friendsOnly.hashCode()
        result = 31 * result + comments.hashCode()
        result = 31 * result + copyright.hashCode()
        result = 31 * result + likes.hashCode()
        result = 31 * result + reposts.hashCode()
        result = 31 * result + views.hashCode()
        result = 31 * result + postType.hashCode()
        result = 31 * result + signerId
        result = 31 * result + canPin.hashCode()
        result = 31 * result + canDelete.hashCode()
        result = 31 * result + canEdit.hashCode()
        result = 31 * result + isPinned.hashCode()
        result = 31 * result + markedAsAds.hashCode()
        result = 31 * result + isFavorite.hashCode()
        result = 31 * result + donut.hashCode()
        result = 31 * result + postponedId
        return result
    }


}
