package com.peshale.wall

import com.peshale.domain.Post
import kotlin.random.Random

class WallService(val posts: ArrayList<Post>) {

    companion object {
        var ids = ArrayList<Int>()
//        var postsStore = emptyArray<Post>()
    }

    fun add(post: Post): Post {
        val newPost = setUniquePostId(post)
        this.posts.add(newPost)
        return newPost
    }

    fun update(post: Post): Boolean {
        var update = false
        println("Before update: $post")
        for (i in this.posts.indices) {
            val currentId = post.id
            val currentDateCreated = post.date
            update = this.posts[i].id == post.id
            val newPost = post.copy(id = currentId, date = currentDateCreated)
            this.posts[i] = newPost
            println("After update: $newPost")
        }
        return update
    }


    private fun setUniquePostId(post: Post): Post {
        //check if id exists in array of id
        val currentId = post.id
        for (i in ids.indices) {
            if (ids[i] == currentId) {
                post.id = Random.nextInt()
            }
        }
        return post
    }
}