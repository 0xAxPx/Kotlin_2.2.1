package com.peshale.wall

import com.peshale.domain.Post
import kotlin.random.Random

class WallService() {

    companion object {
        var ids = ArrayList<Int>()
        var postsStore = ArrayList<Post>()

        fun getNumberOfPosts(): Int {
            return postsStore.size
        }

        fun getPost(id: Int): Post {
            var index = 0
            for (i in postsStore.indices) if (postsStore[i].id == id) {
                index = i
            }
            return postsStore[index]
        }
    }

    fun add(post: Post): Post {
        val newPost = setUniquePostId(post)
        postsStore.add(newPost)
        ids.add(newPost.id)
        return newPost
    }

    fun update(post: Post): Boolean {
        var update = false
        for (i in postsStore.indices) {
            val currentId = post.id
            val currentDateCreated = post.date
            if (postsStore[i].id == post.id) {
                update = true
                val newPost = post.copy(id = currentId, date = currentDateCreated)
                postsStore[i] = newPost
            } else {
                update = false
            }
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