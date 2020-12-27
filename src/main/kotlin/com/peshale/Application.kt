package com.peshale

import com.peshale.domain.Post
import com.peshale.wall.WallService

fun main() {

    val postsStore = ArrayList<Post>()

    println("Size of array is ${postsStore.size}")

    val wallService = WallService(postsStore)

    //for testing only
    val newPost = Post.createPostWithRandomData(id = 1)

    wallService.add(newPost)

    println("Size of array is ${postsStore.size}")

    for (i in postsStore.indices) {
        println("New post: ${postsStore[i]}")
    }
}